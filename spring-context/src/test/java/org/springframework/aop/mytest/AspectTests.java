package org.springframework.aop.mytest;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectTests {
	/**
	 * 测试aop流程，cglib代理
	 */
	@Test
	public void testAspectScan(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:MyAopTests.xml");
		Target target = (Target)context.getBean("target");
		target.doSomething();
	}

	/**
	 * 测试jdk动态代理
	 */
	@Test
	public void testJdkDynamicAop(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:MyAopTests.xml");
		//Dog dog = context.getBean("dog",Dog.class);
		//由于dog是代理对象，不能直接传入Dog.class.且由于使用jdk代理，只能代理接口，所以强制转换的类型
		//必须是接口
		Animal dog = (Animal) context.getBean("dog");
		dog.count();
	}
}
