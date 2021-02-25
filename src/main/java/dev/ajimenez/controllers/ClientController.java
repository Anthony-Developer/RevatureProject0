package dev.ajimenez.controllers;

import com.google.gson.Gson;
import dev.ajimenez.daos.Clients.ClientDaoPostgres;
import dev.ajimenez.entities.Client;
import dev.ajimenez.services.Clients.ClientService;
import dev.ajimenez.services.Clients.ClientServiceImpl;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.Set;

public class ClientController {

    // All logic in the controller should deal with the API, reading path parameters is a controller specific, should call services to perform actions

    private ClientService clientService = new ClientServiceImpl(new ClientDaoPostgres());

    // Getting all the clients
    public Handler getAllClients = (Context ctx) -> {
        Set<Client> allClients = this.clientService.getAllClients();
        Gson gson = new Gson();
        String clientsJSON = gson.toJson(allClients);
        ctx.result(clientsJSON);
        ctx.result("Here is a list of all the clients within this bank." + allClients);
        ctx.status(200);
    };

    // Getting a client by ID
    public Handler getClientById = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(id);

        // If client doesn't exist give back a 404 error
        if (client == null) {
            ctx.result("The client you are looking for does not exist.");
            ctx.status(404);
        } else {
            Gson gson = new Gson();
            String clientJSON = gson.toJson(client);
            ctx.result(clientJSON);
            ctx.result("Here is the client you requested." + client);
            ctx.status(200);
        }
    };

    // Creating a new client
    public Handler createClient = (Context ctx) -> {
        String body = ctx.body();
        Gson gson = new Gson();
        Client client = gson.fromJson(body, Client.class);

        this.clientService.createClient(client);
        ctx.result("You have successfully created a new client! " + client);
        ctx.status(201);
    };

    // Update a client
    public Handler updateClient = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(id);

        if (client == null) {
            ctx.result("This client doesn't exist.");
            ctx.status(404);
        } else {
            Gson gson = new Gson();
            String body = ctx.body();
            Client updatedClient = gson.fromJson(body, Client.class);
            updatedClient.setClientID(id);
            this.clientService.updateClient(updatedClient);
            ctx.result("You have successfully updated the client." + updatedClient);
            ctx.status(200);
        }

    };

    // Delete a client
    public Handler deleteClient = (Context ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(id);
        boolean deleted = this.clientService.deleteClientById(id);

        if (client == null) {
            ctx.result("The client you are trying to delete does not exist.");
            ctx.status(404);
        } else if (deleted) {
            ctx.result("This client was successfully deleted along with associated accounts!");
            ctx.status(200);
        } else {
            ctx.result("Something went wrong.");
            ctx.status(400);
        }
    };

}
