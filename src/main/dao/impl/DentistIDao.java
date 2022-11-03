package main.dao.impl;

import main.dao.IDao;
import main.entities.Dentist;

import java.sql.*;

public class DentistIDao implements IDao<Dentist> {
   // Attributes
   // Connection data
   private final static String DB_DRIVER = "org.h2.Driver";
   private final static String DB_URL = "jdbc:h2:~/shift_reservation_system;INIT=RUNSCRIPT FROM 'create.sql'";
   private final static String DB_USER = "root";
   private final static String DB_PASS = "root";

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
      PreparedStatement preparedStatement;

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
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      Dentist dentist = null;

      // Query to read/search a Dentist
      final String SQL_SEARCH = """
             SELECT * FROM Dentists
             WHERE id = ?;
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to search a Dentist
         preparedStatement = connection.prepareStatement(SQL_SEARCH);

         // Search in table Dentists
         preparedStatement.setLong(1, id);

         // Execute and commit
         ResultSet result = preparedStatement.executeQuery();

         while (result.next()) {
            dentist = new Dentist();
            Long dentistId = result.getLong("id");
            String dentistName = result.getString("Name");
            String dentistLastName = result.getString("Lastname");
            String dentistLicense = result.getString("License");

            dentist.setId(dentistId);
            dentist.setName(dentistName);
            dentist.setLastName(dentistLastName);
            dentist.setLicense(dentistLicense);

         }

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

   @Override // TODO
   public boolean update(Dentist dentist) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Query to update Dentist data
      final String SQL_UPDATE = """
             
             """;

      return false;
   }

   @Override
   public void delete(Long id) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement;

      // Query to delete a Dentist
      final String SQL_DELETE = """
             DELETE FROM Dentists
             WHERE id = ?;
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to delete a Dentist
         preparedStatement = connection.prepareStatement(SQL_DELETE);

         // Delete from table Dentists
         preparedStatement.setLong(1, id);

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
   }
}
