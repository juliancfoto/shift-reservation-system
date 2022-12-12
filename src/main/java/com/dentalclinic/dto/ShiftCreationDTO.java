package com.dentalclinic.dto;

import com.dentalclinic.entity.Dentist;
import com.dentalclinic.entity.Patient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShiftCreationDTO {
   // Attributes
   private Long id;
   // FORMAT: 'YYYY-MM-DD hh:mm:ss' -> TYPE: DATETIME in SQL
   private String datetime;
   private Dentist dentist;
   private Patient patient;
}
