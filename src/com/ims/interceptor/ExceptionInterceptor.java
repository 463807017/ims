package com.ims.interceptor;

import java.lang.reflect.InvocationTargetException;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ExceptionInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		try {
			inv.invoke();
		} catch (Exception e) {System.out.println(inv.getController().hashCode());
		e.printStackTrace();
		
		Exception e1 = null;
		if(e.getCause() instanceof InvocationTargetException){
			InvocationTargetException e2 = (InvocationTargetException)e.getCause();
			e1 = (Exception) e2.getTargetException();
		}else if(e.getCause() == null){
			e1 = e;
		}
		
			System.out.println(e1.getMessage());
			inv.getController().setAttr("erroMsg", e1.getMessage());
			inv.getController().renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
		}
	}

}
