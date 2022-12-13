package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Patient;
import com.dentalclinic.repository.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
class PatientServiceImplTest {
   private final PatientRepository patientRepository;

   // DEPENDENCY INJECTION
   @Autowired
   PatientServiceImplTest(PatientRepository patientRepository) {
      this.patientRepository = patientRepository;
   }


   @Test
   @DisplayName("createPatient() test.")
   void createPatient() {
      // GIVEN
      Patient patientTest = new Patient("name_test", "lastname_test", "address_test", "dni_test", "1999-05-25");

      // WHEN
      patientRepository.save(patientTest);

      // THEN
      assertInstanceOf(Patient.class, patientTest);
   }

   @Test
   void readPatient() {
      // GIVEN
      Patient patientTest = new Patient("name_test", "lastname_test", "address_test", "dni_test", "1999-05-25");

      // WHEN
      patientRepository.save(patientTest);

      Optional<Patient> tested = patientRepository.findById(patientTest.getId());

      // THEN
      if (tested.isPresent()) {
         assertInstanceOf(Patient.class, patientTest);

      }
   }
}