package com.ims.User;


import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 *是否登陆校验
 */
public class UserInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		Controller controller = inv.getController();
		Object user = controller.getSessionAttr("user");
		if(user == null){
			inv.getController().render("/WEB-INF/mvcs/login/login.html");;
		}else{
			inv.invoke();
		}
	}
}
