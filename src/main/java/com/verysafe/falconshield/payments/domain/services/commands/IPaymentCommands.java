package com.verysafe.falconshield.payments.domain.services.commands;

import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.net.URI;

public interface IPaymentCommands {
    ApiResponse<URI> createCheckoutSession(String priceId, String accountId);
}
