package com.demo.bank.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {
	
	@Pointcut("execution(* com.demo.bank.controller.*.*(..))")
	public void controllerAspect() {}
	
	@Pointcut("execution(* com.demo.bank.repository.*.*(..))")
	public void repositoryAspect() {}
	
	@Pointcut("execution(* com.demo.bank.service.*.*(..))")
	public void serviceAspect() {}
	
	
}
