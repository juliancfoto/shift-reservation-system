package com.dentalclinic.service;

import com.dentalclinic.entity.Patient;

import java.util.List;

public interface PatientService {
   // CRUD: Create | Read | Update | Delete
   Patient createPatient(Patient patient);
   Patient readPatient(Long id);
   List<Patient> readAllPatients();
   Patient updatePatient(Patient patient);
   void deletePatient(Long id);
}