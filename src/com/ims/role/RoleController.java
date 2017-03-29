package com.ims.role;



import com.ims.common.service.SRole;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;

/**
 *用户模块
 */
public class RoleController extends Controller {
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


