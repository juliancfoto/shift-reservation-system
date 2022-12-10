package com.dentalclinic.service.impl;

import com.dentalclinic.dto.PatientDTO;
import com.dentalclinic.entity.Patient;
import com.dentalclinic.repository.PatientRepository;
import com.dentalclinic.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
   // Attributes
   private final PatientRepository patientRepository;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public PatientServiceImpl(PatientRepository patientRepository, ObjectMapper objectMapper) {
      this.patientRepository = patientRepository;
      this.objectMapper = objectMapper;
   }

   // Methods using DTO
   @Override
   public Patient createPatient(Patient patient) {
      // Using Repository class to create a Patient
      return patientRepository.save(patient);
   }

   @Override
   public PatientDTO readPatient(Long id) {
      // Using Repository class to read/search a Patient
      // Entity object to be mapped
      Patient patient = patientRepository.findById(id).orElse(null);

      // DTO object container
      PatientDTO patientDTO;

      // Mapping object to DTO object
      patientDTO = toDTO(patient);

      if (patient != null) {
         patientDTO.setFullname(patient.getName() + " " + patient.getLastname());
      } /*else { // TODO Use personalized exception
         throw new NullPointerException("Errorrr");
      }*/

      return patientDTO;
   }

   @Override
   public List<PatientDTO> readAllPatients() {
      // Using Repository class to read/search all Patients
      // Non DTO object to be mapped
      List<Patient> patientsList = patientRepository.findAll();

      // DTO object container
      List<PatientDTO> patientsDTOList = new ArrayList<>();

      // Mapping object to DTO object and assigning full name to each DTO object
      for (Patient patient : patientsList) {
         patientsDTOList.add(toDTO(patient));

         for (int i = 0; i < patientsDTOList.size(); i++) {
            patientsDTOList.get(i).setFullname(patientsList.get(i).getName() + " " + patientsList.get(i).getLastname());
         }
      }
      return patientsDTOList;
   }

   @Override
   public ResponseEntity<Patient> updatePatient(Patient patient) {
      // Using Repository class to update Patient data
      int response = patientRepository.update(
             patient.getName(), patient.getLastname(), patient.getAddress(), patient.getDni(), patient.getDischargeDate(), patient.getId());

      // TODO -- EXCEPTIONS ID NOT FOUND
      if (response == 0) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(patient);

      }
      return ResponseEntity.ok().body(patient);
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

   // Methods for mapping DTOs
   // From object to DTO object
   private PatientDTO toDTO(Patient patient) {
      return objectMapper.convertValue(patient, PatientDTO.class);
   }

   // From DTO object to object
   private Patient toEntity(PatientDTO patientDTO) {
      return objectMapper.convertValue(patientDTO, Patient.class);
   }
}