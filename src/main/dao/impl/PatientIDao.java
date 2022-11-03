package main.dao.impl;

import main.dao.IDao;
import main.entities.Patient;

public class PatientIDao implements IDao<Patient> {
   @Override
   public Patient create(Patient patient) {
      return null;
   }

   @Override
   public Patient read(Long id) {
      return null;
   }

   @Override
   public boolean update(Patient patient) {
      return false;
   }

   @Override
   public boolean delete(Long id) {
      return false;
   }
}
