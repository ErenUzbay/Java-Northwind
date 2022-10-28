package com.northwind.northwind;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@SpringBootApplication
public class NorthwindApplication {

	public static void main(String[] args) {
		SpringApplication.run(NorthwindApplication.class, args);
	}
	
	// http://localhost:8080/swagger-ui/index.html
	@Bean
	public OpenAPI customeOpenAPI(@Value("${application-description}")String description,
	        @Value("${application-version}")String version ) {
	    return new OpenAPI()
	            .info(new Info()
	                    .title("Northwind API")
	                    .version(version)
	                    .description(description)
	                    .license(new License().name("Northwind API Licence")));
	}

}
