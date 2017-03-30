package com.ims.resource;


import java.util.ArrayList;
import java.util.List;

import com.ims.common.service.SResource;
import com.ims.role.SNode;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;

/**
 *用户模块
 */
public class ResourceController extends Controller {
	public void resourceJson() {
		int pid = getParaToInt("id",0);
		List<SResource> resources = SResource.dao.find("select * from s_resource r where r.parent_id=?",pid);
		List<SNode> nodes = new ArrayList<SNode>();
		
		for (int i = 0; i < resources.size(); i++) {
			SNode node = new SNode();
			SResource s = (SResource)resources.get(i);
			
			node.setId(s.getId() + "");
			node.setName(s.getName());
			node.setLevel(s.getLevel() + "");
			node.setIsParent(StringUtil.isNull(s.getUrl()));
			node.setpId(s.getParentId() + "");
			nodes.add(node);
		}
		renderJson(nodes);
	}
	
	public void index() {
		StringBuffer condition = new StringBuffer();
		SResource sResource = getModel(SResource.class);
		setAttr("resourceList", sResource);
		render("/WEB-INF/mvcs/resource/resource.html");
	}
	
	public void add() {
		render("/WEB-INF/mvcs/resource/add.html");
	}
	
	public void save() {
		getModel(SResource.class).save();
		redirect("/resource/");
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		SResource resource = SResource.dao.findById(para);
		setAttr("resource", resource);
		
		String page =  getPara("page");

		if(StringUtil.isNull(page)){
			render("/WEB-INF/mvcs/resource/add.html");
		}else{
			render("/WEB-INF/mvcs/resource/" + page + ".html");
		}

	}
	
	public void update() {
		getModel(SResource.class).update();
		redirect("/resource/");
	}
	
	public void delete() {
		int para =  getParaToInt("id");
		SResource.dao.deleteById(para);
		redirect("/resource/");
	}
	
}



