/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.web.servlet.mvc.annotation;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;

import org.junit.Test;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.mock.web.test.MockHttpServletRequest;
import org.springframework.mock.web.test.MockHttpServletResponse;
import org.springframework.mock.web.test.MockServletConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import static org.junit.Assert.*;

/**
 * @author Arjen Poutsma
 * @since 3.0
 */
public class JdkProxyControllerTests {

	private DispatcherServlet servlet;


	@Test
	public void typeLevel() throws Exception {
		initServlet(TypeLevelImpl.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		assertEquals("doIt", response.getContentAsString());
	}

	/**
	 * 测试参数解析器
	 * @throws Exception
	 */
	@Test
	public void testArgResover() throws Exception {
		initServlet(TypeLevelImpl.class);
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test/arg");
		request.addParameter("name","heiisjf");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		assertEquals("hello", response.getContentAsString());
	}

	/**
	 * 测试对象参数解析器
	 * @throws Exception
	 */
	@Test
	public void testArgBeanResover() throws Exception {
		initServlet(TypeLevelImpl.class);
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test/bean");
		request.addParameter("name","heiisjf");
		request.addParameter("age","23");
		request.addParameter("heads","jiin");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
	}

	/**
	 * 测试RequestBody
	 * @throws Exception
	 */
	@Test
	public void testRequestBody()  throws Exception{
		initServlet(TypeLevelImpl.class);
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test/requestBody");
		request.addParameter("name","heiisjf");
		request.addParameter("age","23");
		request.setContentType(MediaType.APPLICATION_JSON.toString());
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
	}

	@Test
	public void methodLevel() throws Exception {
		initServlet(MethodLevelImpl.class);
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/test");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		assertEquals("doIt", response.getContentAsString());
	}

	@Test
	public void typeAndMethodLevel() throws Exception {
		initServlet(TypeAndMethodLevelImpl.class);

		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/hotels/bookings");
		MockHttpServletResponse response = new MockHttpServletResponse();
		servlet.service(request, response);
		assertEquals("doIt", response.getContentAsString());
	}


	@SuppressWarnings("serial")
	private void initServlet(final Class<?> controllerclass) throws ServletException {
		servlet = new DispatcherServlet() {
			@Override
			protected WebApplicationContext createWebApplicationContext(@Nullable WebApplicationContext parent) {
				GenericWebApplicationContext wac = new GenericWebApplicationContext();
				wac.registerBeanDefinition("controller", new RootBeanDefinition(controllerclass));
				DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
				autoProxyCreator.setBeanFactory(wac.getBeanFactory());
				wac.getBeanFactory().addBeanPostProcessor(autoProxyCreator);
				wac.getBeanFactory().registerSingleton("advisor", new DefaultPointcutAdvisor(new SimpleTraceInterceptor(true)));
				wac.refresh();
				return wac;
			}
		};
		servlet.init(new MockServletConfig());
	}


	@Controller
	@RequestMapping("/test")
	public interface TypeLevel {

		@RequestMapping
		void doIt(Writer writer) throws IOException;

		@RequestMapping("/arg")
		void testArgument(Writer writer,String name) throws IOException;

		@RequestMapping("/bean")
		void testReloverArgu(TestBean testBean);
		@RequestMapping("/requestBody")
		void testRequestBody(@RequestBody TestBean testBean);
	}


	public static class TypeLevelImpl implements TypeLevel {

		@Override
		public void doIt(Writer writer) throws IOException {
			writer.write("doIt");
		}

		@Override
		public void testArgument(Writer writer ,String name) throws IOException{
			System.out.println(name);
			writer.write("hello");
		}

		@Override
		public void testReloverArgu(TestBean testBean) {
			System.out.println(testBean.getAge());
			System.out.println(testBean.getName());
		}

		@Override
		public void testRequestBody(TestBean testBean) {
			System.out.println(testBean.getAge());
		}
	}


	@Controller
	public interface MethodLevel {

		@RequestMapping("/test")
		void doIt(Writer writer) throws IOException;
	}


	public static class MethodLevelImpl implements MethodLevel {

		@Override
		public void doIt(Writer writer) throws IOException {
			writer.write("doIt");
		}
	}


	@Controller
	@RequestMapping("/hotels")
	public interface TypeAndMethodLevel {

		@RequestMapping("/bookings")
		void doIt(Writer writer) throws IOException;
	}


	public static class TypeAndMethodLevelImpl implements TypeAndMethodLevel {

		@Override
		public void doIt(Writer writer) throws IOException {
			writer.write("doIt");
		}
	}

}
