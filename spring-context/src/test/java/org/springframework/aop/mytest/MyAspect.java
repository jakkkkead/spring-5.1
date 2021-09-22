package org.springframework.aop.mytest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.expression.spel.SpelCompilationCoverageTests;

/**
 * 单个切面执行顺序：
 * before
 * around
 * (afterThrowing)
 * afterReturning(如果异常，afterReturning无法执行)
 * after
 *
 */
public class MyAspect {
	public void before(){
		System.out.println("开始执行切面的before方法");
	}
	public void after(){
		System.out.println("开始执行切面的after方法");
	}
	public Object around(ProceedingJoinPoint point) throws Throwable {
		System.out.println("开始执行切面的around方法--之前");
		//在这一步调用afterThrowingAdvice,在其中会调用目标方法
		Object proceed = point.proceed();
		System.out.println("开始执行切面的around方法--之后");
		return proceed;
	}

	/**
	 * 在目标方法执行成功后，调用通知
	 */
	public void afterReturn(){
		System.out.println("开始执行切面的 afterReturn方法");
	}

	/**
	 *在目标方法抛出异常后，执行通知
	 * 连接点的 return 之后调用抛出建议
	 */
	public void afterThrowing(){
		System.out.println("开始执行切面的 afterThrowing方法");
	}
}
