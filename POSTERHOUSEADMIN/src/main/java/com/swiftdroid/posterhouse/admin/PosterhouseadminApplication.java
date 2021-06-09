package com.swiftdroid.posterhouse.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PosterhouseadminApplication extends SpringBootServletInitializer  {

	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(PosterhouseadminApplication.class);
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(PosterhouseadminApplication.class, args);
	}

}
