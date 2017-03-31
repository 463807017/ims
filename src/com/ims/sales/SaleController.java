package com.ims.sales;


import com.ims.common.service.SalesOut;
import com.ims.util.StringUtil;
import com.jfinal.core.Controller;

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
		}
		
		setAttr("page", SalesOut.dao.paginate(getParaToInt(0, 1), 5,condition.toString()));
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
		out.save();
		redirect("/sale/?salesOut.car_type=" + out.getCarType());
	}
	
	public void edit() {
		int para =  getParaToInt("id");
		SalesOut sale = SalesOut.dao.findById(para);
		setAttr("salesOut", sale);
		
		String page =  getPara("page");

		if(StringUtil.isNull(page)){
			render("/WEB-INF/mvcs/saleout/saleoutadd.html");
		}else{
			render("/WEB-INF/mvcs/saleout/" + page + ".html");
		}

	}
	
	public void update() {
		SalesOut out = getModel(SalesOut.class);
		getModel(SalesOut.class).update();
		redirect("/sale/?salesOut.car_type=" + out.getCarType());

	}
	
	public void delete() {
		SalesOut out = getModel(SalesOut.class);
		SalesOut.dao.deleteById(out.getId());
		redirect("/sale/?salesOut.car_type=" + out.getCarType());
	}
	
	
}


