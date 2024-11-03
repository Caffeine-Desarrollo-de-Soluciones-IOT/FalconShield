package com.verysafe.falconshield.user.account.mappers;

import com.verysafe.falconshield.user.account.model.Account;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.Converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class UserAccountMappers {
    /**
     * Custom mapper to map a UserRepresentation (Keycloak) to an Account
     */
    public static Converter<UserRepresentation, Account> keycloakUserRepresentationToAccount() {
        return (context) -> {
            var source = context.getSource();
            var destination = new Account();

            destination.setId(source.getId());
            destination.setUsername(source.getUsername());
            destination.setFirstName(source.getFirstName());
            destination.setLastName(source.getLastName());
            destination.setEmail(source.getEmail());
            if (source.getAttributes() != null) {
                //handle picture null
                destination.setPicture(source.getAttributes().get("picture") != null ? source.getAttributes().get("picture").getFirst() : null);
            }
            //destination.setPicture(source.getAttributes() != null ? source.getAttributes().get("picture").getFirst() : null);
            destination.setEmailVerified(source.isEmailVerified());
            destination.setEnabled(source.isEnabled());
            destination.setCreatedTimestamp(LocalDateTime.ofInstant(Instant.ofEpochMilli(source.getCreatedTimestamp()), ZoneId.systemDefault()));

            return destination;
        };
    }
}
