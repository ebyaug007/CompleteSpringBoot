package com.springexceptioncheck.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;


@Configuration

public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .setGroup("springexceptioncheck-controllers")
                .pathsToMatch("/student/**")
                .build();
    }
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("StudentException API")
                .description("Student Assignment sample application")
                .version("v0.0.1")
                .license(new License().name("").url("")))
                .externalDocs(new ExternalDocumentation()
                .description("")
                .url(""));
    }

}
