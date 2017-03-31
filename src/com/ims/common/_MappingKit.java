package com.ims.common;

import com.ims.common.service.Blog;
import com.ims.common.service.BuyIn;
import com.ims.common.service.SDic;
import com.ims.common.service.SResource;
import com.ims.common.service.SRole;
import com.ims.common.service.SRoleright;
import com.ims.common.service.SRoleuser;
import com.ims.common.service.SUser;
import com.ims.common.service.SalesOut;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {

	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("s_role", "id", SRole.class);
		arp.addMapping("s_user", "id", SUser.class);
		arp.addMapping("s_roleuser", "user_id,role_id", SRoleuser.class);
		arp.addMapping("s_roleright", "resource_id,role_id", SRoleright.class);
		arp.addMapping("s_resource", "resource_id,role_id", SResource.class);
		arp.addMapping("s_dic", "en,op", SDic.class);
		arp.addMapping("sales_out", "id", SalesOut.class);
		arp.addMapping("buy_in", "id", BuyIn.class);
	}
}

