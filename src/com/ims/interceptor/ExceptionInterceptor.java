package com.ims.interceptor;

import java.lang.reflect.InvocationTargetException;

import com.ims.util.StringUtil;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class ExceptionInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		try {
			inv.invoke();
		} catch (Exception e) {System.out.println(inv.getController().hashCode());
		e.printStackTrace();
		
		Throwable e1 = null;
		if(e.getCause() instanceof InvocationTargetException){
			InvocationTargetException e2 = (InvocationTargetException)e.getCause();
			e1 = (Exception) e2.getTargetException();
		}else if(e.getCause() == null){
			e1 = e;
		}else{
			e1 = e.getCause();
		}
		
			System.out.println(e1.getMessage());
			String message = e1.getMessage();
			if(!StringUtil.isNull(message)){
				if(message.contains("for key 'PRIMARY'")){
					message = "存在相同记录";
				}
			}
			inv.getController().setAttr("erroMsg", message);
			inv.getController().renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
		}
	}

}
