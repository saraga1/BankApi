package dev.araga.daos;

import dev.araga.entities.Client;

import java.util.Set;

//CRUD operation for Client (CREATE, READ, UPDATE, and DELETE)
public interface ClientDAO {

    //CREATE
    Client createClient(Client client);

    //READ
    Set<Client> getAllClient();
    Client getClientById(int id);

    //UPDATE
    Client updateClient(Client client);

    //DELETE
    boolean deleteClientById(int id);


}
