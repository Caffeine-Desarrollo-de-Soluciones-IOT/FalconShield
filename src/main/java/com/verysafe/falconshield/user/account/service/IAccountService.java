package com.verysafe.falconshield.user.account.service;

import com.verysafe.falconshield.user.account.model.Account;

public interface IAccountService {
    Account getAccountById(String accountId);
}
