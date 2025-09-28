package com.example.TalentGo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI hireNeticOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recruitment Marketplace API")
                        .description("recruitment marketplace system that connects organizations, vendors, and candidates. "
                                + "This API allows organizations to create and manage jobs, assign candidates from vendor organizations, "
                                + "track application status (APPLIED, SELECTED, REJECTED), and manage vendor-client relationships. "
                                + "It also provides endpoints for candidate visibility, job management, and organizational administration.")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Deepak Kumar Singh")
                                .email("deepak76311@gmail.com")
                                .url("https://github.com/DeepakKDEV")
                        )
                );
    }
}
