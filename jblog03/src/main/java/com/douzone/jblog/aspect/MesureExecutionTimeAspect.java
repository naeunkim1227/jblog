package com.douzone.jblog.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class MesureExecutionTimeAspect {
	
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable{
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = pjp.proceed();
		
		sw.stop();
		Long totaltime = sw.getTotalTimeMillis();
		
		
		String className = pjp.getTarget().getClass().getName();
		String method = pjp.getSignature().getName();
		String taskName = className + " : " + method;
		
		System.out.println("[실행 시간][ " + taskName + "]" + totaltime + "mills" );
				
		return result;
	}

}
