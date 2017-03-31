package com.ims.buy;

import com.ims.common.service.BuyIn;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;

/**
 *用户模块
 */
public class BuyController extends Controller {
	
	public void tab() {
		render("/WEB-INF/mvcs/buyin/buyinttab.html");
	}
	
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
			if(buyIn.getBuyType() != null){
				condition.append(" and buy_type =" + buyIn.getBuyType());
			}
		}
		setAttr("page", BuyIn.dao.paginate(getParaToInt(0, 1), 5,condition.toString()));
		render("/WEB-INF/mvcs/buyin/buyin.html");
	}
	
	public void add() {
		BuyIn buyIn = getModel(BuyIn.class);
		setAttr("buyIn",buyIn);
		render("/WEB-INF/mvcs/buyin/buyinadd.html");
	}
	
	public void save() {
		BuyIn in = getModel(BuyIn.class);
		in.setInputDate(StringUtil.getDate(null));
		in.setInputTime(StringUtil.getTime(null));
		in.save();
		redirect("/buy/?buyIn.buy_type=" + in.getBuyType());
	}
	
	public void edit() {
		BuyIn in = getModel(BuyIn.class);
		BuyIn buyin = BuyIn.dao.findById(in.getId());
		setAttr("buyIn", buyin);
		
		String page =  getPara("page");

		if(StringUtil.isNull(page)){
			render("/WEB-INF/mvcs/buyin/buyinadd.html");
		}else{
			render("/WEB-INF/mvcs/buyin/" + page + ".html");
		}

	}
	
	public void update() {
		BuyIn in = getModel(BuyIn.class);
		in.update();
		redirect("/buy/?buyIn.buy_type=" + in.getBuyType());
	}
	
	public void delete() {
		BuyIn in = getModel(BuyIn.class);
		BuyIn.dao.deleteById(in.getId());
		redirect("/buyin/");
		redirect("/buy/?buyIn.buy_type=" + in.getBuyType());
	}
	
	
}


