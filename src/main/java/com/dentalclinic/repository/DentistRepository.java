package com.dentalclinic.repository;

import com.dentalclinic.entity.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Long> {
   @Transactional
   @Modifying
   @Query("""
         UPDATE Dentist d
         SET d.name = ?1, d.lastname = ?2, d.license = ?3
         WHERE d.id = ?4
         """)
   int update(String name, String lastname, String license, @NonNull Long id);
}
