package com.example.dto;

import com.example.model.Account;

public class AccountDTO {

    private static AccountDTO accountDTO = null;

    private AccountDTO() {}

    public static AccountDTO getInstance() {
        if (accountDTO == null)
            accountDTO = new AccountDTO();
        return accountDTO;
    }

    public Account accountDTO(Account account) {

        Account accountDTO = new Account();

        accountDTO.setId(account.getId());
        accountDTO.setUser(account.getUser());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setIsAdmin(account.getIsAdmin());
        accountDTO.setIsSell(account.getIsSell());

        return accountDTO;

    }

}
