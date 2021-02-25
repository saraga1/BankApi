package dev.araga.controllers;

import com.google.gson.Gson;
import dev.araga.daos.AccountDaoPostgres;
import dev.araga.daos.ClientDaoPostgres;
import dev.araga.entities.Account;
import dev.araga.entities.Client;
import dev.araga.services.AccountService;
import dev.araga.services.AccountServiceImpl;
import dev.araga.services.ClientService;
import dev.araga.services.ClientServiceImpl;
import io.javalin.http.Handler;


import java.util.Set;

public class AccountController {

    private AccountService accountService = new AccountServiceImpl(new AccountDaoPostgres());
    private ClientService clientService = new ClientServiceImpl(new ClientDaoPostgres());


    public Handler getAllAccountHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        String s = ctx.queryParam("amountLessThen","NONE");
        String t = ctx.queryParam("amountGreaterThen","NONE");
        Client getCl = this.clientService.getClientById(id);

        if(getCl == null){
            ctx.result("Client not found");
            ctx.status(404);
        }else {

            if (t.equals("NONE") || s.equals("NONE")) {
                System.out.println(t);
                Set<Account> allAccounts = this.accountService.getAllAccounts();
                Gson gson = new Gson();
                String accountJson = gson.toJson(allAccounts);
                ctx.result(accountJson);
                ctx.status(200);
            } else {
                int num1 = Integer.parseInt(t);
                int num2 = Integer.parseInt(s);
                Set<Account> accounts = this.accountService.getAccountInRange(num1, num2);
                Gson gson = new Gson();
                String selectedAccountsJSON = gson.toJson(accounts);
                ctx.result(selectedAccountsJSON);
                ctx.status(200);
            }
        }
    };

    public Handler getAccountByIdHandler = (ctx) -> {
        int id = Integer.parseInt(ctx.pathParam("id"));
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Account account = this.accountService.getAccountById(id);
        Client client = this.clientService.getClientById(id2);
        if(account == null || client == null){
            ctx.result("Account or Client was not found");
            ctx.status(404);
        }else if (client.getClientId() == account.getClientID()){
            Gson gson = new Gson();
            String accountJson = gson.toJson(account);
            ctx.result(accountJson);
            ctx.status(200);
        } else {
            ctx.result("Client does not have that particular account");
        }
    };

    public Handler createAccountHandler = (ctx) ->{
        String body = ctx.body();
        Gson gson = new Gson();
        Account account = gson.fromJson(body, Account.class);
        this.accountService.registerAccount(account);
        String json = gson.toJson(account);
        ctx.result(json);
        ctx.status(201);
    };

    public Handler updateAccountHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Account account2 = this.accountService.getAccountById(id);
        Client client = this.clientService.getClientById(id2);

        if(account2 == null || client == null){
            ctx.result("Account or Client was not found");
            ctx.status(404);
        }else if (client.getClientId() == account2.getClientID()){
            String body = ctx.body();
            Gson gson = new Gson();
            Account account = gson.fromJson(body, Account.class);
            account.setAccountID(id);
            this.accountService.updateAccount(account);
        }else {
            ctx.result("Client does not have that particular account");
        }

    };

    public Handler deleteAccountHandler = (ctx) ->{
        int id = Integer.parseInt(ctx.pathParam("id"));
        int id2 = Integer.parseInt(ctx.pathParam("id2"));
        Account account2 = this.accountService.getAccountById(id);
        Client client = this.clientService.getClientById(id2);
        boolean result = false;
        if(account2 == null || client == null){
            ctx.result("Account or Client was not found");
            ctx.status(404);
            //ctx.result("Could not delete");
        }else if (client.getClientId() == account2.getClientID()){
            this.accountService.deleteAccounById(id);
            ctx.result("Account was deleted");
        }else {
            ctx.result("Client does not have that particular account");
        }

    };


}
