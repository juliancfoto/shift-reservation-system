package com.dentalclinic.dao.impl;

import com.dentalclinic.dao.IDao;
import com.dentalclinic.entities.Dentist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DentistIDao implements IDao<Dentist> {
   // Attributes
   private static final Logger LOGGER = LogManager.getLogger();

   // Connection data
   private final static String DB_DRIVER = "org.h2.Driver";
   private final static String DB_URL = "jdbc:h2:~/shift_reservation_system;INIT=RUNSCRIPT FROM 'create.sql'";
   private final static String DB_USER = "root";
   private final static String DB_PASS = "root";

   // Constructor
   public DentistIDao() {
      LOGGER.info("Dentists database created.");
   }

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
             INSERT INTO Dentists(name, lastname, license)
             VALUES(?, ?, ?);
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to create a Dentist
         preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

         // Insert data to table Dentists
         preparedStatement.setString(1, dentist.getName());
         preparedStatement.setString(2, dentist.getLastName());
         preparedStatement.setString(3, dentist.getLicense());

         // Execute and commit
         preparedStatement.executeUpdate();

         // Get keys to show JSON result with correct ID
         ResultSet keys = preparedStatement.getGeneratedKeys();

         while (keys.next()) {
            dentist.setId(keys.getLong(1));
         }

         connection.commit();
         connection.setAutoCommit(true);

      } catch (Exception e) {
         // Rollback to undo changes in case of failure
        try {
           assert connection != null;
           connection.rollback();
        } catch (SQLException exc) {
           LOGGER.error(exc.getMessage());
        }

      } finally {
         // Close connection
         try {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
         } catch (SQLException e) {
            LOGGER.error(e.getMessage());
         }
      }
      LOGGER.info("Dentist created.");
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

            LOGGER.info("Reading Dentist with id = " + id + "...");

            System.out.printf("""
                   |  id  |  Name  |  Lastname  |  License  |
                   |  %d  |  %s  |  %s  |  %s  |%n
                   """,
                   result.getLong(1), result.getString(2),
                   result.getString(3), result.getString(4));
         }
         result.close();

         connection.commit();
         connection.setAutoCommit(true);

      } catch (Exception e) {
         // Rollback to undo changes in case of failure
         try {
            assert connection != null;
            connection.rollback();
         } catch (SQLException exc) {
            LOGGER.error(exc.getMessage());
         }

      } finally {
         // Close connection
         try {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
         } catch (SQLException e) {
            LOGGER.error(e.getMessage());
         }
      }
      return dentist;
   }

   @Override
   public List<Dentist> readAll() {
      // Dentists list
      List<Dentist> dentists = new ArrayList<>();

      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      Dentist dentist = null;

      // Query to read/search all Dentists
      final String SQL_SEARCH_ALL = """
             SELECT * FROM Dentists;
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to search all Dentists
         preparedStatement = connection.prepareStatement(SQL_SEARCH_ALL);

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

            // Add each dentist to the dentists list
            dentists.add(dentist);

            LOGGER.info("Reading all Dentists...");

            System.out.printf("""
                   |  id  |  Name  |  Lastname  |  License  |
                   |  %d  |  %s  |  %s  |  %s  |%n
                   """,
                   result.getLong(1), result.getString(2),
                   result.getString(3), result.getString(4));
         }
         result.close();

         connection.commit();
         connection.setAutoCommit(true);

      } catch (Exception e) {
         // Rollback to undo changes in case of failure
         try {
            assert connection != null;
            connection.rollback();
         } catch (SQLException exc) {
            LOGGER.error(exc.getMessage());
         }

      } finally {
         // Close connection
         try {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
         } catch (SQLException e) {
            LOGGER.error(e.getMessage());
         }
      }
      return dentists;
   }

   @Override
   public Dentist update(Dentist dentist) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Query to update Dentist data
      final String SQL_UPDATE = """
             UPDATE Dentists
             SET name = ?, lastname = ?, license = ?
             WHERE id = ?
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Use preparedStatement to update a Dentist
         preparedStatement = connection.prepareStatement(SQL_UPDATE);

         // Update from table Dentists
         preparedStatement.setString(1, dentist.getName());
         preparedStatement.setString(2, dentist.getLastName());
         preparedStatement.setString(3, dentist.getLicense());
         preparedStatement.setLong(4, dentist.getId());

         // Execute and commit
         preparedStatement.executeUpdate();
         connection.commit();
         connection.setAutoCommit(true);
         LOGGER.info("Dentist with id = " + dentist.getId() + " updated.");

      } catch (Exception e) {
         // Rollback to undo changes in case of failure
         try {
            assert connection != null;
            connection.rollback();
         } catch (SQLException exc) {
            LOGGER.error(exc.getMessage());
         }

      } finally {
         // Close connection
         try {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
         } catch (SQLException e) {
            LOGGER.error(e.getMessage());
         }
      }
      return dentist;
   }

   @Override
   public void delete(Long id) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

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
         LOGGER.info("Dentist with id = " + id + " deleted.");

      } catch (Exception e) {
         // Rollback to undo changes in case of failure
         try {
            assert connection != null;
            connection.rollback();
         } catch (SQLException exc) {
            LOGGER.error(exc.getMessage());
         }

      } finally {
         // Close connection
         try {
            assert preparedStatement != null;
            preparedStatement.close();
            connection.close();
         } catch (SQLException e) {
            LOGGER.error(e.getMessage());
         }
      }
   }
}
