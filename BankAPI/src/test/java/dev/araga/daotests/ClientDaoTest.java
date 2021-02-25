package dev.araga.daotests;


import dev.araga.daos.ClientDAO;
import dev.araga.daos.ClientDaoPostgres;
import dev.araga.entities.Client;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientDaoTest {


    private static ClientDAO cdao = new ClientDaoPostgres();
    private static Client testClient;


    @Test
    @Order(1)
    void create_client() {

        Client newClient = new Client(0,"Sam","Araga","Georgia");
        cdao.createClient(newClient);
        System.out.println(newClient);
        testClient = newClient;
        Assertions.assertNotEquals(0,newClient.getClientId());

    }

    @Test
    @Order(2)
    void get_client_by_id(){
        int id = testClient.getClientId();
        Client client = cdao.getClientById(id);
        Assertions.assertEquals(testClient.getFname(), client.getFname());
    }

    @Test
    @Order(3)
    void update_client(){
        Client client = cdao.getClientById(testClient.getClientId());
        System.out.println(testClient.getClientId());
        client.setFname("Bob");
        cdao.updateClient(client);

        Client updatedClient = cdao.getClientById(testClient.getClientId());
        System.out.println(testClient.getFname());
        System.out.println(cdao.toString());
        System.out.printf(updatedClient.getFname());
        Assertions.assertNotEquals(testClient.getFname(), client.getFname());
    }

    @Test
    @Order(4)
    void get_all_clients(){
        Client c1 = new Client(0, "Megan", " Fox", "Kansas");
        Client c2 = new Client(0, "Morgan", "Freeman", "Californnia");
        Client c3 = new Client(0, "Drew ","Carry", "Ohio");

        cdao.createClient(c1);
        cdao.createClient(c2);
        cdao.createClient(c3);

        Set<Client> allClients = cdao.getAllClient();
        Assertions.assertTrue(allClients.size()>2);

    }

    @Test
    @Order(5)
    void delete_client_by_id(){
        int id = testClient.getClientId();
        boolean deleted = cdao.deleteClientById(id);
        Assertions.assertTrue(deleted);
    }


}
