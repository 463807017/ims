package com.ims.User;


import java.util.Date;

import com.ims.common.service.SRole;
import com.ims.common.service.SRoleuser;
import com.ims.common.service.SUser;
import com.ims.util.StringUtil;
import com.ims.util.TimeUtil;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 *用户模块
 */
public class UserController extends Controller {
	
	public void hasLoingName() {
		String loginName = getPara("login_name");
		long count = Db.queryLong("select count(1) from s_user where login_name=?",loginName);
		renderJson("{\"flag\":" + count + "}");
	}
	
	public void index() {
		StringBuffer condition = new StringBuffer();
		SUser sUser = getModel(SUser.class);
		setAttr("sUser", sUser);
		if(sUser != null){
			condition.append(" where 1=1 ");
			if(!StringUtil.isNull(sUser.getName())){
				condition.append(" and name like '%" + sUser.getName() + "%'");
			}
			if(!StringUtil.isNull(sUser.getLoginName())){
				condition.append(" and login_name like '%" + sUser.getLoginName() + "%'");
			}
		}
		setAttr("userPage", SUser.dao.paginate(getParaToInt(0, 1), 5,condition.toString()));
		render("/WEB-INF/mvcs/user/user.html");
	}
	
	public void add() {
		render("/WEB-INF/mvcs/user/add.html");
	}
	
	public void save() {
		SUser user = getModel(SUser.class);
		Date date = new Date();
		user.setInputTime(StringUtil.getDate(date) + " " + StringUtil.getTime(date));
		user.save();
		redirect("/user/");
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		SUser user = SUser.dao.findById(para);
		setAttr("sUser", user);
		
		String page =  getPara("page");

		if(StringUtil.isNull(page)){
			render("/WEB-INF/mvcs/user/add.html");
		}else{
			render("/WEB-INF/mvcs/user/" + page + ".html");
		}

	}
	
	public void update() {
		getModel(SUser.class).update();
		redirect("/user/");
	}
	
	public void delete() {
		int para =  getParaToInt("id");
		SUser.dao.deleteById(para);
		redirect("/user/");
	}
	
	public void updatePasswd() throws Exception{
		int id = getParaToInt("id");
		SUser user = SUser.dao.findById(id);
		String loginPasswd = user.getLoginPasswd();
		String old_login_passwd = getPara("old_login_passwd");
		if(StringUtil.isNull(old_login_passwd) || !old_login_passwd.equals(loginPasswd)){
			setAttr("erroMsg", "旧密码输入错误");
			renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
			return;
		}
		String new_login_passwd = getPara("new_login_passwd");
		user.setLoginPasswd(new_login_passwd);
		user.update();
		
		setAttr("erroMsg", "修改成功");
		renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
		
	}
	
	
	public void queryRole() throws Exception{
		int id = getParaToInt("id");
		SUser user = SUser.dao.findById(id);
		setAttr("u",user);
		
//		String sql = "select r.id,r.name,r.descption from s_roleuser ru,s_role r where r.id=ru.role_id and ru.user_id=?";
		Page<SRole> page = SRole.dao.paginateRoles(getParaToInt(0, 1), 5, String.valueOf(id));
		setAttr("page",page);
		render("/WEB-INF/mvcs/user/userrole.html");

	}
	
	public void addRolePage(){
		
		int para =  getParaToInt("id");
		SUser user = SUser.dao.findById(para);
		setAttr("user", user);
		
		render("/WEB-INF/mvcs/user/addrole.html");

	}
	
	public void saveAddRole(){
		String role_ids = getPara("role_ids");
		if(StringUtil.isNull(role_ids)){
			setAttr("erroMsg", "请选择角色");
			renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
			return;
		}
		SUser user = getModel(SUser.class);
		String[] roles = role_ids.split(",");
		for (int i = 0; i < roles.length; i++) {
			SRoleuser ru = new SRoleuser();
			ru.setRoleId(Long.parseLong(roles[i]));
			ru.setUserId(user.getId());
			try {
				ru.save();
			} catch (Exception e) {
			}
		}
		redirect("/user/queryRole?id=" + user.getId());
	}
	
	public void deleteRole(){
		long userId = getParaToLong("user_id");
		long roleId = getParaToLong("role_id");
		String sql = "delete from s_roleuser where role_id=? and user_id=?";
		Db.update(sql,roleId,userId);
		redirect("/user/queryRole?id=" + userId);
	}
	
}


