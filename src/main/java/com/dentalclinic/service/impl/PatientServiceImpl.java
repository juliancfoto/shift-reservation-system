package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Patient;
import com.dentalclinic.repository.PatientRepository;
import com.dentalclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
   // Attributes
   private final PatientRepository patientRepository;

   // DEPENDENCY INJECTION
   @Autowired
   public PatientServiceImpl(PatientRepository patientRepository) {
      this.patientRepository = patientRepository;
   }

   // Methods using DTO
   @Override
   public Patient createPatient(Patient patient) {
      // Using Repository class to create a Patient
      return patientRepository.save(patient);
   }

   @Override
   public Patient readPatient(Long id) {
      // Using Repository class to read/search a Patient
      return patientRepository.findById(id).orElse(null);
   }

   @Override
   public List<Patient> readAllPatients() {
      // Using Repository class to read/search all Patients
      return patientRepository.findAll();
   }

   @Override // TODO REVIEW EXCEPTION WHEN PATIENT'S ID DOESN'T EXIST
   public Patient updatePatient(Patient patient) {
      // Using Repository class to update Patient data
      int response = patientRepository.update(
             patient.getName(), patient.getLastname(), patient.getAddress(),
             patient.getDni(), patient.getDischargeDate(), patient.getId());

      // TODO -- EXCEPTIONS ID NOT FOUND
      /*if (response == 0) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(patient);

      }
      return ResponseEntity.ok().body(patient);*/
      return patient;
   }

   @Override
   public void deletePatient(Long id) {
      // Using DAO interface to delete a Patient
      if (patientRepository.findById(id).isPresent()) {
         patientRepository.deleteById(id);
      } /*else { // TODO Use personalized exception
         throw new NullPointerException("Errorrr");
      }*/
   }
}