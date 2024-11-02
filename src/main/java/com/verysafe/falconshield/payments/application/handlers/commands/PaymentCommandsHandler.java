package com.verysafe.falconshield.payments.application.handlers.commands;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.verysafe.falconshield.payments.domain.services.commands.IPaymentCommands;
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
public class PaymentCommandsHandler implements IPaymentCommands {
    private final IAccountService accountService;
    private final StripeProperties stripeProperties;

    public PaymentCommandsHandler(IAccountService accountService, StripeProperties stripeProperties) {
        this.accountService = accountService;
        this.stripeProperties = stripeProperties;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeProperties.getApiKey();
    }

    @Override
    public ApiResponse<URI> createCheckoutSession(String priceId, String accountId) {
        var userAccount = accountService.getAccountById(accountId);
        var fullName = userAccount.getFirstName() + " " + userAccount.getLastName();

        try {
            var customer = StripeGateway.findOrCreateCustomer(userAccount.getEmail(), fullName);
            var params = SessionCreateParams.builder()
                    .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                    .setCustomer(customer.getId())
                    .setSuccessUrl(FRONTEND_DOMAIN + "?success=true")
                    .setCancelUrl(FRONTEND_DOMAIN + "?canceled=true")
                    .addLineItem(
                            SessionCreateParams.LineItem.builder()
                                    .setQuantity(1L)
                                    .setPrice(priceId) //price ID of the product you want to sell
                                    .build()
                    )
                    .build();

            var session = Session.create(params);
            return new ApiResponse<>("Ok", true, URI.create(session.getUrl()));

        } catch (StripeException ex) {
            throw new CustomException(HttpStatus.I_AM_A_TEAPOT, "Failed to create checkout session", ex.getMessage());
        }
    }
}
