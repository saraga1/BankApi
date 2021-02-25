package dev.araga.servicetests;


import dev.araga.daos.AccountDaoPostgres;
import dev.araga.entities.Account;
import dev.araga.services.AccountService;
import dev.araga.services.AccountServiceImpl;
import org.junit.jupiter.api.*;

import java.util.HashSet;
import java.util.Set;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {


    private static AccountService aserv = new AccountServiceImpl(new AccountDaoPostgres());
    private static Account testAccount = null;


    @Test
    @Order(1)
    public void register_account(){
        Account ac1 = new Account(0,"Checking",false, 0,2);
        aserv.registerAccount(ac1);
        testAccount = ac1;
        System.out.println(ac1);
        boolean checkBool = ac1.isAccountActive();

        Assertions.assertTrue(true, String.valueOf(checkBool));
    }

    @Test
    @Order(2)
    public void get_account_in_range (){
        Account ac1 = new Account(0,"Checking",false, 100,2);
        Account ac2 = new Account(0,"Checking",false, 500,4);
        Account ac3 = new Account(0,"Checking",false, 1000,6);
        Account ac4 = new Account(0,"Checking",false, 300,5);
        aserv.registerAccount(ac1);
        aserv.registerAccount(ac2);
        aserv.registerAccount(ac3);
        aserv.registerAccount(ac4);

        Set<Account> accountCheck =  aserv.getAccountInRange(5, 600);
        System.out.println(accountCheck.size());


    }

    @Test
    @Order(3)
    public void accounts_by_clientId(){

        Account ac1 = new Account(0,"Checking",false, 100,2);
        Account ac2 = new Account(0,"Checking",false, 500,4);
        Account ac3 = new Account(0,"Checking",false, 1000,6);
        Account ac4 = new Account(0,"Checking",false, 300,5);
        aserv.registerAccount(ac1);
        aserv.registerAccount(ac2);
        aserv.registerAccount(ac3);
        aserv.registerAccount(ac4);

        Set<Account> accountCheck =  aserv.getAccountsByClientId(5);
        System.out.println(accountCheck.size());
    }


//    @Test
//    @Order(4)
//    public void get_account_byId(){
//        int id = testAccount.getAccountID();
//        Account account = aserv.getAccountById(id);
//        Assertions.assertEquals(testAccount.getTypeOfAccount(), account.getTypeOfAccount());
//    }

//    @Test
//    @Order(5)
//    public void update_account(){
//
//        Account account = aserv.getAccountById(testAccount.getAccountID());
//        System.out.println(testAccount.getAccountBalance());
//        account.setAccountBalance(100222);
//        aserv.updateAccount(account);
//
//        Account updatedAccount = aserv.getAccountById((testAccount.getAccountID()));
//        System.out.println(testAccount.getAccountBalance());
//        System.out.println(aserv.toString());
//        System.out.println(updatedAccount.getAccountBalance());
//        Assertions.assertNotEquals(testAccount.getAccountBalance(), account.getAccountBalance());
//
//    }
    @Test
    @Order(6)
    public void get_all_accounts(){
        Account ac1 = new Account(0,"Checking",false, 100,2);
        Account ac2 = new Account(0,"Checking",false, 500,4);
        Account ac3 = new Account(0,"Checking",false, 1000,6);
        Account ac4 = new Account(0,"Checking",false, 300,5);
        aserv.registerAccount(ac1);
        aserv.registerAccount(ac2);
        aserv.registerAccount(ac3);
        aserv.registerAccount(ac4);

        Set<Account> allAccounts = aserv.getAllAccounts();
        Assertions.assertTrue(allAccounts.size()>2);

    }
//
//    @Test
//    @Order(7)
//    public void delete_account_byId(){
//        int id = testAccount.getAccountID();
//        boolean deleted =aserv.deleteAccounById(id);
//        Assertions.assertTrue(deleted);
//    }


}
