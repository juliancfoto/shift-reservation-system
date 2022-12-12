package com.dentalclinic.service;

import com.dentalclinic.entity.Shift;
import com.dentalclinic.exception.BadRequestException;
import com.dentalclinic.exception.ResourceNotFoundException;

import java.util.List;

public interface ShiftService {
   // CRUD: Create | Read | Update | Delete
   Shift createShift(Shift shift) throws BadRequestException;
   Shift readShift(Long id) throws ResourceNotFoundException;
   List<Shift> readAllShifts() throws ResourceNotFoundException;
   Shift updateShiftDateTime(Shift shift) throws ResourceNotFoundException;
   void deleteShift(Long id) throws ResourceNotFoundException;
}