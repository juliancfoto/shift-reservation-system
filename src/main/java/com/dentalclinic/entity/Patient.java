package com.dentalclinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Patients")
public class Patient {
   // Attributes
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String lastname;
   private String address;
   private String dni;
   @Column(name = "discharge_date")
   // FORMAT: 'YYYY-MM-DD' -> TYPE: DATE in SQL
   private String dischargeDate;

   @JsonIgnore
   @OneToOne(mappedBy = "patient")
   private Shift shift;

   // Constructor for testing
   public Patient(String name, String lastname, String address, String dni, String dischargeDate) {
      this.name = name;
      this.lastname = lastname;
      this.address = address;
      this.dni = dni;
      this.dischargeDate = dischargeDate;
   }
}