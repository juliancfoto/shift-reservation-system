package com.dentalclinic.service;

import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entity.Dentist;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DentistService {
   // CRUD: Create | Read | Update | Delete
   Dentist createDentist(Dentist dentist);
   DentistDTO readDentist(Long id);
   List<DentistDTO> readAllDentists();
   ResponseEntity<Dentist> updateDentist(Dentist dentist);
   void deleteDentist(Long id);

}
