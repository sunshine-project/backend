package com.example.sunshineserver.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "GDSC Solution Challenge : Sunshine",
        description = "Sunshine API 명세서",
        version = "v1"
    )
)
@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "Authorization";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .components(new Components()
	.addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
	    .type(SecurityScheme.Type.HTTP)
	    .name(SECURITY_SCHEME_NAME)
	    .scheme("Bearer")
	    .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
//
//            .servers(List.of(
//	new Server().url("http://localhost:8080/").description("Sunshine API Server")));

            .servers(List.of(
	new Server().url("http://35.216.8.32:8080/").description("Sunshine API Server")));
    }
}