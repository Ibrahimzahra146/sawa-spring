package com.secret.secret;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@EnableJSONDoc

public class SecretApplication {

	public static void main(String[] args) {
		
        System.out.println("version: " + SpringVersion.getVersion());

		SpringApplication.run(SecretApplication.class, args);
	}
}
