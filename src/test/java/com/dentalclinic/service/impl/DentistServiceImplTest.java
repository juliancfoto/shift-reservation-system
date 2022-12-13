package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Dentist;
import com.dentalclinic.repository.DentistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceImplTest {
   private final DentistRepository dentistRepository;

   // DEPENDENCY INJECTION
   @Autowired
   DentistServiceImplTest(DentistRepository dentistRepository) {
      this.dentistRepository = dentistRepository;
   }

   @Test
   @DisplayName("createDentist() test.")
   void createDentist() {
      // GIVEN
      Dentist dentistTest = new Dentist("name_test", "lastname_test", "license_test");

      // WHEN
      dentistRepository.save(dentistTest);

      // THEN
      assertInstanceOf(Dentist.class, dentistTest);
   }

   @Test
   @DisplayName("readDentist() test.")
   void readDentist() {
      // GIVEN
      Dentist dentistTest = new Dentist("name_test", "lastname_test", "license_test");

      // WHEN
      dentistRepository.save(dentistTest);

      Optional<Dentist> tested = dentistRepository.findById(dentistTest.getId());

      // THEN
      if (tested.isPresent()) {
         assertInstanceOf(Dentist.class, dentistTest);

      }
   }
}