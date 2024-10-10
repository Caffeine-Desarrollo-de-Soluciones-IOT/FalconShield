package com.verysafe.falconshield.security;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Keycloak client configuration (to make requests to the server)
 */
@Configuration
public class KeycloakClient {
    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("https://auth.falcon-shield.tech")
                .realm("master")
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username("admin_user")
                .password("admin123")
                .build();
    }
}
