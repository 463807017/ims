package com.ims.common;

import com.ims.User.UserController;
import com.ims.User.UserInterceptor;
import com.ims.blog.BlogController;
import com.ims.common.model._MappingKit;
import com.ims.index.IndexController;
import com.ims.interceptor.ExceptionInterceptor;
import com.ims.role.RoleController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.TxByMethodRegex;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * 本 ims 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * API引导式配置
 */
public class ImsConfig extends JFinalConfig {
	
	
	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用PropKit.get(...)获取值
		PropKit.use("ims.properties");
		me.setDevMode(PropKit.getBoolean("devMode", false));
	}
	
	/**
	 * 配置路由
	 */
	public void configRoute(Routes me) {
		me.add("/", IndexController.class, "/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/blog", BlogController.class); // 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/user", UserController.class); 
		me.add("/role", RoleController.class); 
	}
	
	public void configEngine(Engine me) {
		me.addSharedFunction("/WEB-INF/mvcs/common/_layout.html");
		me.addSharedFunction("/WEB-INF/mvcs/common/_paginate.html");
	}
	
	public static DruidPlugin createDruidPlugin() {
		return new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		DruidPlugin druidPlugin = createDruidPlugin();
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		// 所有映射在 MappingKit 中自动化搞定
		_MappingKit.mapping(arp);
		me.add(arp);
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		//登录检查
		me.add(new UserInterceptor());
		//事务拦截器
		me.add(new TxByMethodRegex("(.*save.*|.*update.*)"));
		//错误信息页面
		me.add(new ExceptionInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		
	}
}
