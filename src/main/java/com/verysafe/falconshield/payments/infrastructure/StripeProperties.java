package com.verysafe.falconshield.payments.infrastructure;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
@Configuration
@ConfigurationProperties(prefix = "stripe")
public class StripeProperties {
    @NotBlank(message = "Stripe API key is required")
    private String apiKey;

    @NotBlank(message = "Stripe webhook secret is required")
    private String webhookSecret;
}