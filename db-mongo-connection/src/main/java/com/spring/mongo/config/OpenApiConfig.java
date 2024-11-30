package com.spring.mongo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name="Vishwajeet",
                        email="vishwajeetak@gmail.com"
                ),
                description = "OpenApi documentation for Students Operations from Spring box",
                title = "DB MongoDB integration - User Profile Details",
                version = "1.0",
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        })
public class OpenApiConfig {
}

