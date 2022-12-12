package com.dentalclinic.repository;

import com.dentalclinic.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
   // UPDATE Method to manipulate only the datetime of a registered shift
   @Transactional
   @Modifying
   @Query("""
         UPDATE Shift s
         SET s.datetime = ?1
         WHERE s.id = ?2
         """)
   int update(String datetime, Long id);
}