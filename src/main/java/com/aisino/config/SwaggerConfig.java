package com.aisino.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by gangchaopan on 2017/5/25.
 */

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket platformApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("full-platform").apiInfo(apiInfo())
                .forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Mr.Pan-API").description("©2017 Copyright. Powered By Mr.Pan.")
                .contact(new Contact("API", "", "593358472@qq.com")).license("Apache License Version 2.0")
                .licenseUrl("").version("2.0").build();
    }

}
