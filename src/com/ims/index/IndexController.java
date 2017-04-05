package com.ims.index;

import java.io.File;
import java.util.List;

import com.ims.User.UserInterceptor;
import com.ims.common.service.SResource;
import com.ims.common.service.SRole;
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
	
	@Clear(value=UserInterceptor.class)
	public void login() throws Exception{
		
		String loginName = getPara("name");
		String logPasswd = getPara("passwd");
		if(StringUtil.isNull(loginName)){
			throw new Exception("用户名为空");
		}
		SqlPara sqlPara = new SqlPara();
		sqlPara.setSql("select * from s_user u where u.login_name=? and u.login_passwd=?");
		sqlPara.addPara(loginName).addPara(logPasswd);
		
		SUser sUser = new SUser();
		sUser = sUser.dao().findFirst(sqlPara);
		if(sUser == null){
			throw new Exception("用户名或者密码错误");
		}else{
			this.setSessionAttr("user", sUser);
			
			//获取系统角色
			List<SRole> roles =  SRole.dao.find("select * from s_role r where r.id in (select role_id from s_roleuser where user_id=?)", sUser.getId());
			//如果其中有系统管理员
			this.setSessionAttr("roles", roles);
			setSessionAttr("admin",2);

			
			if(roles != null && roles.size() > 0){
				//查询到角色
				StringBuffer roleStr = new StringBuffer();
				boolean isAdmin = false;
				for (int i = 0; i < roles.size(); i++) {
					SRole role =  roles.get(i);
					if(role.getIsAdmin() == 1) {
						isAdmin = true;
						setSessionAttr("admin", 1);
						break;
					}
					if(i==0) roleStr.append(" role_id in(");
					if(i == roles.size() - 1)
						roleStr.append(role.getId()).append(")");
					else
						roleStr.append(role.getId()).append(",");
				}
				
				
				SResource resources = new SResource();
				resources.setId(0L);
				resources.setName("root");
				resources.setLevel(0);
				
				StringBuffer roleSql= new StringBuffer("select * from s_resource r where ");
				if(!isAdmin) roleSql.append(" r.id in (select resource_id from s_roleright where ").append(roleStr).append(" and op_flg=0) and");//非管理员
				roleSql.append(" parent_id=? order by order_id desc");//0 表示访问权限

				resources =  SResource.dao.findTrees(roleSql.toString(), 0L,resources);//根目录id为0
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
		setAttr("erroMsg", "退出成功");
		renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
	}
}





