package models;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;

public class JDBCLecture {

    public static void main(String[] args) throws SQLException {

        // instantiate the config obj
        Config config = new Config();

        // set up out DB driver and make a connection
        DriverManager.registerDriver(new Driver());

        // get a connection object
        Connection conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );

        // create a statement onj
        Statement stmt = conn.createStatement();

        // execute some sort of query
        String contactsQuery = "SELECT * FROM contacts";

        ResultSet rs = stmt.executeQuery(contactsQuery);

        // display whats in the result set
        while(rs.next()){
            System.out.println(rs.getString("first_name") + " | " + rs.getString("last_name") + " | " + rs.getString("phone_number"));

        }

        // if we want to add a row to the db
            // 1. create a contact obj (bean)
            //2. use our DAO to add new contact using saveContact() method
            //3. create an sql query to insert that contact into the DB as a new row.


        Contacts clDao = DaoFactory.getContactsDao();// this data access object allows us to interact with all the contacts

        //instantiate new contact obj
        Contact bryce = new Contact(
                "Bryce",
                "Payne",
                "3618346266"
        );

        long newContactId = clDao.saveContact(bryce); //returns id of contact bryce
        Contact newlyCreatedContact = clDao.getContactById(newContactId);

        // INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('bryce', 'payne', '3618346266');

        String addContactQuery = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES('%s','%s','%s')",
                newlyCreatedContact.getFirstName(),
                newlyCreatedContact.getLastName(),
                newlyCreatedContact.getPhoneNumber()
        );

        // this is the query string being sent to sql
        System.out.println("this is the query string being sent to sql");
        System.out.println(addContactQuery);
        //now let's execute the statement to add to DB
        stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);

        long insertedRowId = 0;

        ResultSet ks = stmt.getGeneratedKeys();
        if (ks.next()){
            insertedRowId = ks.getLong(1); //save the MySQL row ID to variable
            System.out.println("The ID of the newly inserted row is: " + ks.getLong(1));
        }

        System.out.println("Before mySQL id check, " + newlyCreatedContact.getFirstName() + "'s id was: " + newlyCreatedContact.getId());
        // check to see if the id was returned or its still at the default "0"
        if (insertedRowId != 0) {
            newlyCreatedContact.setId(insertedRowId);
        }
        System.out.println("After mySQL id check, " + newlyCreatedContact.getFirstName() + "'s id was: " + newlyCreatedContact.getId());
    }
}
