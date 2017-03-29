package com.ims.User;


import com.ims.common.service.user.SUser;
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
		render("/WEB-INF/mvcs/user/add.html");
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
}


