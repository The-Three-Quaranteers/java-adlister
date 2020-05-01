package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection = null;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = null;

        try {
            String query = "SELECT * FROM users WHERE username LIKE ?";
            String searchTermWithWildcards = "%" + username + "%";

            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, searchTermWithWildcards);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user = new User(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println("There was an error finding that user!");
        }
        return user;
    }

    @Override
    public Long insert(User user) {
        long output = -1;
        try {
            PreparedStatement stmt = createInsertQuery(user);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            output =  rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new user.", e);
        }
        return output;
    }

    private PreparedStatement createInsertQuery(User user) {
        PreparedStatement stmt = null;
        try {
            String sql = "INSERT INTO users(username, email, password) VALUES (?,?,?)";
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());

        } catch (SQLException e) {
            System.out.println("There was an error!");
        }
        return stmt;
    }
}
