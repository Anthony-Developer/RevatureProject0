package dev.ajimenez.daos.Clients;

import dev.ajimenez.entities.Client;
import dev.ajimenez.utils.ConnectionUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientDaoPostgres implements ClientDao {

    @Override
    public Client createClient(Client client) {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "insert into client (first_name, last_name, address, city_state, date_of_birth, credit_score) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Putting data for all the question marks as actual values
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getCityState());
            ps.setString(5, client.getDob());
            ps.setInt(6, client.getCreditScore());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int key = rs.getInt("client_id");
            client.setClientID(key);

            return client;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();

            return null;
        }
    }

    @Override
    public Set<Client> getAllClients() {
        try (Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from client";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Set<Client> clients = new HashSet<Client>();

            while (rs.next()) {
                Client client = new Client();
                client.setClientID(rs.getInt("client_id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setAddress(rs.getString("address"));
                client.setCityState(rs.getString("city_state"));
                client.setDob(rs.getString("date_of_birth"));
                client.setCreditScore(rs.getInt("credit_score"));
                clients.add(client);
            }
            return clients;

        } catch (SQLException sqlException) {

        }
        return null;
    }

    @Override
    public Client getClientById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "select * from client where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Client client = new Client();
            client.setClientID(rs.getInt("client_id"));
            client.setFirstName(rs.getString("first_name"));
            client.setLastName(rs.getString("last_name"));
            client.setAddress(rs.getString("address"));
            client.setCityState(rs.getString("city_state"));
            client.setDob(rs.getString("date_of_birth"));
            client.setCreditScore(rs.getInt("credit_score"));

            return client;
        } catch (SQLException sqlException) {

        }
        return null;
    }

    @Override
    public Client updateClient(Client client) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            // SQL using nice prepared statements, you can write a string and just update the string directly with your values
            String sql = "update client set first_name = ?, last_name = ?, address = ?, city_state = ?, date_of_birth = ?, credit_score = ? where client_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setString(3, client.getAddress());
            ps.setString(4, client.getCityState());
            ps.setString(5, client.getDob());
            ps.setInt(6, client.getCreditScore());
            ps.setInt(7, client.getClientID());
            ps.executeUpdate();

            return client;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteClientById(int id) {
        try(Connection conn = ConnectionUtil.createConnection()) {
            String sql = "delete from client where client_id = ?";
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
