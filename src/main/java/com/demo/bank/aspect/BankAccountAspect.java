package com.demo.bank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class BankAccountAspect {
	
	@Before("com.demo.bank.aspect.CommonJoinPointConfig.controllerAspect()")
	public void beforeController(JoinPoint joinPoint) {
		log.info("Executing method ::{}", joinPoint);
	}
	
	@Before("com.demo.bank.aspect.CommonJoinPointConfig.serviceAspect()")
	public void beforeService(JoinPoint joinPoint) {
		log.info("Executing method ::{}", joinPoint);
	}
	
	@Before("com.demo.bank.aspect.CommonJoinPointConfig.repositoryAspect()")
	public void beforeRepsoitory(JoinPoint joinPoint) {
		log.info("Executing method ::{}", joinPoint);
	}
	
	@After("com.demo.bank.aspect.CommonJoinPointConfig.controllerAspect()")
	public void afterController(JoinPoint joinPoint) {
		log.info("After execution of ::{}", joinPoint);
	}
	
	@After("com.demo.bank.aspect.CommonJoinPointConfig.serviceAspect()")
	public void afterService(JoinPoint joinPoint) {
		log.info("After execution of ::{}", joinPoint);
	}
	
	@After("com.demo.bank.aspect.CommonJoinPointConfig.repositoryAspect()")
	public void afterRepository(JoinPoint joinPoint) {
		log.info("After execution of::{}", joinPoint);
	}

}
