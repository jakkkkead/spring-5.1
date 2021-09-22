package org.springframework.context.mytest;



public class BeanPostProcessorTest {
	private String des;

	/**
	 * spring第3种依赖注入方式，通过构造函数注入
	 * @param des
	 */
	public BeanPostProcessorTest(String des){
		this.des = des;
	}
	public BeanPostProcessorTest(){}
	public String getDes(){
		return des;
	}
}
