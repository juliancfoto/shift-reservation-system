package com.dentalclinic.controller;

import com.dentalclinic.dto.ShiftCreationDTO;
import com.dentalclinic.dto.ShiftDTO;
import com.dentalclinic.dto.ShiftUpdateDTO;
import com.dentalclinic.entity.Shift;
import com.dentalclinic.exception.BadRequestException;
import com.dentalclinic.exception.ResourceNotFoundException;
import com.dentalclinic.service.impl.ShiftServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {
   private final ShiftServiceImpl shiftService;
   private final ObjectMapper objectMapper;

   @Autowired
   public ShiftController(ShiftServiceImpl shiftService, ObjectMapper objectMapper) {
      this.shiftService = shiftService;
      this.objectMapper = objectMapper;
   }

   // CREATE A PATIENT
   @PostMapping("/new")
   public ResponseEntity<ShiftDTO> create(@RequestBody ShiftCreationDTO shiftCreationDTO) throws BadRequestException {
      // Map the received Creation DTO to a Shift entity
      Shift shift = shiftCreationToEntity(shiftCreationDTO);

      // Create the Shift based on the previous map
      shift = shiftService.createShift(shift);

      // DTO object to show protected data using ShiftDTO class
      ShiftDTO shiftDTO = shiftCreationToDTO(shift);

      // Assign IDs to Dentist and Patient in ShiftDTO based on data received
      assignIDs(shift, shiftDTO);

      return ResponseEntity.ok().body(shiftDTO);
   }

   // SEARCH ALL PATIENTS
   @GetMapping("/search")
   public ResponseEntity<List<ShiftDTO>> searchAll() throws ResourceNotFoundException {
      // Non DTO object to be mapped
      List<Shift> shiftsList = shiftService.readAllShifts();

      // DTO object container
      List<ShiftDTO> shiftsDTOList = new ArrayList<>();

      // Mapping object to DTO object and adding to DTOs list
      for (Shift shift : shiftsList) {
         shiftsDTOList.add(shiftCreationToDTO(shift));

         // Set ID's to ShiftDTO
         for (int i = 0; i < shiftsDTOList.size(); i++) {
            shiftsDTOList.get(i).setDentist_id(shiftsList.get(i).getDentist().getId());
            shiftsDTOList.get(i).setPatient_id(shiftsList.get(i).getPatient().getId());
         }
      }

      return ResponseEntity.ok().body(shiftsDTOList);
   }

   // SEARCH A PATIENT BY ID
   @GetMapping("/search/{id}")
   public ResponseEntity<ShiftDTO> searchById(@PathVariable("id") Long id) throws ResourceNotFoundException {
      // Entity object to be mapped
      Shift shift = shiftService.readShift(id);

      // DTO Object container and assign ID's to ShiftDTO
      ShiftDTO shiftDTO = shiftCreationToDTO(shift);
      assignIDs(shift, shiftDTO);

      return ResponseEntity.ok().body(shiftDTO);
   }

   // UPDATE A PATIENT
   @PutMapping("/update")
   public ResponseEntity<ShiftUpdateDTO> updateDatetime(@RequestBody ShiftUpdateDTO shiftUpdateDTO) throws ResourceNotFoundException {
      // Map the received Creation DTO to a Shift entity
      Shift shift = shiftUpdateToEntity(shiftUpdateDTO);

      // Update the Shift based on the previous map
      shift = shiftService.updateShiftDateTime(shift);

      // DTO object to show protected data using ShiftDTO class
      ShiftUpdateDTO shiftDTO = shiftUpdateToDTO(shift);

      return ResponseEntity.ok().body(shiftDTO);
   }

   // DELETE A PATIENT
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<?> delete(@PathVariable Long id) throws ResourceNotFoundException {
      shiftService.deleteShift(id);
      return ResponseEntity.ok("Shift with id = " + id + " deleted.");

   }

   // MAPPING METHODS
   private void assignIDs(Shift shift, ShiftDTO shiftDTO) {
      if (shift != null) {
         shiftDTO.setDentist_id(shift.getDentist().getId());
         shiftDTO.setPatient_id(shift.getPatient().getId());
      }
   }

   // From object to DTO object (Creation DTO and Update DTO)
   private ShiftDTO shiftCreationToDTO(Shift shift) {
      return objectMapper.convertValue(shift, ShiftDTO.class);
   }

   private ShiftUpdateDTO shiftUpdateToDTO(Shift shift) {
      return objectMapper.convertValue(shift, ShiftUpdateDTO.class);
   }

   // From DTO object to object
   private Shift shiftCreationToEntity(ShiftCreationDTO shiftDTO) {
      return objectMapper.convertValue(shiftDTO, Shift.class);
   }

   private Shift shiftUpdateToEntity(ShiftUpdateDTO shiftUpdateDTODTO) {
      return objectMapper.convertValue(shiftUpdateDTODTO, Shift.class);
   }
}