package com.khoipd8.accounts.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI accountsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Accounts Service API")
                        .description("REST APIs for Accounts microservice")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("KhoiPD8")
                                .email("support@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
