package dev.ajimenez.services.Clients;

import dev.ajimenez.entities.Client;

import java.util.Set;

public interface ClientService {

    // Most of the business logic for an application is in it's services, it is the implementation of application specific rules or features
    // A service might also have a method that wraps around a DAO class and are fully lean in business

    Client createClient(Client client);

    Set<Client> getAllClients();

    Client getClientById(int id);

    Client updateClient(Client client);

    boolean deleteClientById(int id);

}
