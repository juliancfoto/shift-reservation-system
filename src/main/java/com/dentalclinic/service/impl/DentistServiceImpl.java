package com.dentalclinic.service.impl;

import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entity.Dentist;
import com.dentalclinic.repository.DentistRepository;
import com.dentalclinic.service.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistServiceImpl implements DentistService {
   // Attributes
   private final DentistRepository dentistRepository;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public DentistServiceImpl(DentistRepository dentistRepository, ObjectMapper objectMapper) {
      this.dentistRepository = dentistRepository;
      this.objectMapper = objectMapper;
   }

   // Methods using DTO
   @Override
   public Dentist createDentist(Dentist dentist) {
      // Using Repository class to create a Dentist
      return dentistRepository.save(dentist);
   }

   @Override
   public DentistDTO readDentist(Long id) {
      // Using Repository class to read/search a Dentist
      // Entity object to be mapped
      Dentist dentist = dentistRepository.findById(id).orElse(null);

      // DTO object container
      DentistDTO dentistDTO;

      // Mapping object to DTO object
      dentistDTO = toDTO(dentist);

      if (dentist != null) {
         dentistDTO.setFullname(dentist.getName() + " " + dentist.getLastname());
      } /*else { // TODO Use personalized exception
         throw new NullPointerException("Errorrr");
      }*/

      return dentistDTO;
   }

   @Override
   public List<DentistDTO> readAllDentists() {
      // Using Repository class to read/search all Dentists
      // Non DTO object to be mapped
      List<Dentist> dentistsList = dentistRepository.findAll();

      // DTO object container
      List<DentistDTO> dentistsDTOList = new ArrayList<>();

      // Mapping object to DTO object and assigning full name to each DTO object
      for (Dentist dentist : dentistsList) {
         dentistsDTOList.add(toDTO(dentist));

         for (int i = 0; i < dentistsDTOList.size(); i++) {
            dentistsDTOList.get(i).setFullname(dentistsList.get(i).getName() + " " + dentistsList.get(i).getLastname());
         }
      }
      return dentistsDTOList;
   }

   @Override
   public ResponseEntity<Dentist> updateDentist(@NotNull Dentist dentist) {
      // Using Repository class to update Dentist data
      int response = dentistRepository.update(
             dentist.getName(), dentist.getLastname(), dentist.getLicense(),dentist.getId()
      );

      // TODO -- EXCEPTIONS ID NOT FOUND
      if (response == 0) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dentist);

      }
      return ResponseEntity.ok().body(dentist);
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


   // Methods for mapping DTOs
   // From object to DTO object
   private DentistDTO toDTO(Dentist dentist) {
      return objectMapper.convertValue(dentist, DentistDTO.class);
   }

   // From DTO object to object
   private Dentist toEntity(DentistDTO dentistDTO) {
      return objectMapper.convertValue(dentistDTO, Dentist.class);
   }
}
