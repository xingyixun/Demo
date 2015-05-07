package com.springinaction.springidol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws PerformanceException
	{
		ApplicationContext ctx=new ClassPathXmlApplicationContext("com/springinaction/springidol/spring-idol.xml");
		Performer performer=(Performer) ctx.getBean("duke");
		performer.perform();
	}
}
