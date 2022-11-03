package main.dao.impl;

import main.dao.IDao;
import main.entities.Dentist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DentistIDao implements IDao<Dentist> {
   // Attributes
   // Connection data
   private final static String DB_DRIVER = "org.h2.Driver";
   private final static String DB_URL = "jdbc:h2:~/shift_reservation_system;INIT=RUNSCRIPT FROM 'create.sql'";
   private final static String DB_USER = "root";
   private final static String DB_PASS = "root";

   // Query for creating table Dentists
   /*private static final String SQL_CREATE_TABLE = """
          CREATE TABLE IF NOT EXISTS Dentists(
          id INT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255) NOT NULL,
          lastname VARCHAR(255) NOT NULL,
          license VARCHAR(255) NOT NULL
          );
          """;*/

   // Methods
   // Connect to H2 Driver
   private Connection getConnection() throws Exception {
      Class.forName(DB_DRIVER).getDeclaredConstructor().newInstance();
      return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
   }

   // CRUD Methods
   @Override
   public Dentist create(Dentist dentist) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Query to create a Dentist
      final String SQL_INSERT = """
             INSERT INTO Dentists(id, name, lastname, license)
             VALUES(?, ?, ?, ?);
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to create a Dentist
         preparedStatement = connection.prepareStatement(SQL_INSERT);

         // Insert data to table Dentists
         preparedStatement.setLong(1, dentist.getId());
         preparedStatement.setString(2, dentist.getName());
         preparedStatement.setString(3, dentist.getLastName());
         preparedStatement.setString(4, dentist.getLicense());

         // Execute and commit
         preparedStatement.executeUpdate();
         connection.commit();
         connection.setAutoCommit(true);

      } catch (Exception e) {
         // Rollback to undo changes in case of failure
        try {
           assert connection != null;
           connection.rollback();
        } catch (SQLException exc) {
           throw new RuntimeException(exc);
        }

      } finally {
         // Close connection
         try {
            assert connection != null;
            connection.close();
         } catch (SQLException e) {
            throw new RuntimeException(e);
         }
      }
      return dentist;
   }

   @Override
   public Dentist read(Long id) {
      return null;
   }

   @Override
   public boolean update(Dentist dentist) {
      return false;
   }

   @Override
   public boolean delete(Long id) {
      return false;
   }
}
