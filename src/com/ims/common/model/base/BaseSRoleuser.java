package com.ims.common.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseSRoleuser<M extends BaseSRoleuser<M>> extends Model<M> implements IBean {

	public M setRoleId(java.lang.Long roleId) {
		set("role_id", roleId);
		return (M)this;
	}

	public java.lang.Long getRoleId() {
		return get("role_id");
	}

	public M setUserId(java.lang.Long userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.Long getUserId() {
		return get("user_id");
	}

}
