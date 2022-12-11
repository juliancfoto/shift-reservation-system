package com.dentalclinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.log4j.Logger;

@SpringBootApplication
public class ShiftReservationSystemApplication {

   private final static Logger LOGGER = Logger.getLogger(ShiftReservationSystemApplication.class);
   public static void main(String[] args) {
      LOGGER.info("Spring Application running");
      SpringApplication.run(ShiftReservationSystemApplication.class, args);
   }

}
