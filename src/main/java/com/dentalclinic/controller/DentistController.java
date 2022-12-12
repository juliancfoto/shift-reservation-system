package com.dentalclinic.controller;

import com.dentalclinic.dto.DentistCreationDTO;
import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entity.Dentist;
import com.dentalclinic.service.impl.DentistServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
   private final DentistServiceImpl dentistService;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public DentistController(DentistServiceImpl dentistServiceImpl, ObjectMapper objectMapper) {
      this.dentistService = dentistServiceImpl;
      this.objectMapper = objectMapper;
   }

   // CREATE A DENTIST
   @PostMapping("/new")
   public ResponseEntity<DentistDTO> create(@RequestBody DentistCreationDTO dentistCreationDTO) {
      // Map the received Creation DTO to a Dentist entity
      Dentist dentist = toEntity(dentistCreationDTO);

      // Create the Dentist based on the previous map
      dentist = dentistService.createDentist(dentist);

      // DTO object to show protected data using DentistDTO class
      DentistDTO dentistDTO = toDTO(dentist);

      // Assign fullName to DentistDTO based on data received
      assignFullName(dentist, dentistDTO);

      return ResponseEntity.ok().body(dentistDTO);
   }

   // SEARCH ALL DENTISTS
   @GetMapping("/search")
   public ResponseEntity<List<DentistDTO>> searchAll() {
      // Non DTO object to be mapped
      List<Dentist> dentistsList = dentistService.readAllDentists();

      // DTO object container
      List<DentistDTO> dentistsDTOList = new ArrayList<>();

      // Mapping object to DTO object and assigning full name to each DTO object
      for (Dentist dentist : dentistsList) {
         dentistsDTOList.add(toDTO(dentist));

         for (int i = 0; i < dentistsDTOList.size(); i++) {
            dentistsDTOList.get(i).setFullname(dentistsList.get(i).getName() + " " + dentistsList.get(i).getLastname());
         }
      }
      return ResponseEntity.ok().body(dentistsDTOList);
   }

   // SEARCH A DENTIST BY ID
   @GetMapping("/search/{id}")
   public ResponseEntity<DentistDTO> searchById(@PathVariable Long id) {
      // Entity object to be mapped
      Dentist dentist = dentistService.readDentist(id);

      // DTO Object container
      DentistDTO dentistDTO = toDTO(dentist);
      assignFullName(dentist, dentistDTO);

      return ResponseEntity.ok().body(dentistDTO);
   }

   // UPDATE A DENTIST
   @PutMapping("/update")
   public ResponseEntity<DentistDTO> update(@RequestBody DentistCreationDTO dentistCreationDTO) {
      // Map the received Creation DTO to a Dentist entity
      Dentist dentist = toEntity(dentistCreationDTO);

      // Update the Dentist based on the previous map
      dentist = dentistService.updateDentist(dentist);

      // DTO object to show protected data using DentistDTO class
      DentistDTO dentistDTO = toDTO(dentist);

      // Assign fullName to DentistDTO based on data received
      assignFullName(dentist, dentistDTO);

      return ResponseEntity.ok().body(dentistDTO);
   }

   // DELETE A DENTIST TODO RESPONSE ENTITY
   @DeleteMapping("/delete/{id}") // TODO REVIEW EXCEPTION WHEN PATIENT'S ID DOESN'T EXIST
   public ResponseEntity<?> delete(@PathVariable Long id) {
      dentistService.deleteDentist(id);
      return ResponseEntity.ok("Dentist with id = " + id + " deleted.");
   }

   // MAPPING METHODS
   // TODO USE PERSONALIZED EXCEPTION
   private void assignFullName(Dentist dentist, DentistDTO dentistDTO) {
      if (dentist != null) {
         dentistDTO.setFullname(dentist.getName() + " " + dentist.getLastname());
      }  /*else { // TODO Use personalized exception
         throw new NullPointerException("Errorrr");
      }*/
   }

   // From object to DTO object
   private DentistDTO toDTO(Dentist dentist) {
      return objectMapper.convertValue(dentist, DentistDTO.class);
   }

   // From DTO object to object
   private Dentist toEntity(DentistCreationDTO dentistDTO) {
      return objectMapper.convertValue(dentistDTO, Dentist.class);
   }
}