package com.dentalclinic.service;

import com.dentalclinic.dto.PatientDTO;
import com.dentalclinic.entity.Patient;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
   // CRUD: Create | Read | Update | Delete
   Patient createPatient(Patient patient);
   PatientDTO readPatient(Long id);
   List<PatientDTO> readAllPatients();
   ResponseEntity<Patient> updatePatient(Patient patient);
   void deletePatient(Long id);
}