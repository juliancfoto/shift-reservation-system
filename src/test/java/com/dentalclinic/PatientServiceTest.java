package com.dentalclinic;

import com.dentalclinic.dao.impl.PatientIDao;
import com.dentalclinic.entities.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.dentalclinic.services.PatientService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
class PatientServiceTest {
   @Test
   @DisplayName("Create test.")
   void createPatient() {
      // GIVEN
      PatientService patientService = new PatientService(new PatientIDao());
      Patient patientTest;
      patientTest = new Patient(1L, "Jesse", "Pinkman", "325 Terrace St SE, Albuquerque", "1013691", "2022-11-03");

      // WHEN
//      patientService.setPatientIDao(new PatientIDao());

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