package models;

import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLContactDao implements Contacts {
    private Connection conn;

    public MySQLContactDao() throws SQLException {
        // instantiate the config obj
        Config config = new Config();

        // set up out DB driver and make a connection
        DriverManager.registerDriver(new Driver());

        // get a connection object
        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> output = new ArrayList<>();
        //query the SQL db table for all contacts
        String query = "SELECT * FROM contacts";

        try {
            //create statement object
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //iterate through result set and add each contact
            while (rs.next()){
                output.add(
                        new Contact(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("phone_number")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public long saveContact(Contact contact) {
        return 0;
    }

    @Override
    public void deleteContactById(long id) {

    }

    @Override
    public Contact getContactById(long id) {
        return null;
    }
}

