package com.dentalclinic.entity;

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
}