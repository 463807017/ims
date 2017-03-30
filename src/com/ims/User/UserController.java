package com.ims.User;


import com.ims.common.service.SUser;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;

/**
 *用户模块
 */
public class UserController extends Controller {
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
		getModel(SUser.class).save();
		redirect("/user/");
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		SUser user = SUser.dao.findById(para);
		setAttr("user", user);
		
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
			renderText("旧密码输入错误");
			return;
		}
		String new_login_passwd = getPara("new_login_passwd");
		user.setLoginPasswd(new_login_passwd);
		user.update();
		renderText("修改成功");
		
	}
	
}


