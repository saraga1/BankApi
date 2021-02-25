package dev.araga.entities;

//Account JavaBean
public class Account {

    private int accountID; // The unique key for specific account
    private String typeOfAccount; // The type of account CD, Saving, or Checking
    private boolean accountActive; // The amount of years the account has been active
    private long accountBalance; //The available balance in the account
    private int clientID; // Foreign Key for Client relation.


    public Account() {
    }

    public Account(int accountID, String typeOfAccount, boolean accountActive, long accountBalance,int clientID) {
        this.accountID = accountID;
        this.typeOfAccount = typeOfAccount;
        this.accountActive = accountActive;
        this.accountBalance = accountBalance;
        this.clientID = clientID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public void setAccountActive(boolean accountActive) {
        this.accountActive = accountActive;
    }

    public long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", typeOfAccount='" + typeOfAccount + '\'' +
                ", accountActive=" + accountActive +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
