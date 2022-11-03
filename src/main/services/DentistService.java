package main.services;

import main.dao.IDao;
import main.entities.Dentist;

public class DentistService {
   // Attributes
   private IDao<Dentist> dentistIDao;

   // Methods
   public Dentist createDentist(Dentist dentist) {
      // Using DAO interface to create a Dentist
      return dentistIDao.create(dentist);
   }

   public Dentist readDentist(Long id) {
      // Using DAO interface to read/search a Dentist
      return dentistIDao.read(id);
   }

   public boolean updateDentist(Dentist dentist) {
      // Using DAO interface to update Dentist data
      return dentistIDao.update(dentist);
   }

   public void deleteDentist(Long id) {
      // Using DAO interface to delete a Dentist
      dentistIDao.delete(id);
   }

   // Getters and setters
   public void setDentistIDao(IDao<Dentist> dentistIDao) {
      this.dentistIDao = dentistIDao;
   }
}
