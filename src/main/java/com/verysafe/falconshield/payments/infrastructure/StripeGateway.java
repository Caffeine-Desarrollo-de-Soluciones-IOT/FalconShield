package com.verysafe.falconshield.payments.infrastructure;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;

public class StripeGateway {
    public static final String FRONTEND_DOMAIN = "https://falcon-shield-web-kizx.vercel.app";

    public static Customer findOrCreateCustomer(String email, String name) throws StripeException {
        var params = CustomerSearchParams
                .builder()
                .setQuery(String.format("email:'%s'", email))
                .build();

        var result = Customer.search(params);

        Customer customer;

        //si no existe el cliente, se crea uno nuevo
        if (result.getData().isEmpty()) {
            var customerCreateParams = CustomerCreateParams.builder()
                    .setName(name)
                    .setEmail(email)
                    .build();
            customer = Customer.create(customerCreateParams);
        } else {
            customer = result.getData().getFirst();
        }

        return customer;
    }
}
