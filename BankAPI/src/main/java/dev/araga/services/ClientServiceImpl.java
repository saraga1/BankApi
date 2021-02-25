package dev.araga.services;

import dev.araga.daos.ClientDAO;
import dev.araga.entities.Client;

import java.util.Set;

public class ClientServiceImpl implements ClientService{


    private ClientDAO cdao;

    public ClientServiceImpl(ClientDAO clientDAO){
        this.cdao = clientDAO;
    }

    @Override
    public Client registerClient(Client client) {
        this.cdao.createClient(client);
        return client;
    }

    @Override
    public Set<Client> getAllClients() {
        return this.cdao.getAllClient();
    }

    @Override
    public Client getClientById(int id) {
        return this.cdao.getClientById(id);
    }

    @Override
    public Client updateClient(Client client) {

        return this.cdao.updateClient(client);
    }

    @Override
    public boolean deleteClientById(int id) {
        return this.cdao.deleteClientById(id);
    }
}
