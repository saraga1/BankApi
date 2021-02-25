package dev.araga.daos;

import dev.araga.entities.Account;

import java.util.Set;

//CRUD operations CREATE, READ, UPDATE, and DELETE
public interface AccountDAO {


    //CREATE
    Account createAccount(Account account);

    //READ
    Set<Account> getAllAccounts();
    Account getAccountById(int id);

    //UPDATE
    Account updateAccount(Account account);

    //DELETE
    boolean deleteAccountById(int id);


}
