package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Dentist;
import com.dentalclinic.exception.ResourceNotFoundException;
import com.dentalclinic.repository.DentistRepository;
import com.dentalclinic.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {
   // Attributes
   private final DentistRepository dentistRepository;

   // DEPENDENCY INJECTION
   @Autowired
   public DentistServiceImpl(DentistRepository dentistRepository) {
      this.dentistRepository = dentistRepository;
   }

   // Methods using DTO
   @Override
   public Dentist createDentist(Dentist dentist) {
      // Using Repository class to create a Dentist
      return dentistRepository.save(dentist);
   }

   @Override
   public Dentist readDentist(Long id) throws ResourceNotFoundException {
      // Using Repository class to read/search a Dentist
      if (dentistRepository.findById(id).isEmpty()) {
         throw new ResourceNotFoundException("Dentist with id = " + id + " not found.");

      } else {
         return dentistRepository.findById(id).get();
      }
   }

   @Override // TODO RUN POSTMAN EXCEPTION  --DELETE DB CONTENT TO SEE
   public List<Dentist> readAllDentists() throws ResourceNotFoundException {
      // Using Repository class to read/search all Dentists
      if (dentistRepository.findAll().isEmpty()) {
         throw new ResourceNotFoundException("There are not dentists created.");

      } else {
         return dentistRepository.findAll();
      }
   }

   @Override
   public Dentist updateDentist(Dentist dentist) throws ResourceNotFoundException {
      // Using Repository class to update Dentist data
      int response = dentistRepository.update(
             dentist.getName(), dentist.getLastname(), dentist.getLicense(),dentist.getId()
      );

      if (response == 0) {
         throw new ResourceNotFoundException("Dentist cannot be updated. " +
                "Dentist with id = " + dentist.getId() + " not found.");

      }
      return dentist;
   }

   @Override
   public void deleteDentist(Long id) throws ResourceNotFoundException {
      // Using Repository class to delete a Dentist
      if (dentistRepository.findById(id).isEmpty()) {
         throw new ResourceNotFoundException("Dentist cannot be deleted. " +
                "Dentist with id = " + id + " not found.");

      } else  {
         dentistRepository.deleteById(id);
      }
   }
}