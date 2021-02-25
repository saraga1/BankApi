package dev.araga.controllers;

import com.google.gson.Gson;
import dev.araga.daos.ClientDaoPostgres;
import dev.araga.entities.Client;
import dev.araga.services.ClientService;
import dev.araga.services.ClientServiceImpl;
import io.javalin.http.Handler;

import java.util.Set;

public class ClientController {

    private ClientService clientService = new ClientServiceImpl(new ClientDaoPostgres());
    //private Gson gson = new Gson();

    public Handler getAllClientHandler = (ctx) -> {
        Set<Client> clientsAll = this.clientService.getAllClients();
        Gson gson = new Gson();
        String clientJson = gson.toJson(clientsAll);
        ctx.result(clientJson);
        ctx.status(200);
    };

    public Handler getClientByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Client client = this.clientService.getClientById(id);
        if(client == null){
            ctx.result("Client not found");
            ctx.status(404);
        }else {
            Gson gson = new Gson();
            String clientJson = gson.toJson(client);
            ctx.result(clientJson);
            ctx.status(201);
        }
    };

    public Handler createClientHandler = (ctx) ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Client client = gson.fromJson(body, Client.class);
        this.clientService.registerClient(client);
        String json = gson.toJson(client);
        ctx.result(json);
        ctx.status(201);
    };

    public Handler updateClientHandler = (ctx)-> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        String body = ctx.body();
        Gson gson = new Gson();
        Client client = gson.fromJson(body, Client.class);
        if(client == null){
            ctx.result("Client not find client");
            ctx.status(404);

        }else {
            client.setClientId(id);
            this.clientService.updateClient(client);
        }
    };

    public Handler deleteClientHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        boolean deleted = this.clientService.deleteClientById(id);
        if(deleted) {
            ctx.result("Client was deleted");
        }else{
            ctx.result("Could not find client");
            ctx.status(404);
        }
    };


}
