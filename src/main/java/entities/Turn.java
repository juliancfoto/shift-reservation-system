package entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turn {
   // Attributes
   private Long id;
   private LocalDate date;
   private LocalTime time;

   // Getters and setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public LocalTime getTime() {
      return time;
   }

   public void setTime(LocalTime time) {
      this.time = time;
   }
}
