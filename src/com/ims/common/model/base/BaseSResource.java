package com.ims.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSResource<M extends BaseSResource<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Long getId() {
		return get("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}

	public java.lang.String getName() {
		return get("name");
	}

	public M setUrl(java.lang.String url) {
		set("url", url);
		return (M)this;
	}

	public java.lang.String getUrl() {
		return get("url");
	}

	public M setParentId(java.lang.Long parentId) {
		set("parent_id", parentId);
		return (M)this;
	}

	public java.lang.Long getParentId() {
		return get("parent_id");
	}

	public M setLevel(java.lang.Integer level) {
		set("level", level);
		return (M)this;
	}

	public java.lang.Integer getLevel() {
		return get("level");
	}

	public M setOrderId(java.lang.Integer orderId) {
		set("order_id", orderId);
		return (M)this;
	}

	public java.lang.Integer getOrderId() {
		return get("order_id");
	}

}
