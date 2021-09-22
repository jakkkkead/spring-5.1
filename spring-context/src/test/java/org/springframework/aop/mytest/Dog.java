package org.springframework.aop.mytest;

public class Dog implements Animal {
	@Override
	public int count() {
		//int x = 1/0;
		return 2;
	}
}
