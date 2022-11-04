import dao.impl.DentistIDao;
import dao.impl.PatientIDao;
import entities.Dentist;
import entities.Patient;
import services.DentistService;
import services.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
   private static final Logger LOGGER = LogManager.getLogger();
   public static void main(String[] args) {
      LOGGER.info("First log. Main class running.");
      /*TODO:
      *  1. Add dependencies: log4j, h2, junit  -DONE
      *  2. Create project structure (dao, entities, services, test, logs)  -DONE
      *  3. Migrate from Log4J to Log4J2  *Append file is missing*  -DONE
      *  4. Create interface  -DONE
      *  5. Create script sql for creating database tables  -DONE
      *  5. Create interface implementations (DentistIDao, PatientIDao) *Update is missing*  -IN PROGRESS
      *  6. Create services classes (DentistService, PatientService)  -DONE
      *  7. Create Dentist, Patient and services objects in Main  *CRUD works* -DONE
      *  8. Add and use loggings  -DONE
      *  9. Create tests for services classes  -IN PROGRESS
      *        - create()  -DONE
      *  10. Migrate to Maven project -DONE
      * */

      // DENTISTS AND PATIENTS
      Dentist dentist1;
      dentist1 = new Dentist(1L, "Walter", "White", "09qsbn");
      Dentist dentist2;
      dentist2 = new Dentist(2L, "Saul", "Goodman", "lwyrup");
      Dentist dentist3;
      dentist3 = new Dentist(3L, "Mike", "Ehrmantraut", "vr57g1");

      Patient patient1;
      patient1 = new Patient(1L, "Jesse", "Pinkman", "325 Terrace St SE, Albuquerque", "1013691", "2022-11-03");
      Patient patient2;
      patient2 = new Patient(2L, "Gustavo", "Fring", "1213 Jefferson St NE, Albuquerque", "5247843", "2022-12-06");

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
      dentistService.createDentist(dentist3);
      patientService.createPatient(patient1);
      patientService.createPatient(patient2);

      // READ
      dentistService.readDentist(1L);
      dentistService.readDentist(2L);
      dentistService.readDentist(3L);
      patientService.readPatient(1L);
      patientService.readPatient(2L);

      /*UPDATE // TODO
      dentistService.createDentist(dentist2);
      patientService.createPatient(patient1);*/

      // DELETE
      dentistService.deleteDentist(2L);
   }
}