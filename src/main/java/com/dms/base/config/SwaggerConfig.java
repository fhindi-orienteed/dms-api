package com.dms.base.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    private SecurityScheme securityScheme = new SecurityScheme()
            .name("Authorization")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT");

    private SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("bearerAuth");

    private Info getInfo(String title, String description) {
        return new Info()
                .title(title)
                .version("1.0")
                .description(description);
    }

    // Group for WEB APIs
    @Bean
    public GroupedOpenApi webApi() {
        return GroupedOpenApi.builder()
                .group("Web Portal")
                .pathsToMatch("/v1/web/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(
                            getInfo("DMS Platform - Web Portal APIs", "Endpoints For Web Portal of DMS Platform."));
                    openApi.getComponents().addSecuritySchemes("bearerAuth", securityScheme);
                    openApi.addSecurityItem(securityRequirement);
                })
                .build();
    }

    // Group for MOBILE APIs
    @Bean
    public GroupedOpenApi mobileApi() {
        return GroupedOpenApi.builder()
                .group("Mobile Applicaiton")
                .pathsToMatch("/v1/mobile/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(
                            getInfo("DMS Platform - Mobile Application APIs",
                                    "Endpoints For DMS Platform Mobile Applicaiton."));
                    openApi.getComponents().addSecuritySchemes("bearerAuth", securityScheme);
                    openApi.addSecurityItem(securityRequirement);
                })
                .build();
    }

    // Group for Public APIs
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Public Access")
                .pathsToMatch("/v1/public/**")
                .addOpenApiCustomizer(openApi -> {
                    openApi.info(
                            getInfo("DMS Platform - Public Access APIs",
                                    "Endpoints For Public Access Of DMS Platform."));
                    openApi.getComponents().addSecuritySchemes("bearerAuth", securityScheme);
                    openApi.addSecurityItem(securityRequirement);
                })
                .build();
    }
}