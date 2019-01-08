package com.cg.app.aspect;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

	private Logger logger = Logger.getLogger(ValidationAspect.class.getName());

	
	  
	@Before("execution(* com.cg.app.model.Calculator.*(..))")
	public void log1() {
		logger.info("Before - logging the method");
	}

	@After("execution(* com.cg.app.model.Calculator.*(..))")
	public void log2() {
		logger.info("After - logging the method");
	}

	@Around("execution(* com.cg.app.model.Calculator.*(..))")
	public void log3(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Inside log 3");
		logger.info("Function name is : " + pjp.getSignature());
		logger.info("Parameters are: ");
		Object[] params = pjp.getArgs();
		for (int i = 0; i < params.length; i++)
			logger.info("Parameter value at index " + i + " is " + params[i]);
		if((Integer)params[0] != 0 && (Integer)params[1] != 0) {
			pjp.proceed();
			logger.info("Method executed successfully.");
		} else {
			logger.info("Numbers should not be zero.");
		}
		logger.info("After proceed in log 3");
	}
	
	
	@AfterReturning(pointcut = "execution(* com.cg.app.model.Calculator.*(..))", returning = "returnValue")
	public void log4(JoinPoint joinPoint, Integer returnValue) {
		logger.info("Inside log 4");
		logger.info((joinPoint.getSignature()).toString());
		logger.info("return value " + returnValue);
	}
	 
	  
	 @AfterThrowing(pointcut = "execution(* com.cg.app.model.Calculator.*(..))",
			 throwing="ex")
	 public void log5(Exception ex) throws Throwable{
		 logger.info("inside log 5");
		 logger.info("throws exception "+ex);
	 }
	 
}
