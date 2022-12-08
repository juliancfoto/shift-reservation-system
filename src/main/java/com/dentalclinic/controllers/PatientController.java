package com.dentalclinic.controllers;

import com.dentalclinic.dto.PatientDTO;
import com.dentalclinic.entities.Patient;
import com.dentalclinic.services.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
   private final PatientService patientService;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public PatientController(PatientService patientService, ObjectMapper objectMapper) {
      this.patientService = patientService;
      this.objectMapper = objectMapper;
   }

   // CREATE A PATIENT
   @PostMapping("/new")
   public Patient create(@RequestBody Patient patient) {
      return patientService.createPatient(patient);
   }

   // SEARCH ALL PATIENTS
   @GetMapping("/search")
   public List<Patient> searchAll() {
      return patientService.readAllPatients();
   }

   // SEARCH A PATIENT BY ID USING DTO
   @GetMapping("/search/{id}")
   public PatientDTO searchById(@PathVariable("id") Long id) {
      // Entity to be mapped
      Patient patient = patientService.readPatient(id);

      // DTO object container
      PatientDTO patientDTO;

      // Mapping object to DTO object
      patientDTO = objectMapper.convertValue(patient, PatientDTO.class);
      patientDTO.setFullname(patient.getName() + " " + patient.getLastName());
      return patientDTO;
   }

   // UPDATE A PATIENT
   @PutMapping("/update")
   public Patient update(@RequestBody Patient patient) {
      return patientService.updatePatient(patient);
   }

   // DELETE A PATIENT
   @DeleteMapping("/delete/{id}")
   public void delete(@PathVariable Long id) {
      patientService.deletePatient(id);
   }
}