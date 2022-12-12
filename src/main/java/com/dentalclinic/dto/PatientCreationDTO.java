package com.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientCreationDTO {
   // Attributes
   private Long id;
   private String name;
   private String lastname;
   private String address;
   private String dni;
   // FORMAT: 'YYYY-MM-DD' -> TYPE: DATE in SQL
   private String dischargeDate;
}
