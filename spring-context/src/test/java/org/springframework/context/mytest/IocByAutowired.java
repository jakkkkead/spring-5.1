package org.springframework.context.mytest;

import org.springframework.beans.factory.annotation.Autowired;

public class IocByAutowired {
	@Autowired
	public SimpleBean simpleBean;
	/**
	 * 使用Autowired注入属性，spring使用byType方式注入，即根据属性字段的Class类型（Service），匹配
	 * 容器种的所有bean，如果匹配到了多个bean，则需要根据 @primary,@Priority注解按顺序判断，决定取谁；
	 * 如果没有上述注解，则根据字段名称如 wel 与容器种保存的bean定义名称 匹配（equals),匹配成功取出即可。
	 * 否则报错
	 * 注意：使用@Primary注解，但是bean确使用xml方式注入，@primary无效，因为bean的 primary属性为空。
	 * 1.@Primary需配合@Component等注解一起使用
	 * 否则就使用：
	 * 	<bean id="goodByeService" class="org.springframework.context.mytest.GoodByeService" primary="true"></bean>
	 * 	2. @Priority(1) 只能通过注解的方式使用，xml没法配置。值越小，优先级越高
	 */
	//@Autowired
//	public Service goodByeService;
	private String old;


	public void setOld(String old) {
		this.old = old;
	}
}
