package com.dentalclinic.controllers;

import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entities.Dentist;
import com.dentalclinic.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
   public ResponseEntity<Dentist> create(@RequestBody Dentist dentist) {
      return ResponseEntity.ok().body(dentistService.createDentist(dentist));
   }

   // SEARCH ALL DENTISTS
   @GetMapping("/search")
   public ResponseEntity<List<DentistDTO>> searchAll() {
      return ResponseEntity.ok().body(dentistService.readAllDentists());
   }

   // SEARCH A DENTIST BY ID
   @GetMapping("/search/{id}")
   public ResponseEntity<DentistDTO> searchById(@PathVariable Long id) {
      return ResponseEntity.ok().body(dentistService.readDentist(id));
   }

   // UPDATE A DENTIST
   @PutMapping("/update")
   public ResponseEntity<Dentist> update(@RequestBody Dentist dentist) {
      /*ResponseEntity<Dentist> response;

      if (dentistService.readDentist(dentist.getId()) == null) {
         response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
      } else {
         response = new ResponseEntity<>(dentistService.updateDentist(dentist), HttpStatus.OK);
      }

      return response;*/
      return ResponseEntity.ok().body(dentistService.updateDentist(dentist));
   }

   // DELETE A DENTIST TODO RESPONSE ENTITY
   @DeleteMapping("/delete/{id}")
   public void delete(@PathVariable Long id) {
      dentistService.deleteDentist(id);
   }
}