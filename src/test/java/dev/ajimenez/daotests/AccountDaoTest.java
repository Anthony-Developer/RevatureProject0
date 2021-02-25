package dev.ajimenez.daotests;

import dev.ajimenez.daos.Accounts.AccountDao;
import dev.ajimenez.daos.Accounts.AccountDaoLocal;
import dev.ajimenez.entities.Account;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDaoTest {

    private static AccountDao adao = new AccountDaoLocal();
    private static Account testAccount = null;

    @Test
    @Order(1)
    void create_account() {
        Account testAccountOne = new Account(0, "checking", 1, 10_000, 1);
        adao.createAccount(testAccountOne);

        System.out.println("The Account created was " + testAccountOne);
        testAccount = testAccountOne;
        Assertions.assertNotEquals(0, testAccountOne.getAccountId());
    }

    @Test
    @Order(2)
    void get_account_by_id() {
        int id = testAccount.getAccountId();
        Account account = adao.getAccountById(id);
        Assertions.assertEquals(testAccount.getAccountId(), account.getAccountId());
        System.out.println("The account retrieved was " + account);
    }

    @Test
    @Order(3)
    void update_account() {
        Account account = adao.getAccountById(testAccount.getAccountId());
        account.setBalance(50_000);
        adao.updateAccount(account);
        Account updateAccount = adao.getAccountById(testAccount.getAccountId());
        System.out.println("The account's balance increased to " + updateAccount.getBalance() + "!");
        Assertions.assertEquals(50_000, updateAccount.getBalance());
    }

    @Test
    @Order(4)
    void get_all_accounts() {
        Account account1 = new Account(0, "checking", 1, 15_000, 7);
        Account account2 = new Account(0, "checking", 1, 10_000, 4);
        Account account3 = new Account(0, "saving", 1, 7_000, 9);
        Account account4 = new Account(0, "checking", 1, 54_000, 8);
        Account account5 = new Account(0, "saving", 1, 22_000, 5);

        adao.createAccount(account1);
        adao.createAccount(account2);
        adao.createAccount(account3);
        adao.createAccount(account4);
        adao.createAccount(account5);

        Set<Account> allAccounts = adao.getAllAccounts();
        Assertions.assertTrue(allAccounts.size() > 4);

        System.out.println("We retrieved all accounts successfully!");
    }

    @Test
    @Order(5)
    void delete_account_by_id() {
        int id = testAccount.getAccountId();
        boolean result = adao.deleteAccountById(id);
        System.out.println("The account has been successfully deleted!");

        Assertions.assertTrue(result);
    }
}
