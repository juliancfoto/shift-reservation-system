package com.dentalclinic.service.impl;

import com.dentalclinic.entity.Dentist;
import com.dentalclinic.entity.Patient;
import com.dentalclinic.entity.Shift;
import com.dentalclinic.exception.BadRequestException;
import com.dentalclinic.exception.ResourceNotFoundException;
import com.dentalclinic.repository.DentistRepository;
import com.dentalclinic.repository.PatientRepository;
import com.dentalclinic.repository.ShiftRepository;
import com.dentalclinic.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShiftServiceImpl implements ShiftService {
   // Attributes
   private final ShiftRepository shiftRepository;
   private final DentistRepository dentistRepository;
   private final PatientRepository patientRepository;

   // DEPENDENCY INJECTION
   @Autowired
   public ShiftServiceImpl(ShiftRepository shiftRepository, DentistRepository dentistRepository, PatientRepository patientRepository) {
      this.shiftRepository = shiftRepository;
      this.dentistRepository = dentistRepository;
      this.patientRepository = patientRepository;
   }

   @Override
   public Shift createShift(Shift shift) throws BadRequestException {
      // Using Repository class to create a Shift
      Optional<Dentist> dentist = dentistRepository.findById(shift.getDentist().getId());
      Optional<Patient> patient = patientRepository.findById(shift.getPatient().getId());

      if (dentist.isEmpty() || patient.isEmpty()) {
         throw new BadRequestException("Non-existent Dentist or Patient.");
      }

      shift.setDentist(dentist.get());
      shift.setPatient(patient.get());

      return shiftRepository.save(shift);
   }

   @Override
   public Shift readShift(Long id) throws ResourceNotFoundException {
      // Using Repository class to read/search a Shift
      if (shiftRepository.findById(id).isEmpty()) {
         throw new ResourceNotFoundException("Shift with id = " + id + " not found.");

      } else {
         return shiftRepository.findById(id).get();
      }
   }

   @Override
   public List<Shift> readAllShifts() throws ResourceNotFoundException {
      // Using Repository class to read/search all Shifts
      if (shiftRepository.findAll().isEmpty()) {
         throw new ResourceNotFoundException("No shifts have been created.");

      } else {
         return shiftRepository.findAll();
      }
   }

   @Override
   public Shift updateShiftDateTime(Shift shift) throws ResourceNotFoundException {
      // Using Repository class to update datetime of Shift data
      int response = shiftRepository.update(
             shift.getDatetime(), shift.getId()
      );

      if (response == 0) {
         throw new ResourceNotFoundException("Shift datetime cannot be updated. " +
                "Shift with id = " + shift.getId() + " not found.");
      }
      return shift;
   }

   @Override
   public void deleteShift(Long id) throws ResourceNotFoundException {
      // Using Repository class to delete a Shift
      if (shiftRepository.findById(id).isEmpty()) {
         throw new ResourceNotFoundException("Shift cannot be deleted. " +
                "Shift with id = " + id + " not found.");

      } else {
         shiftRepository.deleteById(id);
      }
   }
}