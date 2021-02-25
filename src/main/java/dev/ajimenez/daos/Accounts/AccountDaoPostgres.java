package dev.ajimenez.daos.Accounts;

import dev.ajimenez.entities.Account;
import dev.ajimenez.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class AccountDaoPostgres implements AccountDao {

    @Override
    public Account createAccount(Account account) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into account (account_type, account_number, balance, client_id) values (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getAccountType());
            ps.setInt(2, account.getAccountNumber());
            ps.setFloat(3, account.getBalance());
            ps.setInt(4, account.getClientId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int key = rs.getInt("account_id");
            account.setAccountId(key);

            return account;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Set<Account> getAllAccounts() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from account";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Account> accounts = new HashSet<Account>();

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getInt("account_id"));
                account.setAccountType(rs.getString("account_type"));
                account.setAccountNumber(rs.getInt("account_number"));
                account.setBalance(rs.getFloat("balance"));
                account.setClientId(rs.getInt("client_id"));
                accounts.add(account);
            }
            return accounts;

        }  catch (SQLException sqlException) {

        }
        return null;
    }

    @Override
    public Account getAccountById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from account where client_id = ? and account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Account account = new Account();
            account.setAccountId(rs.getInt("account_id"));
            account.setAccountType(rs.getString("account_type"));
            account.setAccountNumber(rs.getInt("account_number"));
            account.setBalance(rs.getFloat("balance"));
            account.setClientId(rs.getInt("client_id"));

            return account;

        } catch (SQLException sqlException) {

        }
        return null;
    }

    @Override
    public Account updateAccount(Account account) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "update account set account_type = ?, balance = ? where client_id = ? and account_number = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getAccountType());
            ps.setFloat(2, account.getBalance());
            ps.setInt(3, account.getClientId());
            ps.setInt(4, account.getAccountNumber());
            ps.executeUpdate();

            return account;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteAccountById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from account where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();

            return true;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }
}
