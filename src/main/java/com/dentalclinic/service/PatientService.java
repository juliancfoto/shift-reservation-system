package com.dentalclinic.service;

import com.dentalclinic.entity.Patient;
import com.dentalclinic.exception.ResourceNotFoundException;

import java.util.List;

public interface PatientService {
   // CRUD: Create | Read | Update | Delete
   Patient createPatient(Patient patient);
   Patient readPatient(Long id) throws ResourceNotFoundException;
   List<Patient> readAllPatients() throws ResourceNotFoundException;
   Patient updatePatient(Patient patient) throws ResourceNotFoundException;
   void deletePatient(Long id) throws ResourceNotFoundException;
}