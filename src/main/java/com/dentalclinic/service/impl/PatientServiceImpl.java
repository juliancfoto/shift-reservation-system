package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Patient;
import com.dentalclinic.exception.ResourceNotFoundException;
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
   public Patient readPatient(Long id) throws ResourceNotFoundException {
      // Using Repository class to read/search a Patient
      if (patientRepository.findById(id).isEmpty()) {
         throw new ResourceNotFoundException("Patient with id = " + id + " not found.");

      } else {
         return patientRepository.findById(id).get();
      }
   }

   @Override // TODO RUN POSTMAN EXCEPTION  --DELETE DB CONTENT TO SEE
   public List<Patient> readAllPatients() throws ResourceNotFoundException {
      // Using Repository class to read/search all Patients
      if (patientRepository.findAll().isEmpty()) {
         throw new ResourceNotFoundException("There are not patients created.");

      } else {
         return patientRepository.findAll();
      }
   }

   @Override
   public Patient updatePatient(Patient patient) throws ResourceNotFoundException {
      // Using Repository class to update Patient data
      int response = patientRepository.update(
             patient.getName(), patient.getLastname(), patient.getAddress(),
             patient.getDni(), patient.getDischargeDate(), patient.getId());

      if (response == 0) {
         throw new ResourceNotFoundException("Patient cannot be updated. " +
                "Patient with id = " + patient.getId() + " not found.");

      }
      return patient;
   }

   @Override
   public void deletePatient(Long id) throws ResourceNotFoundException {
      // Using Repository class to delete a Patient
      if (patientRepository.findById(id).isEmpty()) {
         throw new ResourceNotFoundException("Patient cannot be deleted. " +
                "Patient with id = " + id + " not found.");

      } else  {
         patientRepository.deleteById(id);
      }
   }
}