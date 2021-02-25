package dev.araga.entities;

//Client JavaBean
public class Client {

    private int clientId;
    private String fname;
    private String lname;
    private String clientState;

    //No args constructor
    public Client () {
    }

    public Client(int clientId, String fnaclieme, String lname, String clientState) {
        this.clientId = clientId;
        this.fname = fname;
        this.lname = lname;
        this.clientState = clientState;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }
}
