package com.verysafe.falconshield.payments.domain.services.queries;

import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;

import java.net.URI;

public interface IPaymentQueries {
    ApiResponse<URI> createCustomerPortalSession(String accountId);
}
