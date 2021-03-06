package dev.araga.services;

import dev.araga.entities.Client;

import java.util.Set;

public interface ClientService {

    Client registerClient(Client client);

    Set<Client> getAllClients();
    Client getClientById(int id);

    Client updateClient(Client client);
    boolean deleteClientById(int id);




}
