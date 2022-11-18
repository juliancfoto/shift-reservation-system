package com.dentalclinic.dao.impl;

import com.dentalclinic.dao.IDao;
import com.dentalclinic.entities.Patient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientIDao implements IDao<Patient> {
   // Attributes
   private static final Logger LOGGER = LogManager.getLogger();

   // Connection data
   private final static String DB_DRIVER = "org.h2.Driver";
   private final static String DB_URL = "jdbc:h2:~/shift_reservation_system;INIT=RUNSCRIPT FROM 'create.sql'";
   private final static String DB_USER = "root";
   private final static String DB_PASS = "root";

   // Constructor
   public PatientIDao() {
      LOGGER.info("Patients database created.");
   }

   // Methods
   // Connect to H2 Driver
   private Connection getConnection() throws Exception {
      Class.forName(DB_DRIVER).getDeclaredConstructor().newInstance();
      return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
   }

   // CRUD METHODS
   @Override
   public Patient create(Patient patient) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Query to create a Patient
      final String SQL_INSERT = """
             INSERT INTO Patients(name, lastname, address, dni, discharge_date)
             VALUES(?, ?, ?, ?, ?);
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to create a Patient
         preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

         // Insert data to table Patients
         preparedStatement.setString(1, patient.getName());
         preparedStatement.setString(2, patient.getLastName());
         preparedStatement.setString(3, patient.getAddress());
         preparedStatement.setString(4, patient.getDni());
         preparedStatement.setString(5, patient.getDischargeDate());

         // Execute and commit
         preparedStatement.executeUpdate();

         // Get keys to show JSON result with correct ID
         ResultSet keys = preparedStatement.getGeneratedKeys();

         while (keys.next()) {
            patient.setId(keys.getLong(1));
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
      LOGGER.info("Patient created.");
      return patient;
   }

   @Override
   public Patient read(Long id) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      Patient patient = null;

      // Query to read/search a Patient
      final String SQL_SEARCH = """
             SELECT * FROM Patients
             WHERE id = ?;
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to search a Patient
         preparedStatement = connection.prepareStatement(SQL_SEARCH);

         // Search in table Patients
         preparedStatement.setLong(1, id);

         // Execute and commit
         ResultSet result = preparedStatement.executeQuery();

         while (result.next()) {
            patient = new Patient();
            Long patientId = result.getLong("id");
            String patientName = result.getString("Name");
            String patientLastName = result.getString("Lastname");
            String patientAddress = result.getString("Address");
            String patientDni = result.getString("DNI");
            String patientDischargeDate = result.getString("Discharge_Date");

            patient.setId(patientId);
            patient.setName(patientName);
            patient.setLastName(patientLastName);
            patient.setAddress(patientAddress);
            patient.setDni(patientDni);
            patient.setDischargeDate(patientDischargeDate);

            LOGGER.info("Reading Patient with id = " + id + "...");

            System.out.printf("""
                    |  id  |  Name  | Lastname  |  Address  |  DNI  |  Discharge_Date  |
                    |  %d  |  %s  |  %s  |  %s  |  %s  |  %s  |%n
                    """,
                   result.getLong(1), result.getString(2),
                   result.getString(3), result.getString(4),
                   result.getString(5), result.getString(6));
         }
         result.close();

         connection.commit();
         connection.setAutoCommit(true);

      }catch (Exception e) {
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
      return patient;
   }

   @Override
   public List<Patient> readAll() {
      // Patients list
      List<Patient> patients = new ArrayList<>();

      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;
      Patient patient = null;

      // Query to read/search all Patients
      final String SQL_SEARCH_ALL = """
             SELECT * FROM Patients;
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
            patient = new Patient();
            Long patientId = result.getLong("id");
            String patientName = result.getString("Name");
            String patientLastName = result.getString("Lastname");
            String patientAddress = result.getString("Address");
            String patientDni = result.getString("DNI");
            String patientDischargeDate = result.getString("Discharge_Date");

            patient.setId(patientId);
            patient.setName(patientName);
            patient.setLastName(patientLastName);
            patient.setAddress(patientAddress);
            patient.setDni(patientDni);
            patient.setDischargeDate(patientDischargeDate);

            // Add each patient to the patients list
            patients.add(patient);

            LOGGER.info("Reading all Patients...");

            System.out.printf("""
                    |  id  |  Name  | Lastname  |  Address  |  DNI  |  Discharge_Date  |
                    |  %d  |  %s  |  %s  |  %s  |  %s  |  %s  |%n
                    """,
                   result.getLong(1), result.getString(2),
                   result.getString(3), result.getString(4),
                   result.getString(5), result.getString(6));
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
      return patients;
   }

   @Override
   public Patient update(Patient patient) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Query to update Patient data
      final String SQL_UPDATE = """
             UPDATE Patients
             SET name = ?, lastname = ?, address = ?, dni = ?, discharge_date = ?
             WHERE id = ?;
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Use preparedStatement to update a Patient
         preparedStatement = connection.prepareStatement(SQL_UPDATE);

         // Update from table Patients
         preparedStatement.setString(1, patient.getName());
         preparedStatement.setString(2, patient.getLastName());
         preparedStatement.setString(3, patient.getAddress());
         preparedStatement.setString(4, patient.getDni());
         preparedStatement.setString(5, patient.getDischargeDate());
         preparedStatement.setLong(6, patient.getId());

         // Execute and commit
         preparedStatement.executeUpdate();
         connection.commit();
         connection.setAutoCommit(true);
         LOGGER.info("Patient with id = " + patient.getId() + " updated.");

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
      return patient;
   }

   @Override
   public void delete(Long id) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement = null;

      // Query to delete a Patient
      final String SQL_DELETE = """
             DELETE FROM Patients
             WHERE id = ?;
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to delete a Patient
         preparedStatement = connection.prepareStatement(SQL_DELETE);

         // Delete from table Patients
         preparedStatement.setLong(1, id);

         // Execute and commit
         preparedStatement.executeUpdate();
         connection.commit();
         connection.setAutoCommit(true);
         LOGGER.info("Patient with id = " + id + " deleted.");

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