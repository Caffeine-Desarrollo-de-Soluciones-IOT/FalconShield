package com.verysafe.falconshield.shared.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openApiConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("VerySafe - Falcon Shield")
                        .description("Documentación del backend")
                        .version("1.0.0")
                );
    }
}
