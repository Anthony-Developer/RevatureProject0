package dev.ajimenez.services.Accounts;

import dev.ajimenez.entities.Account;

import java.util.Set;

public interface AccountService {

    Account createAccount(Account account);

    Set<Account> getAllAccounts();

    Account getAccountById(int id);

    Account updateAccount(Account account);

    boolean deleteAccountById(int id);
}
