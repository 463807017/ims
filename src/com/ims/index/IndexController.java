package com.ims.index;

import com.ims.common.service.user.SUser;
import com.ims.util.StringUtil;
import com.jfinal.aop.Clear;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * IndexController
 */
public class IndexController extends Controller {
	public void index() {
		render("/WEB-INF/mvcs/login/login.html");
	}
	
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
			render("/WEB-INF/mvcs/index/index.html");

		}
	}
}





