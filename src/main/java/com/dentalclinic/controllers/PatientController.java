package com.dentalclinic.controllers;

import com.dentalclinic.dao.impl.PatientIDao;
import com.dentalclinic.entities.Patient;
import com.dentalclinic.services.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
   private final PatientService patientService = new PatientService();
   private final PatientIDao patientIDao = new PatientIDao();

   // CREATE A PATIENT
   @PostMapping("/new")
   public Patient create(@RequestBody Patient patient) {
      patientService.setPatientIDao(patientIDao);
      return patientService.createPatient(patient);
   }

   // SEARCH ALL PATIENTS
   @GetMapping("/search")
   public List<Patient> searchAll() {
      patientService.setPatientIDao(patientIDao);
      return patientService.readAllPatients();
   }

   // SEARCH A PATIENT BY ID
   @GetMapping("/search/{id}")
   public Patient searchById(@PathVariable("id") Long id) {
      patientService.setPatientIDao(patientIDao);
      return patientService.readPatient(id);
   }

   // UPDATE A PATIENT
   @PutMapping("/update")
   public Patient update(@RequestBody Patient patient) {
      patientService.setPatientIDao(patientIDao);
      return patientService.updatePatient(patient);
   }

   // DELETE A PATIENT
   @DeleteMapping("/delete/{id}")
   public void delete(@PathVariable Long id) {
      patientService.deletePatient(id);
   }
}