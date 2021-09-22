package org.springframework.context.mytest;

import org.springframework.beans.factory.annotation.Value;

/**
 *依赖注入是一个过程，主要通过setter和构造方法以及一些变体的方式完成把对象依赖、或者填充上的这个过程叫做依赖注入，
 * 不管手动装配还是自动装配都有这个过程
 * 细分：
 * 手动装配：setter
 * 自动装配模型是一种完成自动装配依赖的手段体现，
 * 自动装配模型：通过一个标志位表示 autowireMode
 *   0：表示非自动装配   ---》手动装配
 *	 1: byName,通过id或name在工厂种匹配 bean进行依赖注入 --》setter - -> method.invoke
 *	 2：byType,通过 class 在工厂种匹配bean 进行依赖注入 -->setter - -> method.invoke
 *	 4：construct,构造函数注入， -- > newInstance()
 * 变体：目前猜测：@Autowired,@Value, filed.set()
 */
public class HelloService {
	/**
	 * spring 第一种属性注入方式，利用 set方法注入，需要配合配置文件：
	 * <bean id="helloService" class="org.springframework.context.mytest.HelloService">
	 * 		<property name="name" value="夏梦"></property>
	 * 	</bean>
	 * 	手动设置属性: Method.invoke()
	 * 	注释注入在 XML 注入之前执行。因此，XML configuration 会覆盖通过这两种方法连接的 properties 的 annotations
	 */
	@Value("注释值")
	private String name;
	public void sayHello(){
		System.out.println("hello spring-context");
	}

//	public void setBeanPostProcessorTest(BeanPostProcessorTest beanPostProcessorTest){
//		this.beanPostProcessorTest = beanPostProcessorTest;
//	}
	public void setName(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
