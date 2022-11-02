package main.entities;

import java.time.LocalDate;

public class Patient {
   // Attributes
   private Long id;
   private String name;
   private String lastName;
   private String address;
   private String dni;
   private LocalDate dischargeDate;

   // Getters and setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public String getDni() {
      return dni;
   }

   public void setDni(String dni) {
      this.dni = dni;
   }

   public LocalDate getDischargeDate() {
      return dischargeDate;
   }

   public void setDischargeDate(LocalDate dischargeDate) {
      this.dischargeDate = dischargeDate;
   }
}