package org.springframework.context.mytest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.expression.spel.SpelCompilationCoverageTests;

public class BeanFactoryPostProcessorTest implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("开始调用BeanFactoryPostProcessor的postProcessBeanFactory方法");
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String name : beanDefinitionNames){
			if ("helloService".equals(name)){
				System.out.println("找到helloService,开始修改属性");
				BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
				//会覆盖同名的属性
				beanDefinition.getPropertyValues().add("name","卡萨");
				break;
			}
		}
		System.out.println("BeanFactoryPostProcessor的postProcessBeanFactory方法调用结束");

	}
}
