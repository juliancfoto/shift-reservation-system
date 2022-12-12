package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Dentist;
import com.dentalclinic.repository.DentistRepository;
import com.dentalclinic.service.DentistService;
import org.jetbrains.annotations.NotNull;
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
   public Dentist readDentist(Long id) {
      // Using Repository class to read/search a Dentist
      return dentistRepository.findById(id).orElse(null);
   }

   @Override
   public List<Dentist> readAllDentists() {
      // Using Repository class to read/search all Dentists
      return dentistRepository.findAll();
   }

   @Override // TODO REVIEW EXCEPTION WHEN DENTIST'S ID DOESN'T EXIST
   public Dentist updateDentist(@NotNull Dentist dentist) {
      // Using Repository class to update Dentist data
      int response = dentistRepository.update(
             dentist.getName(), dentist.getLastname(), dentist.getLicense(),dentist.getId()
      );

      // TODO -- EXCEPTIONS ID NOT FOUND
      /*if (response == 0) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dentist);

      }
      return ResponseEntity.ok().body(dentist);*/
      return dentist;
   }

   @Override
   public void deleteDentist(Long id) {
      // Using Repository class to delete a Dentist
      if (dentistRepository.findById(id).isPresent()) {
         dentistRepository.deleteById(id);

      } /*else { // TODO Use personalized exception
         throw new NullPointerException("Errorrr");
      }*/
   }
}
