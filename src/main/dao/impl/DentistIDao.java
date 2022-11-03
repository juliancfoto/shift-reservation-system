package main.dao.impl;

import main.dao.IDao;
import main.entities.Dentist;

public class DentistIDao implements IDao<Dentist> {
   @Override
   public Dentist create(Dentist dentist) {
      return null;
   }

   @Override
   public Dentist read(Long id) {
      return null;
   }

   @Override
   public boolean update(Dentist dentist) {
      return false;
   }

   @Override
   public boolean delete(Long id) {
      return false;
   }
}
