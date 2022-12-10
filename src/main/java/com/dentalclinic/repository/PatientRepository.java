package com.dentalclinic.repository;

import com.dentalclinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
   @Transactional
   @Modifying
   @Query("""
          UPDATE Patient p
          SET p.name = ?1, p.lastname = ?2, p.address = ?3, p.dni = ?4, p.dischargeDate = ?5
          where p.id = ?6
          """)
   int update(String name, String lastname, String address, String dni, String dischargeDate, @NonNull Long id);
}
