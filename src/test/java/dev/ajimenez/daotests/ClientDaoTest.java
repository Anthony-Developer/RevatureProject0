package dev.ajimenez.daotests;

import dev.ajimenez.daos.Clients.ClientDao;
import dev.ajimenez.daos.Clients.ClientDaoPostgres;
import dev.ajimenez.entities.Client;
import org.junit.jupiter.api.*;

import java.util.Set;

// Unless specifically use annotations to specify order, they will not run in a pre-determined way
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDaoTest {

    private static ClientDao cdao = new ClientDaoPostgres();
    private static Client testClient = null;

    @Test
    @Order(1)
    void create_client() {
        // Anytime a client has an ID of 0, it means that it is not saved or persisted somewhere, this is a software convention
        Client tonyLucciano = new Client(0, "Tony", "Lucciano", "1584 Wall Street", "New York, NY", "04/19/1974", 825);
        cdao.createClient(tonyLucciano);

        // ID should not be zero anymore once book has been created
        System.out.println("The Client created was " + tonyLucciano);
        testClient = tonyLucciano;
        Assertions.assertNotEquals(0, tonyLucciano.getClientID());
    }

    @Test
    @Order(2)
    void get_client_by_id() {
        // Just to make sure this client exists
        int id = testClient.getClientID();
        Client client = cdao.getClientById(id);
        Assertions.assertEquals(testClient.getFirstName(), client.getFirstName());
        System.out.println("The Client we retrieved was " + client);
    }

    @Test
    @Order(3)
    void update_client() {
        Client client = cdao.getClientById(testClient.getClientID());
        client.setCreditScore(850);
        // This should update the Client to have a PERFECT credit score
        cdao.updateClient(client);
        Client updateClient = cdao.getClientById(testClient.getClientID());
        System.out.println("The Client's credit score jumped to " + updateClient.getCreditScore() + " congrats!");
        Assertions.assertEquals(850, updateClient.getCreditScore());
    }

    @Test
    @Order(4)
    void get_all_clients() {
        Client client1 = new Client(0, "Wilson", "Blackwood", "517 Michigan Street", "New York, NY", "02/20/1990", 530);
        Client client2 = new Client(0, "Stephanie", "Perez", "9540 Washington Street", "New York, NY", "10/04/1987", 740);
        Client client3 = new Client(0, "John", "Johnson", "687 Main Avenue", "Bronx, NY", "06/19/1995", 617);

        cdao.createClient(client1);
        cdao.createClient(client2);
        cdao.createClient(client3);

        Set<Client> allClients = cdao.getAllClients();
        Assertions.assertTrue(allClients.size() > 2);

        System.out.println("We returned all the Clients!");
    }

    @Test
    @Order(5)
    void delete_client_by_id() {
        int id = testClient.getClientID();
        // Deletes the client based on id
        boolean result = cdao.deleteClientById(id);
        System.out.println("The Client has been successfully deleted, sorry to see you go.");

        Assertions.assertTrue(result);
    }

}
