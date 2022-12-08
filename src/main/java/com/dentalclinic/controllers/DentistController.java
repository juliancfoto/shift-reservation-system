package com.dentalclinic.controllers;

import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entities.Dentist;
import com.dentalclinic.services.DentistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
   private final DentistService dentistService;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public DentistController(DentistService dentistService, ObjectMapper objectMapper) {
      this.dentistService = dentistService;
      this.objectMapper = objectMapper;
   }

   // CREATE A DENTIST
   @PostMapping("/new")
   public Dentist create(@RequestBody Dentist dentist) {
      return dentistService.createDentist(dentist);
   }

   // SEARCH ALL DENTISTS
   @GetMapping("/search")
   public List<Dentist> searchAll() {
      return dentistService.readAllDentists();
   }

   // SEARCH A DENTIST BY ID USING DTO
   @GetMapping("/search/{id}")
   public DentistDTO searchById(@PathVariable Long id) {
      // Entity to be mapped
      Dentist dentist = dentistService.readDentist(id);

      // DTO object container
      DentistDTO dentistDTO;

      // Mapping object to DTO object
      dentistDTO = objectMapper.convertValue(dentist, DentistDTO.class);
      dentistDTO.setFullname(dentist.getName() + " " + dentist.getLastName());

      return dentistDTO;
   }

   // UPDATE A DENTIST
   @PutMapping("/update")
   public Dentist update(@RequestBody Dentist dentist) {
      return dentistService.updateDentist(dentist);
   }

   // DELETE A DENTIST
   @DeleteMapping("/delete/{id}")
   public void delete(@PathVariable Long id) {
      dentistService.deleteDentist(id);
   }
}