package dev.araga.daotests;


import dev.araga.daos.AccountDAO;
import dev.araga.daos.AccountDaoPostgres;
import dev.araga.entities.Account;
import dev.araga.entities.Client;
import dev.araga.services.AccountService;
import dev.araga.services.AccountServiceImpl;
import org.junit.jupiter.api.*;

import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountDaoTest {
    private static AccountDAO aado = new AccountDaoPostgres();
    private static Account testAccount = null;


    @Test
    @Order(1)
    public void create_account(){
        Account ac1 = new Account(0,"Checking",false, 0,1);
        aado.createAccount(ac1);
        testAccount = ac1;
        System.out.println(ac1);
        boolean checkBool = ac1.isAccountActive();
        Assertions.assertTrue(true, String.valueOf(checkBool));
    }

    @Test
    @Order(2)
    public void get_account_byId(){
        int id = testAccount.getAccountID();
        Account account = aado.getAccountById(id);
        Assertions.assertEquals(testAccount.getTypeOfAccount(), account.getTypeOfAccount());
    }

    @Test
    @Order(3)
    public void update_account(){

          Account account = aado.getAccountById(testAccount.getAccountID());
          System.out.println(testAccount.getAccountBalance());
          account.setAccountBalance(100222);
          aado.updateAccount(account);

          Account updatedAccount = aado.getAccountById((testAccount.getAccountID()));
          System.out.println(testAccount.getAccountBalance());
          System.out.println(aado.toString());
          System.out.println(updatedAccount.getAccountBalance());
          Assertions.assertNotEquals(testAccount.getAccountBalance(), account.getAccountBalance());

    }

//    @Test
//    @Order(4)
//    public void get_all_accounts(){
//        Account ac1 = new Account(0,"Checking",false, 100,1);
//        Account ac2 = new Account(0,"Checking",false, 500,1);
//        Account ac3 = new Account(0,"Checking",false, 1000,1);
//        Account ac4 = new Account(0,"Checking",false, 300,1);
//        aado.createAccount(ac1);
//        aado.createAccount(ac2);
//        aado.createAccount(ac3);
//        aado.createAccount(ac4);
//
//        Set<Account> allAccounts = aado.getAllAccounts();
//        Assertions.assertTrue(allAccounts.size()>2);
//
//    }

    @Test
    @Order(5)
    public void delete_account_byId(){
        int id = testAccount.getAccountID();
        boolean deleted =aado.deleteAccountById(id);
        Assertions.assertTrue(deleted);
    }
}
