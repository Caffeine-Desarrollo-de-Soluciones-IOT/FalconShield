package com.verysafe.falconshield.user.account.service;

import com.verysafe.falconshield.shared.exception.CustomException;
import com.verysafe.falconshield.shared.exception.ResourceNotFoundException;
import com.verysafe.falconshield.user.account.model.Account;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.keycloak.admin.client.Keycloak;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    private final Keycloak keycloak;
    private final ModelMapper modelMapper;

    public AccountService(Keycloak keycloak, ModelMapper modelMapper) {
        this.keycloak = keycloak;
        this.modelMapper = modelMapper;
    }

    @Override
    public Account getAccountById(String accountId) {
        try {
            var userRepresentation = keycloak.realm("FalconShield")
                    .users()
                    .get(accountId)
                    .toRepresentation();

            return modelMapper.map(userRepresentation, Account.class);

        } catch (NotFoundException ex) {
            throw new ResourceNotFoundException("Account", "id", accountId);
        } catch (BadRequestException ex1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "An error occurred while retrieving the user account, please try again", ex1.getMessage());
        }
    }
}
