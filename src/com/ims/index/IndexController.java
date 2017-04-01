package com.ims.index;

import java.util.List;

import com.ims.common.service.SResource;
import com.ims.common.service.SRoleuser;
import com.ims.common.service.SUser;
import com.ims.util.StringUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * 登录
 * 
 * IndexController
 */
public class IndexController extends Controller {
	@Clear
	public void index() {
		render("/WEB-INF/mvcs/login/login.html");
	}
	
	public void main(){
		Object user = getSessionAttr("user");
		if(user == null){
			redirect("/");
		}else{
			render("/WEB-INF/mvcs/index/index.html");
		}
	}
	
	@Clear
	public void login() throws Exception{
		SUser user = getModel(SUser.class);
		System.out.println(this.hashCode());
		if(StringUtil.isNull(user.getLoginName())){
			throw new Exception("用户名为空");
		}
		SqlPara sqlPara = new SqlPara();
		sqlPara.setSql("select * from s_user u where u.login_name=? and u.login_passwd=?");
		sqlPara.addPara(user.getLoginName()).addPara(user.getLoginPasswd());
		
		SUser sUser = user.dao().findFirst(sqlPara);
		if(sUser == null){
			throw new Exception("用户名或者密码错误");
		}else{
			this.setSessionAttr("user", sUser);
			
			//获取系统角色
			List<SRoleuser> roles =  SRoleuser.dao.find("select * from s_roleuser r where r.user_id=?", sUser.getId());
			this.setSessionAttr("roles", roles);
			if(roles != null && roles.size() > 0){
				//查询到角色
				StringBuffer roleStr = new StringBuffer("select * from s_resource r where r.id in (select resource_id from s_roleright where role_id in(");
				for (int i = 0; i < roles.size(); i++) {
					SRoleuser roleuser =  roles.get(i);
					if(i == roles.size() - 1)
						roleStr.append(roleuser.getRoleId()).append(")");
					else
						roleStr.append(roleuser.getRoleId()).append(",");
				}
				roleStr.append(" and op_flg=0) and parent_id=? order by order_id desc");//0 表示访问权限
				
				
				SResource resources = new SResource();
				resources.setId(0L);
				resources.setName("root");
				resources.setLevel(0);
				
				resources =  SResource.dao.findTrees(roleStr.toString(), 0L,resources);//根目录id为0
				setSessionAttr("resources", resources);

			}else{
				throw new Exception("用户为配置角色，请联系管理员");
			}
			

//			render("/WEB-INF/mvcs/index/index.html");
			redirect("/main");
			
		}
	}
	
	@Clear
	public void logout() throws Exception{
		if(this.getSession() != null) this.getSession().invalidate();
		renderText("退出成功");
	}
}





