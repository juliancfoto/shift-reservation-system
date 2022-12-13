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
@Table(name = "Dentists")
public class Dentist {
   // Attributes
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String lastname;
   private String license;

   @JsonIgnore
   @OneToOne(mappedBy = "dentist")
   private Shift shift;

   // Constructor for testing
   public Dentist(String name, String lastname, String license) {
      this.name = name;
      this.lastname = lastname;
      this.license = license;
   }
}