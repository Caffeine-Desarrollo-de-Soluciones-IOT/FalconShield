package com.verysafe.falconshield.user.account.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Account {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String picture;
    private boolean emailVerified;
    private boolean enabled;
    private LocalDateTime createdTimestamp;

    public Account() {}
}
