package org.springframework.context.ioctest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.mytest.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashSet;
import java.util.Set;

//测试类的名字要符合 规定，以**/*Tests.class, 等等
public class MyContestTests {
	@Test
	public void sayHello(){
		ApplicationContext context = new ClassPathXmlApplicationContext("MyTestApplication.xml",getClass());
		HelloService helloService = context.getBean("helloService",HelloService.class);
		helloService.sayHello();
	}

	@Test
	public void testSetAdd() {
		Set<String> set = new HashSet<>();
		System.out.println(set.add("2"));
		System.out.println(set.add("2"));
	}

	@Test
	public void testIocBySetter(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioctest-context.xml");
		IocBySetterBean bean = context.getBean("iocBySetterBean",IocBySetterBean.class);
		System.out.println(bean.getName());
	}

	/**
	 * 测试销毁容器
	 */
	@Test
	public void testDestroyIoc() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioctest-context.xml");

		((AbstractApplicationContext)context).close();
	}

	/**
	 * 自动装配-构造函数方式
	 */
	@Test
	public void testIocByConstruct(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:ioctest-context.xml");
		IocByConstruct bean = context.getBean("iocByConstruct",IocByConstruct.class);
		IocByConstruct bean2 = context.getBean("iocByConstruct",IocByConstruct.class);
//		System.out.println(bean.simpleBean == bean2.simpleBean);
		System.out.println(bean.getName());
		System.out.println(context.getBean(SimpleBean.class));
	}

	/**
	 * 通过byName方式进行依赖注入
	 * <bean id="iocByNameTest" class="org.springframework.context.mytest.IocByNameTest" autowire="byName">
	 * </bean>
	 * 此时无需使用property指定依赖,否则byName不生效，走默认的setter方式注入
	 */
	@Test
	public void testIocByName(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		IocByNameTest iocByNameTest = context.getBean("iocByNameTest",IocByNameTest.class);
		System.out.println(iocByNameTest);
	}

	/**
	 * 通过byType方式进行依赖注入
	 */
	@Test
	public void testIocByType(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		IocByType iocByType = context.getBean("iocByType",IocByType.class);
		System.out.println(iocByType);
	}

	/**
	 * 通过Autowired注解注入
	 */
	@Test
	public void testIocByAutowired(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		IocByAutowired iocByAutowired = (IocByAutowired) context.getBean("iocByAutowired");
		System.out.println(iocByAutowired);
	}

	/**
	 * 测试Autowired属性注入的方式
	 * 一个接口，有两个实现类，注入时选择那个？
	 *
	 */
	@Test
	public void testAutowiredType(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		IocByAutowired iocByAutowired = (IocByAutowired) context.getBean("iocByAutowired");
		System.out.println(iocByAutowired.simpleBean);
//		iocByAutowired.goodByeService.say();
	}

	/**
	 * 通过注解方式注入容器 @Component
	 */
	@Test
	public void testIocByAnno(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		IocByAnnoncation iocByAutowired = (IocByAnnoncation) context.getBean("iocByAnnoncation");
		iocByAutowired.say();
	}

	/**
	 * 测试 @Autowired循环依赖
	 */
	@Test
	public void testIocByAnnoDepend(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		IocByAnnoncation iocByAutowired = (IocByAnnoncation) context.getBean("iocByAnnoncation");
		iocByAutowired.say();
	}

}
