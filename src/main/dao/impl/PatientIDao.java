package main.dao.impl;

import main.dao.IDao;
import main.entities.Patient;
import java.sql.*;

public class PatientIDao implements IDao<Patient> {
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

   // CRUD METHODS
   @Override
   public Patient create(Patient patient) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement;

      // Query to create a Patient
      final String SQL_INSERT = """
             INSERT INTO Patients(id, name, lastname, address, dni, discharge_date)
             VALUES(?, ?, ?, ?, ?, ?);
             """;

      // Connect to Driver
      try {
         connection = getConnection();
         connection.setAutoCommit(false);

         // Create and use preparedStatement to create a Patient
         preparedStatement = connection.prepareStatement(SQL_INSERT);

         // Insert data to table Patients
         preparedStatement.setLong(1, patient.getId());
         preparedStatement.setString(2, patient.getName());
         preparedStatement.setString(3, patient.getLastName());
         preparedStatement.setString(4, patient.getAddress());
         preparedStatement.setString(5, patient.getDni());
         preparedStatement.setString(6, patient.getDischargeDate());

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
      return patient;
   }

   @Override
   public Patient read(Long id) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement;
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

            System.out.printf("""
                    |  id  |  Name  | Lastname  |  Address  |  DNI  |  Discharge_Date  |
                    |  %d  |  %s  |  %s  |  %s  |  %s  |  %s  |%n
                    """,
                   result.getLong(1), result.getString(2),
                   result.getString(3), result.getString(4),
                   result.getString(5), result.getString(6));
         }

         connection.commit();
         connection.setAutoCommit(true);

      }catch (Exception e) {
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
      return patient;
   }

   @Override // TODO
   public boolean update(Patient patient) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement;
      return false;
   }

   @Override
   public void delete(Long id) {
      // Create connection and preparedStatement
      Connection connection = null;
      PreparedStatement preparedStatement;

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