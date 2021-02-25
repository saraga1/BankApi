package dev.araga.services;

import dev.araga.entities.Account;

import java.util.Set;

public interface AccountService {

    Account registerAccount(Account account);

    Set<Account> getAllAccounts();
    Set<Account> getAccountInRange(int start, int end);
    Account getAccountById(int id);

    Account updateAccount(Account account);

    Set<Account> getAccountsByClientId(int id);

    boolean deleteAccounById(int id);


}
