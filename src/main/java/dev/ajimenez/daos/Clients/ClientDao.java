package dev.ajimenez.daos.Clients;

import dev.ajimenez.entities.Client;

import java.util.Set;

public interface ClientDao {

    // Every entity will have it's own DAO (data access object), it's designed to perform CRUD operations for a single entity

    // Create
    Client createClient(Client client);

    // Read
    Set<Client> getAllClients();
    Client getClientById(int id);

    // Update
    Client updateClient(Client client);

    // Delete
    boolean deleteClientById(int id);
}
