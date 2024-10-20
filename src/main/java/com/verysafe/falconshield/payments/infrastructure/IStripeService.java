package com.verysafe.falconshield.payments.infrastructure;

import java.net.URI;

public interface IStripeService {
    URI createCheckoutSession(String priceId, String accountId);
    URI createCustomerPortalSession(String accountId);
}
