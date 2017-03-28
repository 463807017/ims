package com.ims.common;

import com.ims.common.service.user.Blog;
import com.ims.common.service.user.SRole;
import com.ims.common.service.user.SUser;
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
	}
}

