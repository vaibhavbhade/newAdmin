package com.swiftdroid.posterhouse.admin;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.swiftdroid.posterhouse.admin.controller.ProductController;

@SpringBootApplication

public class PosterhouseadminApplication {

	public static void main(String[] args) {

		SpringApplication.run(PosterhouseadminApplication.class, args);
	}

}
