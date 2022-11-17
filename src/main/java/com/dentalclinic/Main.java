package com.dentalclinic;

import com.dentalclinic.dao.impl.DentistIDao;
import com.dentalclinic.dao.impl.PatientIDao;
import com.dentalclinic.entities.Dentist;
import com.dentalclinic.entities.Patient;
import com.dentalclinic.services.DentistService;
import com.dentalclinic.services.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
   private static final Logger LOGGER = LogManager.getLogger();
   public static void main(String[] args) {
      LOGGER.info("First log. com.dentalclinic.Main class running.");
      /*TODO:
      *  1. Add dependencies: log4j, h2, junit  -DONE
      *  2. Create project structure (com.dentalclinic.dao, com.dentalclinic.entities, com.dentalclinic.services, test, logs)  -DONE
      *  3. Migrate from Log4J to Log4J2  *Append file is missing*  -DONE
      *  4. Create interface  -DONE
      *  5. Create script sql for creating database tables  -DONE
      *  5. Create interface implementations (DentistIDao, PatientIDao) -DONE
      *  6. Create com.dentalclinic.services classes (DentistService, PatientService)  -DONE
      *  7. Create Dentist, Patient and com.dentalclinic.services objects in com.dentalclinic.Main  *CRUD works* -DONE
      *  8. Add and use loggings  -DONE
      *  9. Create tests for com.dentalclinic.services classes  -IN PROGRESS
      *        - create()  -DONE
      *  10. Migrate to Maven project -DONE
      *  11. Migrate to Springboot project  -DONE
      *  12. Create Controllers classes (DentistController, PatientController) -IN PROGRESS
      *        - read() and update()  -DONE
      * */

      // DENTISTS AND PATIENTS
      Dentist dentist1;
      dentist1 = new Dentist("Walter", "White", "09qsbn");
      Dentist dentist2;
      dentist2 = new Dentist("Saul", "Goodman", "lwyrup");
      Dentist dentist3;
      dentist3 = new Dentist("Mike", "Ehrmantraut", "vr57g1");

      Patient patient1;
      patient1 = new Patient("Jesse", "Pinkman", "325 Terrace St SE, Albuquerque", "1013691", "2022-11-03");
      Patient patient2;
      patient2 = new Patient("Gustavo", "Fring", "1213 Jefferson St NE, Albuquerque", "5247843", "2022-12-06");
      Patient patient3;
      patient3 = new Patient("Mateo", "Ruiz", "1213 Jefferson St NE, Albuquerque", "5247843", "2022-12-06");

      // SERVICES
      DentistService dentistService = new DentistService();
      PatientService patientService = new PatientService();

      // SET DAO TO BE USED FOR EACH SERVICE
      dentistService.setDentistIDao(new DentistIDao());
      patientService.setPatientIDao(new PatientIDao());

      // USING DATABASE
      // CREATE
      /*dentistService.createDentist(dentist1);
      dentistService.createDentist(dentist2);
      dentistService.createDentist(dentist3);
      patientService.createPatient(patient1);
      patientService.createPatient(patient2);
      patientService.createPatient(patient3);

      // READ
      dentistService.readDentist(1L);
      dentistService.readDentist(2L);
      dentistService.readDentist(3L);
      patientService.readPatient(1L);
      patientService.readPatient(2L);*/

      // READ ALL
      dentistService.readAllDentists();
      patientService.readAllPatients();

      // UPDATE
      /*Dentist dentistToUpdate = new Dentist();
      Patient patientToUpdate = new Patient();
      dentistService.updateDentist(dentistToUpdate);
      patientService.updatePatient(patientToUpdate);*/

      // DELETE
      /*dentistService.deleteDentist(2L);*/
   }
}