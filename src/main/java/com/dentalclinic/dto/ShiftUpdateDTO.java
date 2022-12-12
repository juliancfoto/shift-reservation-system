package com.dentalclinic.dto;

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
public class ShiftUpdateDTO {
   // UPDATE DTO to manipulate only the datetime of a registered shift
   // Attributes
   private Long id;
   // FORMAT: 'YYYY-MM-DD hh:mm:ss' -> TYPE: DATETIME in SQL
   private String datetime;
}
