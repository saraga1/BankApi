package dev.araga.app;

import dev.araga.controllers.AccountController;
import dev.araga.controllers.ClientController;
import io.javalin.Javalin;

public class App {

    public static void main (String[] args) {

        Javalin app = Javalin.create();
        ClientController clientController = new ClientController();
        AccountController accountController = new AccountController();


        app.get("/clients", clientController.getAllClientHandler);

        app.get("/clients/:id", clientController.getClientByIdHandler);

        app.post("/clients", clientController.createClientHandler);

        app.put("/clients/:id", clientController.updateClientHandler);

        app.delete("/clients/:id", clientController.deleteClientHandler);

        app.get("/clients/:id/accounts", accountController.getAllAccountHandler);
        app.post("/clients/:id/accounts", accountController.createAccountHandler);
        app.get("/clients/:id2/accounts/:id", accountController.getAccountByIdHandler);
        app.put("/clients/:id2/accounts/:id", accountController.updateAccountHandler);
        app.delete("/clients/:id2/accounts/:id", accountController.deleteAccountHandler);


        app.start();

    }
}
