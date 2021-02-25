package dev.ajimenez.services.Accounts;

import dev.ajimenez.daos.Accounts.AccountDao;
import dev.ajimenez.entities.Account;
import org.apache.log4j.Logger;

import java.util.Set;

public class AccountServiceImpl implements AccountService{

    private static Logger logger = Logger.getLogger(AccountServiceImpl.class.getName());

    private AccountDao adao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.adao = accountDao;
    }


    @Override
    public Account createAccount(Account account) {
        this.adao.createAccount(account);
        return account;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return this.adao.getAllAccounts();
    }

    @Override
    public Account getAccountById(int id) {
        return this.adao.getAccountById(id);
    }

    @Override
    public Account updateAccount(Account account) {
        this.adao.updateAccount(account);
        return account;
    }

    @Override
    public boolean deleteAccountById(int id) {
        return this.adao.deleteAccountById(id);
    }
}
