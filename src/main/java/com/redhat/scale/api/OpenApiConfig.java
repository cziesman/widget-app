package com.redhat.scale.api;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI initOpenAPI() {

        return new OpenAPI().info(new Info().title("Widget API").description("The Widget Backend Service").version("1.0"));
    }
}
