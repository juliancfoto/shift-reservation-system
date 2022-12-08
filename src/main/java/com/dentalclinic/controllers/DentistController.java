package com.dentalclinic.controllers;

import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entities.Dentist;
import com.dentalclinic.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
   private final DentistService dentistService;

   // DEPENDENCY INJECTION
   @Autowired
   public DentistController(DentistService dentistService) {
      this.dentistService = dentistService;
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

   // SEARCH A DENTIST BY ID
   @GetMapping("/search/{id}")
   public DentistDTO searchById(@PathVariable Long id) {
      return dentistService.readDentist(id);
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