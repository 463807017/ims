package com.ims.sales;

import com.ims.common.service.SalesOut;
import com.ims.util.StringUtil;
import com.ims.util.TimeUtil;
import com.ims.util.dic.SDicHelper;
import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

/**
 *出库模块
 */
public class SaleController extends Controller {
	public void index() {
		StringBuffer condition = new StringBuffer();
		SalesOut salesOut = getModel(SalesOut.class);
		setAttr("salesOut", salesOut);
		
		if(salesOut != null){
			condition.append(" where 1=1 ");
			if(!StringUtil.isNull(salesOut.getInputDate())){
				condition.append(" and input_date like '%" + salesOut.getInputDate() + "%'");
			}
			if(!StringUtil.isNull(salesOut.getDriver())){
				condition.append(" and driver like '%" + salesOut.getDriver() + "%'");
			}
			if(!StringUtil.isNull(salesOut.getPrdName())){
				condition.append(" and prd_name like '%" + salesOut.getPrdName() + "%'");
			}
			if(salesOut.getCarType() != null){
				condition.append(" and car_type=" + salesOut.getCarType());
			}
			if(salesOut.getBuyerName() != null){
				condition.append(" and buyer_name  like '").append(salesOut.getCarType()).append("'");
			}
		}
		
		setAttr("page", SalesOut.dao.paginate(getParaToInt(0, 1), 10,condition.toString()));
		render("/WEB-INF/mvcs/saleout/saleout.html");
	}
	
	public void add() {
		SalesOut salesOut = getModel(SalesOut.class);
		setAttr("salesOut",salesOut);
		render("/WEB-INF/mvcs/saleout/saleoutadd.html");
	}
	public void tab() {
		render("/WEB-INF/mvcs/saleout/saleouttab.html");
	}
	
	public void save() {
		SalesOut out = getModel(SalesOut.class);
		out.setInputDate(StringUtil.getDate(null));
		out.setInputTime(StringUtil.getTime(null));
		
		String carNo = out.getCarNo();
		out.setCarNo(SDicHelper.translate("CAR", carNo));
		
		String driver = out.getDriver();
		out.setDriver(SDicHelper.translate("DRIVER", driver));
		
		String prdName = out.getPrdName();
		out.setPrdName(SDicHelper.translate("PRD", prdName));
		
		String buyerName = out.getBuyerName();
		out.setBuyerName(SDicHelper.translate("BUYER", buyerName));
		
		out.save();
		redirect("/sale/?salesOut.car_type=" + out.getCarType());
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		SalesOut sale = SalesOut.dao.findById(para);
		setAttr("salesOut", sale);
		
		String inputTime = sale.getInputDate() + " " + sale.getInputTime();
		if((Integer)getSessionAttr("admin") != 1){
			if(TimeUtil.subHours(inputTime, Integer.parseInt(PropKit.get("updateHous"))) > 0){
				setAttr("erroMsg", "录入时间超过" + PropKit.get("updateHous") + "小时，请联系管理员修改");
				renderJsp("/WEB-INF/mvcs/erroMsg.jsp");
				return;
			}		
		}
		
		String page =  getPara("page");

		if(StringUtil.isNull(page)){
			render("/WEB-INF/mvcs/saleout/saleoutadd.html");
		}else{
			render("/WEB-INF/mvcs/saleout/" + page + ".html");
		}

	}
	
	public void update() {
		SalesOut out = getModel(SalesOut.class);
		
		String carNo = out.getCarNo();
		out.setCarNo(SDicHelper.translate("CAR", carNo));
		
		String driver = out.getDriver();
		out.setDriver(SDicHelper.translate("DRIVER", driver));
		
		String prdName = out.getPrdName();
		out.setPrdName(SDicHelper.translate("PRD", prdName));
		
		String buyerName = out.getBuyerName();
		out.setBuyerName(SDicHelper.translate("BUYER", buyerName));
		
		out.update();

		redirect("/sale/?salesOut.car_type=" + out.getCarType());

	}
	
	public void delete() {
		SalesOut out = getModel(SalesOut.class);
		SalesOut.dao.deleteById(out.getId());
		redirect("/sale/?salesOut.car_type=" + out.getCarType());
	}
	
	
}


