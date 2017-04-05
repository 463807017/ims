package com.ims.role;



import java.util.List;

import com.ims.common.service.SRole;
import com.ims.common.service.SRoleright;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;

/**
 *用户模块
 */
public class RoleController extends Controller {
	
	public void updateRoleRight() {
		long roleId =  getParaToLong("role_ids",-1L);
		long resourceId =  getParaToLong("resource_id",-1L);
		String opFlgs = getPara("op_flgs");
		
		if(resourceId == -1||roleId == -1){
			renderJson("{\"flag\":\"参数异常\"}");
			return ;
		}
		
		
		String deleteSql = "delete from s_roleright where resource_id=? and role_id=?";
		Db.update(deleteSql,resourceId,roleId);
		if(!StringUtil.isNull(opFlgs)){
			String op[] = opFlgs.split(",");
			for (int i = 0; i < op.length; i++) {
				String temp = op[i];
				SRoleright s = new SRoleright();
				s.setResourceId(resourceId);
				s.setRoleId(roleId);
				s.setOpFlg(Integer.parseInt(temp));
				s.save();
			}
		}
		
		renderJson("{\"flag\":\"操作成功\"}");
		
	}
	
	public void editRoleRight() {
		int roleId =  getParaToInt("role_id");
		int resourceId =  getParaToInt("resource_id");

		
		//查询菜单所有角色的权限
		String sql = "select r.id,r.name,group_concat(rr.op_flg) from s_roleright rr,s_role r where r.id=rr.role_id and rr.resource_id=? and r.id=? group by r.id";
		Object[] rights = Db.queryFirst(sql, resourceId,roleId);
		renderJson(rights);
	}
	
	public void addRoleRight() {
		String resourceId = getPara("resource_id");
		String roleIds = getPara("role_ids");
		String opFlgs = getPara("op_flgs");
		
		if(StringUtil.isNull(resourceId) ||StringUtil.isNull(roleIds) || StringUtil.isNull(opFlgs)){
			renderJson("{\"flag\":\"参数异常\"}");
			return ;
		}
		
		String role[] = roleIds.split(",");
		String op[] = opFlgs.split(",");
		
		StringBuffer sb = new StringBuffer();
		//插入到roleright表
		for (int i = 0; i < role.length; i++) {
			
			for (int j = 0; j < op.length; j++) {
				SRoleright r = new SRoleright();
				r.setResourceId(Long.parseLong(resourceId));
				r.setOpFlg(Integer.parseInt(op[j]));
				r.setRoleId(Long.parseLong(role[i]));
				
				try {
					r.save();
				} catch (Exception e) {
					sb.append(r.getRoleId()).append("-").append(r.getOpFlg()).append(";");
				}
				
			}
			
		}
		if(sb.length() > 0){
			renderJson("{\"flag\":\"" + sb.toString() + "失败\"}");
		}else{
			renderJson("{\"flag\":\"操作成功\"}");
		}
	}
	
	public void roleJson() {
		List<SRole> roles = SRole.dao.find("Select id,name text from s_role");
		renderJson(roles);
	}
	
	public void index() {
		StringBuffer condition = new StringBuffer();
		SRole sRole = (SRole) getModel(SRole.class);
		setAttr("sRole", sRole);
		if(sRole != null){
			condition.append(" where 1=1 ");
			if(!StringUtil.isNull(sRole.getName())){
				condition.append(" and name like '%" + sRole.getName() + "%'");
			}
		}
		setAttr("rolePage", SRole.dao.paginate(getParaToInt(0, 1), 5,condition.toString()));
		render("/WEB-INF/mvcs/role/role.html");
	}
	
	public void add() {
		render("/WEB-INF/mvcs/role/add.html");
	}
	
	public void save() {
		getModel(SRole.class).save();
		redirect("/role/");
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		SRole Role = SRole.dao.findById(para);
		setAttr("role", Role);
		render("/WEB-INF/mvcs/Role/add.html");
	}
	
	public void update() {
		getModel(SRole.class).update();
		redirect("/role/");
	}
	
	public void delete() {
		int para =  getParaToInt("id");
		SRole.dao.deleteById(para);
		redirect("/role/");
	}
}


