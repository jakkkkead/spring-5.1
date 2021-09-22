package org.springframework.context.ioctest;

import javax.annotation.PreDestroy;

/**
 * 测试单元类
 * @author fanfurong
 * @date 2020-12-30 15:54
 **/
public class SimpleBean {
	private String name;

	@PreDestroy
	public void destroy() {
		System.out.println("simpleBean 开始销毁");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SimpleBean(){};

	public SimpleBean(String name) {
		this.name = name;
	}
}
