package dev.ajimenez.controllers;

import com.google.gson.Gson;
import dev.ajimenez.daos.Accounts.AccountDaoPostgres;
import dev.ajimenez.entities.Account;
import dev.ajimenez.services.Accounts.AccountService;
import dev.ajimenez.services.Accounts.AccountServiceImpl;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountController {

    private AccountService accountService = new AccountServiceImpl(new AccountDaoPostgres());

    public Handler getAllAccounts = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Set<Account> allAccounts = this.accountService.getAllAccounts();
        List<Account> selectedAccounts = new ArrayList<Account>();


        // Going through all accounts and only adding the ones with the client_id to the list
        for (Account a : allAccounts) {
            if (a.getClientId() == id) {
                selectedAccounts.add(a);
            }
        }

        Gson gson = new Gson();
        String accountsJSON = gson.toJson(selectedAccounts);
        int number = selectedAccounts.size();

        if (selectedAccounts.size() == 0) {
            ctx.result("This client does not exist.");
            ctx.status(404);
        } else {
            ctx.result("Retrieved " + number + " accounts for this client. " + selectedAccounts);
            ctx.status(200);
        }

    };

    public Handler getAccountById = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int num = Integer.parseInt(ctx.pathParam("num"));
        Set<Account> allAccounts = this.accountService.getAllAccounts();
        List<Account> selectedAccounts = new ArrayList<Account>();

        for (Account a : allAccounts) {
            if (a.getClientId() == id && a.getAccountNumber() == num) {
                selectedAccounts.add(a);
            }
        }

        if (selectedAccounts.size() == 0) {
            ctx.result("The account you are looking for does not exist.");
            ctx.status(404);
        } else {
            Gson gson = new Gson();
            String clientJSON = gson.toJson(selectedAccounts);
            ctx.result(clientJSON);
            ctx.result("Here is the account you requested." + selectedAccounts);
            ctx.status(200);
        }
    };

    public Handler createAccount = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Set<Account> allAccounts = this.accountService.getAllAccounts();
        Set<Account> selectedAccounts = new HashSet<Account>();

        // Going through all accounts and making a set with
        for (Account a : allAccounts) {
            if (a.getClientId() == id) {
                selectedAccounts.add(a);
            }
        }

        int number = selectedAccounts.size() + 1;
        String body = ctx.body();
        Gson gson = new Gson();
        Account account = gson.fromJson(body, Account.class);
        account.setClientId(id);
        account.setAccountNumber(number);

        this.accountService.createAccount(account);

        if (account.getAccountId() == 0) {
            ctx.result("This client does not exist, cannot create an account.");
            ctx.status(404);
        } else {
            ctx.result("You have successfully opened a new account! " + account);
            ctx.status(201);
        }

    };

    public Handler updateAccount = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int num = Integer.parseInt(ctx.pathParam("num"));
        Set<Account> allAccounts = this.accountService.getAllAccounts();

        for (Account a : allAccounts) {
            if (a.getClientId() != id && a.getAccountNumber() != num) {

            } else if (a.getClientId() == id && a.getAccountNumber() == num) {
                String body = ctx.body();
                Gson gson = new Gson();
                Account account = gson.fromJson(body, Account.class);
                account.setClientId(id);
                account.setAccountNumber(num);
                this.accountService.updateAccount(account);

                ctx.result("You have successfully updated your account. " + account);
                ctx.status(200);
            }
        }


    };

    public Handler deleteAccount = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int num = Integer.parseInt(ctx.pathParam("num"));
        Set<Account> allAccounts = this.accountService.getAllAccounts();

        for (Account a : allAccounts) {
            if (a.getClientId() == id && a.getAccountNumber() == num) {
                boolean deleted = this.accountService.deleteAccountById(a.getAccountId());

                if (deleted) {
                    ctx.result("This account was successfully closed!");
                    ctx.status(200);
                } else {
                    ctx.result("Something went wrong and the account was NOT closed!");
                    ctx.status(400);}
                }

            }

        };

    }
