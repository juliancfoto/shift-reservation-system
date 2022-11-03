package main.entities;

import java.time.LocalDate;

public class Patient {
   // Attributes
   private Long id;
   private String name;
   private String lastName;
   private String address;
   private String dni;
   // FORMAT: 'YYYY-MM-DD' -> TYPE: DATE in SQL
   private String dischargeDate;

   // Constructor

   public Patient() {
   }

   public Patient(Long id, String name, String lastName, String address, String dni, String dischargeDate) {
      this.id = id;
      this.name = name;
      this.lastName = lastName;
      this.address = address;
      this.dni = dni;
      this.dischargeDate = dischargeDate;
   }

   public Patient(String name, String lastName, String address, String dni, String dischargeDate) {
      this.name = name;
      this.lastName = lastName;
      this.address = address;
      this.dni = dni;
      this.dischargeDate = dischargeDate;
   }

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

   public String getDischargeDate() {
      return dischargeDate;
   }

   public void setDischargeDate(String dischargeDate) {
      this.dischargeDate = dischargeDate;
   }
}