package dev.araga.daos;

import dev.araga.entities.Account;
import dev.araga.utils.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoPostgres implements AccountDAO {

    private Logger logger = Logger.getLogger(AccountDaoPostgres.class.getName());

    @Override
    public Account createAccount(Account account) {

        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into account (type_account, account_active, account_balance, c_id) values (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getTypeOfAccount());
            ps.setBoolean(2, account.isAccountActive());
            ps.setLong(3, account.getAccountBalance());
            ps.setInt(4, account.getClientID());
            ps.execute();

            //result set is a cursor that start before the actual first element
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int key = rs.getInt("account_id");
            account.setAccountID(key);
            return account;
        }catch(SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("unable to create account",sqlException);
            return null;
        }
    }

    @Override
    public Set<Account> getAllAccounts() {

        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from account";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Account> accounts = new HashSet<Account>();

            while(rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getInt("account_id"));
                account.setTypeOfAccount(rs.getString("type_account"));
                account.setAccountActive(rs.getBoolean("account_active"));
                account.setAccountBalance(rs.getLong("account_balance"));
                account.setClientID(rs.getInt("c_id"));
                accounts.add(account);
            }
            return accounts;

        }catch(SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("unable to get all accounts",sqlException);
            return null;
        }
    }

    @Override
    public Account getAccountById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from account where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            rs.next();

                Account account = new Account();
                account.setAccountID(rs.getInt("account_id"));
                account.setTypeOfAccount(rs.getString("type_account"));
                account.setAccountActive(rs.getBoolean("account_active"));
                account.setAccountBalance(rs.getLong("account_balance"));
                account.setClientID(rs.getInt("c_id"));


            return account;

        }catch(SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("unable to get account by id",sqlException);
            return null;
        }
    }

    @Override
    public Account updateAccount(Account account) {

        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update account set type_account = ?, account_active = ?, account_balance = ?, c_id = ? where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getTypeOfAccount());
            ps.setBoolean(2, account.isAccountActive());
            ps.setLong(3, account.getAccountBalance());
            ps.setInt(4, account.getClientID());
            ps.setInt(5, account.getAccountID());
            ps.executeUpdate();


            return account;
        }catch(SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("unable to update account",sqlException);
            return null;
        }
    }

    @Override
    public boolean deleteAccountById(int id) {

        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from account where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);;
            ps.setInt(1, id);
            ps.execute();
            return true;

        }catch(SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("unable to delete Account",sqlException);
            return false;
        }
    }
}
