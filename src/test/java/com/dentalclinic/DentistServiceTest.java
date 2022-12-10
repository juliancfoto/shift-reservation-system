package com.dentalclinic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DentistServiceTest {
   @Test
   @DisplayName("Create test.")
   void createDentist() {
      // GIVEN
      /*DentistService dentistService = new DentistService(new DentistIDao(), objectMapper);
      Dentist dentistTest;
      dentistTest = new Dentist(1L, "Walter", "White", "09qsbn");

      // WHEN
//    dentistService.setDentistIDao(new DentistIDao());

      // THEN
      assertInstanceOf(Dentist.class, dentistService.createDentist(dentistTest));
   */}

   @Test
   void readDentist() {
   }

   @Test
   void updateDentist() {
   }

   @Test
   void deleteDentist() {
   }
}