package com.verysafe.falconshield.payments.infrastructure;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerSearchParams;
import org.springframework.beans.factory.annotation.Value;

public class StripeGateway {
    @Value("${stripe.api.key}")
    private static String API_KEY;

    public static final String FRONTEND_DOMAIN = "http://localhost:4242";

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
