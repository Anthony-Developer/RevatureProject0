package dev.ajimenez.daos.Clients;

import dev.ajimenez.entities.Client;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClientDaoLocal implements ClientDao {

    // This is the local memory "database", once you shut off application everything will get deleted

    private static Map<Integer, Client> clientTable = new HashMap<Integer, Client>();
    private static int idMaker = 0;

    @Override
    public Client createClient(Client client) {
        // Creates a new and unique id for the client, most databases just increment a counter
        client.setClientID(++idMaker);
        // Emulates saving to a SQL database table
        clientTable.put(client.getClientID(), client);
        return client;
    }

    @Override
    public Set<Client> getAllClients() {
        Set<Client> allClients = new HashSet<Client>(clientTable.values());
        return allClients;
    }

    @Override
    public Client getClientById(int id) {
        return clientTable.get(id);
    }

    @Override
    public Client updateClient(Client client) {
        return clientTable.put(client.getClientID(), client);
    }

    @Override
    public boolean deleteClientById(int id) {
        // This will remove the object, but will also return the object
        Client client = clientTable.remove(id);

        if (client == null) {
            return false;
        } else {
            return true;
        }
    }
}