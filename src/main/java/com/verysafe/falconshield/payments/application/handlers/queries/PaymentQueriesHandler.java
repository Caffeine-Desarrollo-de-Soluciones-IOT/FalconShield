package com.verysafe.falconshield.payments.application.handlers.queries;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.billingportal.Session;
import com.stripe.param.billingportal.SessionCreateParams;
import com.verysafe.falconshield.payments.domain.services.queries.IPaymentQueries;
import com.verysafe.falconshield.payments.infrastructure.StripeGateway;
import com.verysafe.falconshield.payments.infrastructure.StripeProperties;
import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import com.verysafe.falconshield.user.account.service.IAccountService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;

import static com.verysafe.falconshield.payments.infrastructure.StripeGateway.FRONTEND_DOMAIN;

@Service
public class PaymentQueriesHandler implements IPaymentQueries {
    private final IAccountService accountService;
    private final StripeProperties stripeProperties;

    public PaymentQueriesHandler(IAccountService accountService, StripeProperties stripeProperties) {
        this.accountService = accountService;
        this.stripeProperties = stripeProperties;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeProperties.getApiKey();
    }

    @Override
    public ApiResponse<URI> createCustomerPortalSession(String accountId) {
        var userAccount = accountService.getAccountById(accountId);
        var fullName = userAccount.getFirstName() + " " + userAccount.getLastName();

        try {
            var customer = StripeGateway.findOrCreateCustomer(userAccount.getEmail(), fullName);
            var params = new SessionCreateParams.Builder()
                    .setCustomer(customer.getId())
                    .setReturnUrl(FRONTEND_DOMAIN)
                    .build();
            var portalSession = Session.create(params);
            return new ApiResponse<>("Ok", true, URI.create(portalSession.getUrl()));

        } catch (StripeException ex) {
            throw new CustomException(HttpStatus.I_AM_A_TEAPOT, "Failed to create customer portal session", ex.getMessage());
        }
    }
}
