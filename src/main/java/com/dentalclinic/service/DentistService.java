package com.dentalclinic.service;

import com.dentalclinic.entity.Dentist;

import java.util.List;

public interface DentistService {
   // CRUD: Create | Read | Update | Delete
   Dentist createDentist(Dentist dentist);
   Dentist readDentist(Long id);
   List<Dentist> readAllDentists();
   Dentist updateDentist(Dentist dentist);
   void deleteDentist(Long id);

}
