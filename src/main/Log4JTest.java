package main;

import org.apache.log4j.Logger;

public class Log4JTest {
   private static final Logger LOGGER = Logger.getLogger(Log4JTest.class);

   public static void main(String[] args) {
      LOGGER.info("Holi");
   }
}
