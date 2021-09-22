package org.springframework.context.mytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class SimpleBean {
	private String age;

	public IocByAutowired iocByAutowired;

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public IocByAutowired getIocByAutowired() {
		return iocByAutowired;
	}

	public void setIocByAutowired(IocByAutowired iocByAutowired) {
		this.iocByAutowired = iocByAutowired;
	}
}
