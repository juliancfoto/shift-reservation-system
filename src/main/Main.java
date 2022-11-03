package main;

import main.dao.impl.DentistIDao;
import main.dao.impl.PatientIDao;
import main.entities.Dentist;
import main.entities.Patient;
import main.services.DentistService;
import main.services.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
   private static final Logger LOGGER = LogManager.getLogger();
   public static void main(String[] args) {
      LOGGER.info("First log. Main class running.");
      /*TODO:
      *  1. Add dependencies: log4j, h2, junit  -DONE
      *  2. Create project structure (dao, entities, services, test, logs)  -DONE
      *  3. Migrate from Log4J to Log4J2  -DONE
      *  4. Create interface  -DONE
      *  5. Create script sql for creating database tables  -DONE
      *  5. Create interface implementations (DentistIDao, PatientIDao) *Update is missing*  -IN PROGRESS
      *  6. Create services classes (DentistService, PatientService)  -DONE
      *  7. Create Dentist, Patient and services objects in Main  *CRUD works* -DONE
      *  8. Add and use loggings  -DONE
      *  9. Create tests for services  --IN PROGRESS
      * */

      // DENTISTS AND PATIENTS
      Dentist dentist1;
      dentist1 = new Dentist(1L, "Julian", "Caicedo", "abc123");
      Dentist dentist2;
      dentist2 = new Dentist(2L, "Maria", "Abella", "def456");

      Patient patient1;
      patient1 = new Patient(1L, "Mateo", "Ruiz", "Cll 8 sur", "1013691", "2022-11-03");
      Patient patient2;
      patient2 = new Patient(2L, "Jaime", "Cardenas", "Cll 16 sur", "1013653", "2022-12-06");

      // SERVICES
      DentistService dentistService = new DentistService();
      PatientService patientService = new PatientService();

      // SET DAO TO BE USED FOR EACH SERVICE
      dentistService.setDentistIDao(new DentistIDao());
      patientService.setPatientIDao(new PatientIDao());

      // USING DATABASE
      // CREATE
      dentistService.createDentist(dentist1);
      dentistService.createDentist(dentist2);
      patientService.createPatient(patient1);
      patientService.createPatient(patient2);

      // READ
      dentistService.readDentist(1L);
      dentistService.readDentist(2L);
      patientService.readPatient(1L);
      patientService.readPatient(2L);

      /*UPDATE // TODO
      dentistService.createDentist(dentist2);
      patientService.createPatient(patient1);*/

      // DELETE
//      dentistService.deleteDentist(2L);

   }
}
