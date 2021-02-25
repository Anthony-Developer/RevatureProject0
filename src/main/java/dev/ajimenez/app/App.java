package dev.ajimenez.app;

import dev.ajimenez.controllers.AccountController;
import dev.ajimenez.controllers.ClientController;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        // Importing my controllers
        ClientController clientController = new ClientController();
        AccountController accountController = new AccountController();

        // Client controllers
        app.get("/clients", clientController.getAllClients);
        app.get("/clients/:id", clientController.getClientById);
        app.post("/clients", clientController.createClient);
        app.put("clients/:id", clientController.updateClient);
        app.delete("clients/:id", clientController.deleteClient);

        // Account controllers
        app.get("/clients/:id/accounts", accountController.getAllAccounts);
        app.get("/clients/:id/accounts/:num", accountController.getAccountById);
        app.post("/clients/:id/accounts", accountController.createAccount);
        app.put("clients/:id/accounts/:num", accountController.updateAccount);
        app.delete("clients/:id/accounts/:num", accountController.deleteAccount);

        app.start();

    }
}
