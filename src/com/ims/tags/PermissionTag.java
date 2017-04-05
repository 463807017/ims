package com.ims.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ims.common.service.SRole;

public class PermissionTag extends TagSupport {
	   //模块
	   private String module;
	   //模块中具体权限
	   private String privilege;
	 
	   public String getModule() {
	      return module;
	   }
	   public void setModule(String module) {
	      this.module = module;
	   }
	   public String getPrivilege() {
	      return privilege;
	   }
	   public void setPrivilege(String privilege) {
	      this.privilege = privilege;
	   }
	   @Override
	   public int doStartTag() throws JspException {
	      boolean result = false;
	      
	      HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	      HttpSession session = request.getSession();
	      SRole user = (SRole)session.getAttribute("roles");
	      if(user == null) return SKIP_BODY;
	     
	      //是否有权限
	      
	      return result? EVAL_BODY_INCLUDE : SKIP_BODY;//真：返回EVAL_BODY_INCLUDE（执行标签）；假：返回SKIP_BODY（跳过标签不执行）
	   }
	}
