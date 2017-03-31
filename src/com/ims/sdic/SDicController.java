package com.ims.sdic;


import java.util.ArrayList;
import java.util.List;

import com.ims.common.service.BuyIn;
import com.ims.common.service.SDic;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;

/**
 *用户模块
 */
public class SDicController extends Controller {
	public void index() {
		StringBuffer condition = new StringBuffer();
		BuyIn buyIn = getModel(BuyIn.class);
		setAttr("buyIn", buyIn);
		if(buyIn != null){
			condition.append(" where 1=1 ");
			if(!StringUtil.isNull(buyIn.getInputDate())){
				condition.append(" and input_date like '%" + buyIn.getInputDate() + "%'");
			}
			if(!StringUtil.isNull(buyIn.getCarNo())){
				condition.append(" and car_no like '%" + buyIn.getCarNo() + "%'");
			}
		}
		setAttr("page", BuyIn.dao.paginate(getParaToInt(0, 1), 5,condition.toString()));
		render("/WEB-INF/mvcs/buyin/buyin.html");
	}
	
	public void dicJson() {
		String op = getPara("op");
		List<SDic> sdic = SDic.dao.find("select ch from s_dic where op=?", op);
		List<String> list = new ArrayList<String>(sdic==null?0:sdic.size());
		for (SDic d : sdic) {
			list.add(d.getCh());
		}
		renderJson(list);
	}
	
	public void add() {
		render("/WEB-INF/mvcs/buyin/buyinadd.html");
	}
	
	public void save() {
		BuyIn in = getModel(BuyIn.class);
		in.setInputDate(StringUtil.getDate(null));
		in.setInputTime(StringUtil.getTime(null));
		in.save();
		redirect("/buy/");
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		BuyIn buyin = BuyIn.dao.findById(para);
		setAttr("buyin", buyin);
		
		String page =  getPara("page");

		if(StringUtil.isNull(page)){
			render("/WEB-INF/mvcs/buyin/add.html");
		}else{
			render("/WEB-INF/mvcs/buyin/" + page + ".html");
		}

	}
	
	public void update() {
		getModel(BuyIn.class).update();
		redirect("/buyin/");
	}
	
	public void delete() {
		int para =  getParaToInt("id");
		BuyIn.dao.deleteById(para);
		redirect("/buyin/");
	}
	
	
}


