package com.example.service;

import com.example.dao.AccountDAOImpl;
import com.example.dto.AccountDTO;
import com.example.model.Account;

public class AccountService {

    private AccountDAOImpl accountDAO = AccountDAOImpl.getInstance();
    private AccountDTO accountDTO = AccountDTO.getInstance();

    private static AccountService accountService = null;

    private AccountService() {}

    public static AccountService getInstance() {
        if (accountService == null)
            accountService = new AccountService();
        return accountService;
    }

    public Account loginByUserAndPassword(String userName, String pass) throws Exception {
        return accountDAO.loginByUserAndPassword(userName, pass);
    }

    public boolean checkUserExits(String userName) throws Exception {
        return accountDAO.checkUserExits(userName);
    }

    public boolean addNewAccount(String userName, String password) throws Exception {

        Account account = new Account();
        account.setUser(userName);
        account.setPassword(password);
        return accountDAO.save(account);
    }
}
