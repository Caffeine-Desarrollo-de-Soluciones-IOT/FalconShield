package com.verysafe.falconshield.payments.application.controllers;

import com.verysafe.falconshield.payments.domain.services.commands.IPaymentCommands;
import com.verysafe.falconshield.payments.domain.services.queries.IPaymentQueries;
import com.verysafe.falconshield.shared.model.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Payments")
@RequestMapping("/api/payments")
@RestController
public class PaymentController {
    private final IPaymentQueries paymentQueries;
    private final IPaymentCommands paymentCommands;

    public PaymentController(IPaymentQueries paymentQueries, IPaymentCommands paymentCommands) {
        this.paymentQueries = paymentQueries;
        this.paymentCommands = paymentCommands;
    }

    @PostMapping("/subscription/new/{priceId}")
    public ResponseEntity<ApiResponse<URI>> createCheckoutSession(
            @PathVariable String priceId,
            @AuthenticationPrincipal Jwt principal
    ) {
        var res = paymentCommands.createCheckoutSession(priceId, principal.getSubject());
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/subscription/detail")
    public ResponseEntity<ApiResponse<URI>> createCustomerPortalSession(@AuthenticationPrincipal Jwt principal) {
        var res = paymentQueries.createCustomerPortalSession(principal.getSubject());
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
