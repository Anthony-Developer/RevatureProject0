package dev.ajimenez.daos.Accounts;

import dev.ajimenez.entities.Account;

import java.util.Set;

public interface AccountDao {

    // Create
    Account createAccount(Account account);

    // Read
    Set<Account> getAllAccounts();
    Account getAccountById(int id);

    // Update
    Account updateAccount(Account account);

    // Delete
    boolean deleteAccountById(int id);
}
