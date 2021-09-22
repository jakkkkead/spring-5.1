package org.springframework.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:application.xml");
		TestSpringService bean = context.getBean(TestSpringService.class);
		String s = bean.sayHello();
		System.out.println(s);
	}
}
