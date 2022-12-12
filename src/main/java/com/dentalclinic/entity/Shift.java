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
@Table(name = "shifts")
public class Shift {
   // Attributes
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   // FORMAT: 'YYYY-MM-DD hh:mm:ss' -> TYPE: DATETIME in SQL
   private String datetime;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "dentist_id")
   private Dentist dentist;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "patient_id")
   private Patient patient;

}