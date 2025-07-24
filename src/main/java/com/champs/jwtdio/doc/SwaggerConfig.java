package com.champs.jwtdio.doc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springSwaggerOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(new Info()
                        .title("Spring Swagger API Documentation")
                        .description("API documentation for the Spring Swagger application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Champs")
                                .url("https://www.champs.com")
                                .email("champs@champs.com"))
                        .license(new License()
                                .name("Apache License Version 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Projeto no GitHub")
                        .url("https://github.com/champs-8/springSwagger"));
        return openAPI;
    }
}