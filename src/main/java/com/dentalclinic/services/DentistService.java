package com.dentalclinic.services;

import com.dentalclinic.dao.IDao;
import com.dentalclinic.entities.Dentist;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DentistService {
   // Attributes
   private IDao<Dentist> dentistIDao;

   public DentistService(IDao<Dentist> dentistIDao) {
      this.dentistIDao = dentistIDao;
   }

   // Methods
   public Dentist createDentist(Dentist dentist) {
      // Using DAO interface to create a Dentist
      return dentistIDao.create(dentist);
   }

   public Dentist readDentist(Long id) {
      // Using DAO interface to read/search a Dentist
      return dentistIDao.read(id);
   }

   public List<Dentist> readAllDentists() {
      // Using DAO interface to read/search all Dentists
      return dentistIDao.readAll();
   }

   public Dentist updateDentist(Dentist dentist) {
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
