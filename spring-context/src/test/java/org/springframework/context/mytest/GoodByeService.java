package org.springframework.context.mytest;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

@Priority(1)
@Primary//此时注解无效，需要配合@component使用
public class GoodByeService implements Service {
	@Override
	public void say() {
		System.out.println("goodBye!");
	}
}
