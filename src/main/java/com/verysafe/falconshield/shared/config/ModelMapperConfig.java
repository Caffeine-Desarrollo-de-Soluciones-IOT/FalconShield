package com.verysafe.falconshield.shared.config;

import com.verysafe.falconshield.user.account.mappers.UserAccountMappers;
import com.verysafe.falconshield.user.account.model.Account;
import org.keycloak.representations.idm.UserRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        //custom mappings
        modelMapper.createTypeMap(UserRepresentation.class, Account.class)
                .setConverter(UserAccountMappers.keycloakUserRepresentationToAccount());

        return modelMapper;
    }
}
