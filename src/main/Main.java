package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
   private static final Logger LOGGER = LogManager.getLogger();
   public static void main(String[] args) {
      /*TODO:
      *  1. Create interface  -DONE
      *  2. Migrate from Log4J to Log4J2  -DONE
      *  3. Implement interface in Dentist and Patient DAO
      *  4. Create service classes*/

      LOGGER.info("First log. Main class running.");

   }
}
