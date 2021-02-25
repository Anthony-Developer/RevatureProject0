package dev.ajimenez.services.Clients;

import dev.ajimenez.daos.Clients.ClientDao;
import dev.ajimenez.entities.Client;
import org.apache.log4j.Logger;

import java.util.Set;

public class ClientServiceImpl implements ClientService {

    // When the logger write it will say what class the log was from
    private static Logger logger = Logger.getLogger(ClientServiceImpl.class.getName());

    private ClientDao cdao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.cdao = clientDao;
    }

    @Override
    public Client createClient(Client client) {
        this.cdao.createClient(client);
        return client;
    }

    @Override
    public Set<Client> getAllClients() {
        return this.cdao.getAllClients();
    }

    @Override
    public Client getClientById(int id) {
        return this.cdao.getClientById(id);
    }

    @Override
    public Client updateClient(Client client) {
        Client oldClient = this.cdao.getClientById(client.getClientID());

        this.cdao.updateClient(client);
        return client;
    }

    @Override
    public boolean deleteClientById(int id) {
        return this.cdao.deleteClientById(id);
    }
}
