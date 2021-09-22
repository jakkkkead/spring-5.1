package org.springframework.context.mytest;

import org.springframework.context.annotation.Primary;

import javax.annotation.Priority;

@Priority(value = 0)
public class WelcomeService implements Service {
	@Override
	public void say() {
		System.out.println("welcome");
	}
}
