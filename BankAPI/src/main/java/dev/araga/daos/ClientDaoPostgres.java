package dev.araga.daos;

import dev.araga.entities.Client;
import dev.araga.utils.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDaoPostgres implements ClientDAO{

    private Logger logger = Logger.getLogger(ClientDaoPostgres.class.getName());

    @Override
    public Client createClient(Client client) {

        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "insert into client (client_fname, client_lname, client_state) values (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, client.getFname());
            ps.setString(2, client.getLname());
            ps.setString(3, client.getClientState());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("client_id");
            client.setClientId(key);

            return client;

        }catch (SQLException sqlException){
           sqlException.printStackTrace();
           logger.error("unable to create client",sqlException);
            return null;
        }

    }

    @Override
    public Set<Client> getAllClient() {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from client";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs =  ps.executeQuery();
            Set<Client> clients = new HashSet<Client>();

            while(rs.next()){
                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));
                client.setFname(rs.getString("client_fname"));
                client.setLname(rs.getString("client_lname"));
                client.setClientState(rs.getString("client_state"));
                clients.add(client);
            }
            return clients;

        }catch (SQLException sqlException){
            logger.error("unable to get all client",sqlException);
            sqlException.printStackTrace();
            return null;

        }
    }

    @Override
    public Client getClientById(int id) {


        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "select * from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs =  ps.executeQuery();
            rs.next();

                Client client = new Client();
                client.setClientId(rs.getInt("client_id"));
                client.setFname(rs.getString("client_fname"));
                client.setLname(rs.getString("client_lname"));
                client.setClientState(rs.getString("client_state"));
            return client;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to get client by id",sqlException);
            return null;
        }
    }

    @Override
    public Client updateClient(Client client) {

        try(Connection conn = ConnectionUtil.createConnection()){

            String sql = "update client set client_fname = ?, client_lname = ?, client_state = ? where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, client.getFname());
            ps.setString(2, client.getLname());
            ps.setString(3, client.getClientState());
            ps.setInt(4,client.getClientId());
            ps.executeUpdate();

            return client;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to update client",sqlException);
            return null;
        }

    }

    @Override
    public boolean deleteClientById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()){
            String sql = "delete from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.execute();
            return true;

        }catch (SQLException sqlException){
            sqlException.printStackTrace();
            logger.error("unable to delete client",sqlException);
            return false;
        }
    }


}
