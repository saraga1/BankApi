package dev.araga.servicetests;

import dev.araga.daos.ClientDaoPostgres;
import dev.araga.entities.Client;
import dev.araga.services.ClientService;
import dev.araga.services.ClientServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Set;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClientServiceTest {

    private static ClientService cserv = new ClientServiceImpl(new ClientDaoPostgres());
    private static Client testClient = null;


    @Test
    @Order(1)
    void register_client(){
        Client client = new Client(0,"Beruk" ,"Araga", "Georgia");
        cserv.registerClient(client);
        Assertions.assertNotEquals(0,client.getClientId());
        testClient = client;
    }


    @Test
    @Order(2)
    void get_client_by_id(){
        int id = testClient.getClientId();
        Client client = cserv.getClientById(id);
        Assertions.assertEquals(testClient.getFname(), client.getFname());
    }

    @Test
    @Order(3)
    void update_client(){
        Client client = cserv.getClientById(testClient.getClientId());
        System.out.println(testClient.getClientId());
        client.setFname("Bob");
        cserv.updateClient(client);

        Client updatedClient = cserv.getClientById(testClient.getClientId());
        System.out.println(testClient.getFname());
        System.out.println(cserv.toString());
        System.out.printf(updatedClient.getFname());
        Assertions.assertNotEquals(testClient.getFname(), client.getFname());
    }

    @Test
    @Order(4)
    void get_all_clients(){
        Client c1 = new Client(0, "Megan", " Fox", "Kansas");
        Client c2 = new Client(0, "Morgan", "Freeman", "Californnia");
        Client c3 = new Client(0, "Drew ","Carry", "Ohio");

        cserv.registerClient(c1);
        cserv.registerClient(c2);
        cserv.registerClient(c3);


        Set<Client> allClients = cserv.getAllClients();
        Assertions.assertTrue(allClients.size()>2);

    }

    @Test
    @Order(5)
    void delete_client_by_id(){
        int id = testClient.getClientId();
        boolean deleted = cserv.deleteClientById(id);
        Assertions.assertTrue(deleted);
    }


}
