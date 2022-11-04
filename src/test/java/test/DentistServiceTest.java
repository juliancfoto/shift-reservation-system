package test;

import dao.impl.DentistIDao;
import entities.Dentist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.DentistService;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DentistServiceTest {
   @Test
   @DisplayName("Create test.")
   void createDentist() {
      // GIVEN
      DentistService dentistService = new DentistService();
      Dentist dentistTest;
      dentistTest = new Dentist(1L, "Walter", "White", "09qsbn");

      // WHEN
      dentistService.setDentistIDao(new DentistIDao());

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