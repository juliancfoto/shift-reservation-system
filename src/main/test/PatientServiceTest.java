package main.test;

import main.dao.impl.PatientIDao;
import main.entities.Patient;
import main.services.PatientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientServiceTest {

   @Test
   @DisplayName("Create test.")
   void createPatient() {
      // GIVEN
      PatientService patientService = new PatientService();
      Patient patientTest;
      patientTest = new Patient(1L, "Jesse", "Pinkman", "325 Terrace St SE, Albuquerque", "1013691", "2022-11-03");

      // WHEN
      patientService.setPatientIDao(new PatientIDao());

      // THEN
      assertInstanceOf(Patient.class, patientService.createPatient(patientTest));
   }

   @Test
   void readPatient() {
   }

   @Test
   void updatePatient() {
   }

   @Test
   void deletePatient() {
   }
}