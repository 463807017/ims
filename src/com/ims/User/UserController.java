package com.ims.User;


import com.ims.common.model.Blog;
import com.ims.common.service.user.SUser;
import com.jfinal.core.Controller;

/**
 * 本 ims 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class UserController extends Controller {
	public void index() {
		setAttr("userPage", SUser.dao.paginate(getParaToInt(0, 1), 10));
		render("/WEB-INF/mvcs/user/user.html");
	}
	
	public void add() {
		render("/WEB-INF/mvcs/user/add.html");
	}
	
	public void save() {
		getModel(SUser.class).save();
		redirect("/user/index");
	}
	
	public void edit() {
		setAttr("blog", SUser.dao.findById(getParaToInt()));
	}
	
	public void update() {
		getModel(SUser.class).update();
		redirect("/user");
	}
	
	public void delete() {
		Blog.me.deleteById(getParaToInt());
		redirect("/blog");
	}
}


