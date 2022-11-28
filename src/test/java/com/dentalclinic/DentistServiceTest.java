package com.dentalclinic;

import com.dentalclinic.dao.impl.DentistIDao;
import com.dentalclinic.entities.Dentist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.dentalclinic.services.DentistService;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@SpringBootTest
class DentistServiceTest {
   @Test
   @DisplayName("Create test.")
   void createDentist() {
      // GIVEN
      DentistService dentistService = new DentistService(new DentistIDao());
      Dentist dentistTest;
      dentistTest = new Dentist(1L, "Walter", "White", "09qsbn");

      // WHEN
//    dentistService.setDentistIDao(new DentistIDao());

      // THEN
      assertInstanceOf(Dentist.class, dentistService.createDentist(dentistTest));
   }

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