package com.dentalclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTO {
   // Attributes
   private Long id;
   private String fullname;
   private String license;

   // Constructor
   public DentistDTO() {}

   // Getters and setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFullname() {
      return fullname;
   }
   public void setFullname(String fullname) {
      this.fullname = fullname;
   }

   public String getLicense() {
      return license;
   }

   public void setLicense(String license) {
      this.license = license;
   }
}
