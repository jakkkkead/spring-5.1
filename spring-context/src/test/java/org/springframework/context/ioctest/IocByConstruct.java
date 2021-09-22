package org.springframework.context.ioctest;

import org.springframework.context.annotation.Primary;
import org.springframework.context.mytest.IocByAutowired;

/**
 * 通过构造函数进行自动装配
 * @author fanfurong
 * @date 2020-12-31 09:58
 **/
public class IocByConstruct {
	public IocByConstruct() {}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SimpleBean simpleBean;

	public IocByConstruct(SimpleBean simpleBean) {
		this.simpleBean = simpleBean;
	}

	public void setSimpleBean(SimpleBean simpleBean) {
		this.simpleBean = simpleBean;
	}

	public SimpleBean getSimpleBean() {
		return simpleBean;
	}
}
