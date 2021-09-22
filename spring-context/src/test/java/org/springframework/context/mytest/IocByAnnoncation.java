package org.springframework.context.mytest;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class IocByAnnoncation {
	public void say(){
		System.out.println("通过注解方式注入");
	}
}
