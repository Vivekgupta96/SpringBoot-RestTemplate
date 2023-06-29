package com.masai;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo2 {
	
	public static void main(String[] args) {
		
		ApplicationContext ctx= new AnnotationConfigApplicationContext(AppConfig.class);

		RestClientBean bean= ctx.getBean("restClientBean",RestClientBean.class);
		
		bean.callingApi();
		
	}

}
