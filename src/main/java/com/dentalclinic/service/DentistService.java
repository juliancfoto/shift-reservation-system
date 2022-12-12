package com.dentalclinic.service;

import com.dentalclinic.entity.Dentist;
import com.dentalclinic.exception.BadRequestException;
import com.dentalclinic.exception.ResourceNotFoundException;

import java.util.List;

public interface DentistService {
   // CRUD: Create | Read | Update | Delete
   Dentist createDentist(Dentist dentist);
   Dentist readDentist(Long id) throws ResourceNotFoundException;
   List<Dentist> readAllDentists() throws ResourceNotFoundException;
   Dentist updateDentist(Dentist dentist) throws ResourceNotFoundException;
   void deleteDentist(Long id) throws ResourceNotFoundException, BadRequestException;
}