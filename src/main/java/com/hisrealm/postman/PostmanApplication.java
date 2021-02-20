package com.hisrealm.postman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dingjingli
 */
@SpringBootApplication(scanBasePackages="com.hisrealm.postman")
public class PostmanApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostmanApplication.class, args);
	}

}
