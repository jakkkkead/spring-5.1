package org.springframework.context.mytest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean测试类
 * @author fanfurong
 * @date 2021-09-22 15:42
 **/
@Component
public class MyFactoryBean implements FactoryBean {
	@Override
	public Object getObject() throws Exception {
		return new SimpleBean();
	}

	@Override
	public Class<?> getObjectType() {
		return SimpleBean.class;
	}
}
