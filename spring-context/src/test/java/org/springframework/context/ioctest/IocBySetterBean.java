package org.springframework.context.ioctest;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通过setter方法注入属性
 * @author fanfurong
 * @date 2020-12-30 14:35
 **/
public class IocBySetterBean {

	private String name;

	private SimpleBean simpleBean;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSimpleBean(SimpleBean simpleBean) {
		this.simpleBean = simpleBean;
	}

	public SimpleBean getSimpleBean() {
		return simpleBean;
	}
}
