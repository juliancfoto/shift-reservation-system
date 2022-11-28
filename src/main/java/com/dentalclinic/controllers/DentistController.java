package com.dentalclinic.controllers;

import com.dentalclinic.dao.impl.DentistIDao;
import com.dentalclinic.entities.Dentist;
import com.dentalclinic.services.DentistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentists")
public class DentistController {
   private final DentistIDao dentistIDao = new DentistIDao();
   private final DentistService dentistService = new DentistService();

   // CREATE A DENTIST
   @PostMapping("/new")
   public Dentist create(@RequestBody Dentist dentist) {
      dentistService.setDentistIDao(dentistIDao);
      return dentistService.createDentist(dentist);
   }

   // SEARCH ALL DENTISTS
   @GetMapping("/search")
   public List<Dentist> searchAll() {
      dentistService.setDentistIDao(dentistIDao);
      return dentistService.readAllDentists();
   }

   // SEARCH A DENTIST BY ID
   @GetMapping("/search/{id}")
   public Dentist searchById(@PathVariable Long id) {
      dentistService.setDentistIDao(dentistIDao);
      return dentistService.readDentist(id);
   }

   // UPDATE A DENTIST
   @PutMapping("/update")
   public Dentist update(@RequestBody Dentist dentist) {
      dentistService.setDentistIDao(dentistIDao);
      return dentistService.updateDentist(dentist);
   }

   // DELETE A DENTIST
   @DeleteMapping("/delete/{id}")
   public void delete(@PathVariable Long id) {
      dentistService.deleteDentist(id);
   }
}