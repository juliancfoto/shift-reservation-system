package com.dentalclinic.services;

import com.dentalclinic.dao.IDao;
import com.dentalclinic.dto.DentistDTO;
import com.dentalclinic.entities.Dentist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistService {
   // Attributes
   private IDao<Dentist> dentistIDao;
   private final ObjectMapper objectMapper;

   // DEPENDENCY INJECTION
   @Autowired
   public DentistService(IDao<Dentist> dentistIDao, ObjectMapper objectMapper) {
      this.dentistIDao = dentistIDao;
      this.objectMapper = objectMapper;
   }

   // Methods using DTO
   public Dentist createDentist(Dentist dentist) {
      // Using DAO interface to create a Dentist
      return dentistIDao.create(dentist);
   }

   public DentistDTO readDentist(Long id) {
      // Using DAO interface to read/search a Dentist
      // Entity object to be mapped
      Dentist dentist = dentistIDao.read(id);

      // DTO object container
      DentistDTO dentistDTO;

      // Mapping object to DTO object
      dentistDTO = toDTO(dentist);

      dentistDTO.setFullname(dentist.getName() + " " + dentist.getLastName());

      return dentistDTO;
   }

   public List<DentistDTO> readAllDentists() {
      // Using DAO interface to read/search all Dentists
      // Non DTO object to be mapped
      List<Dentist> dentistsList = dentistIDao.readAll();

      // DTO object container
      List<DentistDTO> dentistsDTOList = new ArrayList<>();

      // Mapping object to DTO object and assigning full name to each DTO object
      for (Dentist dentist : dentistsList) {
         dentistsDTOList.add(toDTO(dentist));

         for (int i = 0; i < dentistsDTOList.size(); i++) {
            dentistsDTOList.get(i).setFullname(dentistsList.get(i).getName() + " " + dentistsList.get(i).getLastName());
         }
      }

      return dentistsDTOList;
   }

   public Dentist updateDentist(Dentist dentist) {
      // Using DAO interface to update Dentist data
      return dentistIDao.update(dentist);
   }

   public void deleteDentist(Long id) {
      // Using DAO interface to delete a Dentist
      dentistIDao.delete(id);
   }

   // Methods for mapping DTOs
   // From object to DTO object
   private DentistDTO toDTO(Dentist dentist) {
      return objectMapper.convertValue(dentist, DentistDTO.class);
   }

   // From DTO object to object
   private Dentist toEntity(DentistDTO dentistDTO) {
      return objectMapper.convertValue(dentistDTO, Dentist.class);
   }

   // Getters and setters
   public void setDentistIDao(IDao<Dentist> dentistIDao) {
      this.dentistIDao = dentistIDao;
   }
}
