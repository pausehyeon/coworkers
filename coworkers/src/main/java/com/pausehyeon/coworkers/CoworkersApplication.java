package com.pausehyeon.coworkers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CoworkersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoworkersApplication.class, args);
	}

}
