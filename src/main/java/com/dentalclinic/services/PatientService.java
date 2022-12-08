package com.dentalclinic.services;

import com.dentalclinic.dao.IDao;
import com.dentalclinic.dto.PatientDTO;
import com.dentalclinic.entities.Patient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
   // Attributes
   private IDao<Patient> patientIDao;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public PatientService(IDao<Patient> patientIDao, ObjectMapper objectMapper) {
      this.patientIDao = patientIDao;
      this.objectMapper = objectMapper;
   }

   // Methods using DTO
   public Patient createPatient(Patient patient) {
      // Using DAO interface to create a Patient
      return patientIDao.create(patient);
   }

   public PatientDTO readPatient(Long id) {
      // Using DAO interface to read/search a Patient
      // Entity object to be mapped
      Patient patient = patientIDao.read(id);

      // DTO object container
      PatientDTO patientDTO;

      // Mapping object to DTO object
      patientDTO = objectMapper.convertValue(patient, PatientDTO.class);
      patientDTO.setFullname(patient.getName() + " " + patient.getLastName());

      return patientDTO;
   }

   public List<Patient> readAllPatients() {
      // Using DAO interface to read/search all Patients
      return patientIDao.readAll();
   }

   public Patient updatePatient(Patient patient) {
      // Using DAO interface to update Patient data
      return patientIDao.update(patient);
   }

   public void deletePatient(Long id) {
      // Using DAO interface to delete a Patient
      patientIDao.delete(id);
   }

   // Getters and setters
   public void setPatientIDao(IDao<Patient> patientIDao) {
      this.patientIDao = patientIDao;
   }
}