package dev.ajimenez.daos.Accounts;

import dev.ajimenez.entities.Account;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AccountDaoLocal implements AccountDao{

    private static Map<Integer, Account> accountTable = new HashMap<Integer, Account>();
    private static int idMaker = 0;

    @Override
    public Account createAccount(Account account) {
        account.setAccountId(++idMaker);
        accountTable.put(account.getAccountId(), account);
        return account;
    }

    @Override
    public Set<Account> getAllAccounts() {
        Set<Account> allAccounts = new HashSet<Account>(accountTable.values());
        return allAccounts;
    }

    @Override
    public Account getAccountById(int id) {
        return accountTable.get(id);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountTable.put(account.getAccountId(), account);
    }

    @Override
    public boolean deleteAccountById(int id) {
        Account account = accountTable.remove(id);

        if (account == null) {
            return false;
        } else {
            return true;
        }
    }
}
