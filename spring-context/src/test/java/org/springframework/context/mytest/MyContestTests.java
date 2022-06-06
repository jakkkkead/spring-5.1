package org.springframework.context.mytest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.mytest.HelloService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//测试类的名字要符合 规定，以**/*Tests.class, 等等
public class MyContestTests {
	@Test
	public void sayHello(){
		ApplicationContext context = new ClassPathXmlApplicationContext("MyTestApplication.xml",getClass());
		HelloService helloService = context.getBean("helloService",HelloService.class);
		helloService.sayHello();
	}

	@Test
	public void testClassPathXml(){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		HelloService helloService = context.getBean("helloService",HelloService.class);
		helloService.sayHello();
		System.out.println(helloService.getName());
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

	@Test
	public void testFactoryBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:classpath-context.xml");
		Object myFactoryBean = context.getBean("myFactoryBean");
		Object bean = context.getBean("&myFactoryBean");
	}

}
