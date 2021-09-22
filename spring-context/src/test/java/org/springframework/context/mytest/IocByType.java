package org.springframework.context.mytest;

public class IocByType {
	public SimpleBean simpleBean;
	public SimpleBean getSimpleBean(){
		return this.simpleBean;
	}
	public void setSimpleBean(SimpleBean simpleBean){
		this.simpleBean = simpleBean;
	}
}
