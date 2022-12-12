package com.dentalclinic.controller;

import com.dentalclinic.dto.PatientCreationDTO;
import com.dentalclinic.dto.PatientDTO;
import com.dentalclinic.entity.Patient;
import com.dentalclinic.exception.ResourceNotFoundException;
import com.dentalclinic.service.impl.PatientServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
   private final PatientServiceImpl patientService;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public PatientController(PatientServiceImpl patientService, ObjectMapper objectMapper) {
      this.patientService = patientService;
      this.objectMapper = objectMapper;
   }

   // CREATE A PATIENT
   @PostMapping("/new")
   public ResponseEntity<PatientDTO> create(@RequestBody PatientCreationDTO patientCreationDTO) {
      // Map the received Creation DTO to a Patient entity
      Patient patient = toEntity(patientCreationDTO);

      // Create the Patient based on the previous map
      patient = patientService.createPatient(patient);

      // DTO object to show protected data using PatientDTO class
      PatientDTO patientDTO = toDTO(patient);

      // Assign fullName to PatientDTO based on data received
      assignFullName(patient, patientDTO);

      return ResponseEntity.ok().body(patientDTO);
   }

   // SEARCH ALL PATIENTS
   @GetMapping("/search")
   public ResponseEntity<List<PatientDTO>> searchAll() throws ResourceNotFoundException {
      // Non DTO object to be mapped
      List<Patient> patientsList = patientService.readAllPatients();

      // DTO object container
      List<PatientDTO> patientsDTOList = new ArrayList<>();

      // Mapping object to DTO object and assigning full name to each DTO object
      for (Patient patient : patientsList) {
         patientsDTOList.add(toDTO(patient));

         for (int i = 0; i < patientsDTOList.size(); i++) {
            patientsDTOList.get(i).setFullname(patientsList.get(i).getName() + " " + patientsList.get(i).getLastname());
         }
      }
      return ResponseEntity.ok().body(patientsDTOList);
   }

   // SEARCH A PATIENT BY ID
   @GetMapping("/search/{id}")
   public ResponseEntity<PatientDTO> searchById(@PathVariable("id") Long id) throws ResourceNotFoundException {
      // Entity object to be mapped
      Patient patient = patientService.readPatient(id);

      // DTO Object container
      PatientDTO patientDTO = toDTO(patient);
      assignFullName(patient, patientDTO);
      return ResponseEntity.ok().body(patientDTO);
   }

   // UPDATE A PATIENT
   @PutMapping("/update")
   public ResponseEntity<PatientDTO> update(@RequestBody PatientCreationDTO patientCreationDTO) throws ResourceNotFoundException {
      // Map the received Creation DTO to a Patient entity
      Patient patient = toEntity(patientCreationDTO);

      // Update the Patient based on the previous map
      patient = patientService.updatePatient(patient);

      // DTO object to show protected data using PatientDTO class
      PatientDTO patientDTO = toDTO(patient);

      // Assign fullName to PatientDTO based on data received
      assignFullName(patient, patientDTO);

      return ResponseEntity.ok().body(patientDTO);
   }

   // DELETE A PATIENT
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
      patientService.deletePatient(id);
      return ResponseEntity.ok("Patient with id = " + id + " deleted.");

   }

   // MAPPING METHODS
   private void assignFullName(Patient patient, PatientDTO patientDTO) {
      if (patient != null) {
         patientDTO.setFullname(patient.getName() + " " + patient.getLastname());
      }
   }

   // From object to DTO object
   private PatientDTO toDTO(Patient patient) {
      return objectMapper.convertValue(patient, PatientDTO.class);
   }

   // From DTO object to object
   private Patient toEntity(PatientCreationDTO patientDTO) {
      return objectMapper.convertValue(patientDTO, Patient.class);
   }
}