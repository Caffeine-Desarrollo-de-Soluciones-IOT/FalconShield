package com.verysafe.falconshield.payments.infrastructure;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.user.account.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class StripeService implements IStripeService {
    private final IAccountService accountService;
    private final String FRONTEND_DOMAIN = "http://localhost:4242";

    public StripeService(IAccountService accountService) {
        this.accountService = accountService;
        Stripe.apiKey = "sk_test_51QBfojGThVlbgNYkCMUxcoAuA7vuW0obzCDwbEgQJ2TqGtPkUiU2kUhGbs36NGk0w471BY4uYjdjeX1ImeJ6mqX100BcZfrl5x";
    }

    @Override
    public URI createCheckoutSession(String priceId, String accountId) {
        var userAccount = accountService.getAccountById(accountId);
        var fullName = userAccount.getFirstName() + " " + userAccount.getLastName();

        try {
            var customer = StripeCustomerUtil.findOrCreateCustomer(userAccount.getEmail(), fullName);
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
            return URI.create(session.getUrl());

        } catch (StripeException ex) {
            throw new CustomException(HttpStatus.I_AM_A_TEAPOT, "Failed to create checkout session", ex.getMessage());
        }
    }

    @Override
    public URI createCustomerPortalSession(String accountId) {
        var userAccount = accountService.getAccountById(accountId);
        var fullName = userAccount.getFirstName() + " " + userAccount.getLastName();

        try {
            var customer = StripeCustomerUtil.findOrCreateCustomer(userAccount.getEmail(), fullName);
            var params = new com.stripe.param.billingportal.SessionCreateParams.Builder()
                    .setCustomer(customer.getId())
                    .setReturnUrl(FRONTEND_DOMAIN)
                    .build();
            var portalSession = com.stripe.model.billingportal.Session.create(params);
            return URI.create(portalSession.getUrl());

        } catch (StripeException ex) {
            throw new CustomException(HttpStatus.I_AM_A_TEAPOT, "Failed to create customer portal session", ex.getMessage());
        }
    }
}
