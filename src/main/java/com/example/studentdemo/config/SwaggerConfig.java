package com.example.studentdemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.studentdemo.controller"))
                .build()
                .apiInfo(metaData());
    }

    public ApiInfo metaData(){
        ApiInfo apiInfo = new ApiInfo(
                "Student Demo Application",
                "My First Spring Boot  application for education ",
                "1.0",
                "Terms Of Service",
                new Contact("Mg Kaung","https://github.com/dev-mgkaung","developermgkaung@gmail.com"),
                "Null",
                "Null",
                new ArrayList<VendorExtension>());
        return apiInfo;
    }
}