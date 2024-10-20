package com.verysafe.falconshield.payments.infrastructure;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Payments")
@RequestMapping("/api/payments")
@RestController
public class StripeController {
    private final IStripeService stripeService;

    public StripeController(IStripeService stripeService) {
        this.stripeService = stripeService;
    }

    @PostMapping("/subscription/new/{priceId}")
    public ResponseEntity<Void> createCheckoutSession(
            @PathVariable String priceId,
            @AuthenticationPrincipal Jwt principal
    ) {
        var res = stripeService.createCheckoutSession(priceId, principal.getSubject());
        var headers = new HttpHeaders();
        headers.setLocation(res);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/subscription/detail")
    public ResponseEntity<Void> createCustomerPortalSession(@AuthenticationPrincipal Jwt principal) {
        var res = stripeService.createCustomerPortalSession(principal.getSubject());
        var headers = new HttpHeaders();
        headers.setLocation(res);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
