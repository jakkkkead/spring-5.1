package org.springframework.aop.mytest;

public class Aspect2 {
	public void before(){
		System.out.println("Aspect2--before执行");
	}

	public void after(){
		System.out.println("Aspect2--after执行");
	}
}
