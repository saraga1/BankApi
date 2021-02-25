package dev.araga.services;

import dev.araga.daos.AccountDAO;
import dev.araga.entities.Account;

import java.util.HashSet;
import java.util.Set;

public class AccountServiceImpl implements AccountService {

    private AccountDAO adao;


    //Dependency injection
    //A service is created by passing in the dependency it needs
    public AccountServiceImpl(AccountDAO adao) {
        this.adao = adao;
    }

    @Override
    public Account registerAccount(Account account) {
        account.setAccountActive(true);
        this.adao.createAccount(account);
        return account;
    }

    @Override
    public Set<Account> getAllAccounts() {
        return this.adao.getAllAccounts();
    }

    @Override
    public Set<Account> getAccountInRange(int start, int end) {
        Set<Account> getAllAcc = this.adao.getAllAccounts();
        Set<Account> accountsClient = new HashSet<Account>();

        for(Account account : getAllAcc ){
           if((account.getAccountBalance() > start) && (account.getAccountBalance() < end)){
               accountsClient.add(account);
           }
        }
        return accountsClient;
    }

    @Override
    public Account getAccountById(int id) {
        return this.adao.getAccountById(id);
    }

    @Override
    public Account updateAccount(Account account) {
        return this.adao.updateAccount(account);
    }

    @Override
    public Set<Account> getAccountsByClientId(int id) {
        Set<Account> getAllAcc = this.adao.getAllAccounts();
        Set<Account> accountsClient = new HashSet<Account>();

        for(Account account : getAllAcc ){
            if(id == account.getClientID()) {
                accountsClient.add(account);
            }
        }
        return accountsClient;

    }

    @Override
    public boolean deleteAccounById(int id) {
        return this.adao.deleteAccountById(id);
    }
}
