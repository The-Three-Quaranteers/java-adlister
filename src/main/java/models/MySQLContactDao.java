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

        long newlyCreatedUserID = 0;

        // INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('firstName', 'lastName', 'phoneNumber');

        String addContactQuery = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES('%s','%s','%s')",
                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber()
        );

        try {
            Statement stmt = conn.createStatement();
            //now let's execute the statement to add to DB
            stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);

            ResultSet ks = stmt.getGeneratedKeys();
            if (ks.next()){
                newlyCreatedUserID = ks.getLong(1); //save the MySQL row ID to variable
            }

            // check to see if the id was returned or its still at the default "0"
            if (newlyCreatedUserID != 0) {
                contact.setId(newlyCreatedUserID);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }
        return newlyCreatedUserID;
    }

    @Override
    public void deleteContactById(long id) {
        //DELETE FROM contacts WHERE id = sentInID

        String query = String.format("DELETE FROM contacts WHERE id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            boolean success = stmt.execute(query);
            if(success){
                System.out.println("Contact has been removed.");
            } else {
                System.out.println("Contact was not removed - check for syntax errors.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Contact getContactById(long id) {

        Contact returnContact = new Contact();
        String query = String.format("SELECT * FROM contacts WHERE id = %d", id);

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()){
                returnContact.setId(id);
                returnContact.setFirstName(rs.getString("first_name"));
                returnContact.setLastName(rs.getString("last_name"));
                returnContact.setPhoneNumber(rs.getString("phone_number"));
            }else{
                System.out.println("No matching user found");

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return returnContact;
    }
}

