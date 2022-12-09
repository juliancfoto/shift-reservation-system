package com.dentalclinic.controllers;

import com.dentalclinic.dto.PatientDTO;
import com.dentalclinic.entities.Patient;
import com.dentalclinic.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
   private final PatientService patientService;

   // DEPENDENCY INJECTION
   @Autowired
   public PatientController(PatientService patientService) {
      this.patientService = patientService;
   }

   // CREATE A PATIENT
   @PostMapping("/new")
   public ResponseEntity<Patient> create(@RequestBody Patient patient) {
      return ResponseEntity.ok().body(patientService.createPatient(patient));
   }

   // SEARCH ALL PATIENTS
   @GetMapping("/search")
   public ResponseEntity<List<PatientDTO>> searchAll() {
      return ResponseEntity.ok().body(patientService.readAllPatients());
   }

   // SEARCH A PATIENT BY ID USING DTO
   @GetMapping("/search/{id}")
   public ResponseEntity<PatientDTO> searchById(@PathVariable("id") Long id) {
      return ResponseEntity.ok().body(patientService.readPatient(id));
   }

   // UPDATE A PATIENT
   @PutMapping("/update")
   public ResponseEntity<Patient> update(@RequestBody Patient patient) {
      return ResponseEntity.ok().body(patientService.updatePatient(patient));
   }

   // DELETE A PATIENT TODO RESPONSE ENTITY
   @DeleteMapping("/delete/{id}")
   public void delete(@PathVariable Long id) {
      patientService.deletePatient(id);
   }
}