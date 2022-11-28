package com.dentalclinic.services;

import com.dentalclinic.dao.IDao;
import com.dentalclinic.entities.Patient;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
   // Attributes
   private IDao<Patient> patientIDao;

   public PatientService(IDao<Patient> patientIDao) {
      this.patientIDao = patientIDao;
   }

   // Methods
   public Patient createPatient(Patient patient) {
      // Using DAO interface to create a Patient
      return patientIDao.create(patient);
   }

   public Patient readPatient(Long id) {
      // Using DAO interface to read/search a Patient
      return patientIDao.read(id);
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