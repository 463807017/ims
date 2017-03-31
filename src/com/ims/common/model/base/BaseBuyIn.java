package com.ims.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseBuyIn<M extends BaseBuyIn<M>> extends Model<M> implements IBean {
	
	public M setBuyType(java.lang.Integer buyType) {
		set("buy_type", buyType);
		return (M)this;
	}

	public java.lang.Integer getBuyType() {
		return get("buy_type");
	}
	
	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public M setInputDate(java.lang.String inputDate) {
		set("input_date", inputDate);
		return (M)this;
	}

	public java.lang.String getInputDate() {
		return get("input_date");
	}

	public M setInputTime(java.lang.String inputTime) {
		set("input_time", inputTime);
		return (M)this;
	}

	public java.lang.String getInputTime() {
		return get("input_time");
	}

	public M setCarNo(java.lang.String carNo) {
		set("car_no", carNo);
		return (M)this;
	}

	public java.lang.String getCarNo() {
		return get("car_no");
	}

	public M setMaoHeavy(java.lang.Long maoHeavy) {
		set("mao_heavy", maoHeavy);
		return (M)this;
	}

	public java.lang.Long getMaoHeavy() {
		return get("mao_heavy");
	}

	public M setPiHeavy(java.lang.Long piHeavy) {
		set("pi_heavy", piHeavy);
		return (M)this;
	}

	public java.lang.Long getPiHeavy() {
		return get("pi_heavy");
	}

	public M setJinHeavy(java.lang.Long jinHeavy) {
		set("jin_heavy", jinHeavy);
		return (M)this;
	}

	public java.lang.Long getJinHeavy() {
		return get("jin_heavy");
	}

	public M setSalerName(java.lang.String salerName) {
		set("saler_name", salerName);
		return (M)this;
	}

	public java.lang.String getSalerName() {
		return get("saler_name");
	}

}