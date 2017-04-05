package com.ims.sdic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ims.common.service.BuyIn;
import com.ims.common.service.SDic;
import com.ims.util.StringUtil;
import com.ims.util.dic.SDicHelper;
import com.jfinal.core.Controller;

/**
 *用户模块
 */
public class SDicController extends Controller {
	public void index() {
		StringBuffer condition = new StringBuffer();
		SDic sDic = getModel(SDic.class);
		setAttr("sDic", sDic);
		if(sDic != null){
			condition.append(" where 1=1 ");
			if(!StringUtil.isNull(sDic.getCh())){
				condition.append(" and ch like '%").append(sDic.getCh()).append("%'");
			}
			
			if(!StringUtil.isNull(sDic.getEn())){
				condition.append(" and en like '%").append(sDic.getEn()).append("%'");
			}
			
			if(!StringUtil.isNull(sDic.getOp())){
				condition.append(" and op='").append(sDic.getOp()).append("'");
			}
			
		}
		setAttr("page", SDic.dao.paginate(getParaToInt(0, 1), 5,condition.toString()));
		render("/WEB-INF/mvcs/sdic/sdic.html");
	}
	
	public void dicJson() {
		String op = getPara("op");
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> dicMap = SDicHelper.getOneDic(op);
		if(dicMap == null){
			renderJson("[]");
			return;
		}
		
		if(SDicHelper.getOneDic("OP_TYPE").containsKey(op)){
			ArrayList<String> names = new ArrayList<String>();
			for (String temp : dicMap.keySet()) {
				HashMap<String,String> m = new HashMap<String, String>();
				names.add(dicMap.get(temp));
			}
			renderJson(names);
			return;
		}else{
			for (String temp : dicMap.keySet()) {
				HashMap<String,String> m = new HashMap<String, String>();
				m.put("id", temp);
				m.put("text", dicMap.get(temp));
				list.add(m);
			}
		}
		
		if(list != null && list.size() > 0)
			renderJson(list);
		else
			renderJson("[]");
	}
	
	public void add() {
		render("/WEB-INF/mvcs/sdic/add.html");
	}
	
	public void save() {
		SDic dic = getModel(SDic.class);
		SDicHelper.addOrUpd(dic.getOp(), dic.getEn(), dic.getCh());
		dic.save();
		
		redirect("/sdic?sDic.op=" + dic.getOp());
	}
	
	public void edit() {
		SDic dic = getModel(SDic.class);
		dic = dic.dao.findById(dic.getEn(),dic.getOp());
		setAttr("sDic", dic );
		render("/WEB-INF/mvcs/sdic/add.html");
	}
	
	public void update() {
		SDic dic = getModel(SDic.class);
		SDicHelper.addOrUpd(dic.getOp(), dic.getEn(), dic.getCh());
		dic.update();
		redirect("/sdic?sDic.op=" + dic.getOp());
	}
	
	public void delete() {
		SDic dic = getModel(SDic.class);
		dic.dao.deleteById(dic.getEn(),dic.getOp());
		redirect("/sdic?sDic.op=" + dic.getOp());
	}
	
	
}


