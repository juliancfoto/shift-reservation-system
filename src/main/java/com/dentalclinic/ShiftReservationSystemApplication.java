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
      /*TODO:
       *  1. Add dependencies: log4j, h2, junit  -DONE
       *  2. Create project structure (com.dentalclinic.dao, com.dentalclinic.entities, com.dentalclinic.services, test, logs)  -DONE
       *  3. Migrate from Log4J to Log4J2  -DONE
       *  4. Create interface  -DONE
       *  5. Create script sql for creating database tables  -DONE
       *  5. Create interface implementations (DentistIDao, PatientIDao) -DONE
       *  6. Create com.dentalclinic.services classes (DentistService, PatientService)  -DONE
       *  7. Create Dentist, Patient and com.dentalclinic.services objects in com.dentalclinic.Main  *CRUD works* -DONE
       *  8. Add and use loggings  -DONE
       *  9. Create TESTS for services classes  -DONE
       *        - create()  -DONE
       *        - readById()  -DONE
       *  10. Migrate to Maven project -DONE
       *  11. Migrate to Springboot project  -DONE
       *  12. Create Controllers classes (DentistController, PatientController) -DONE
       *        - CRUD() done  -DONE
       *  13. Use Dependency Injection  -DONE
       *  14. Use DTO *EVERYTHING MUST USE IT*  -DONE
       *        - read() DTO in Dentist and Patient services  -DONE
       *        - readAll() DTO in Dentist and Patient services  -DONE
       *        - create() DTO in Dentist and Patient services  -DONE
       *        - delete() DTO in Dentist and Patient services  -DONE
       *        - update() DTO in Dentist and Patient services  -DONE
       *  15. Use Response Entity  -DONE
       *  16. Use Optional  -DONE
       *  17. Change DAO to ORM JPA  -DONE
       *     - USE RELATIONS ONETOMANY, etc  -DONE
       *     - USE HQL AND QUERY METHODS  -DONE
       *        - HQL  -DONE
       *        - QUERY METHODS  -DONE
       *  18. Review the use of Log4J. Downgrade Log4j2 to Log4j 1.2.x  -DONE
       *  19. Create and use Exceptions  -DONE
       *  20. Create Shift entity, repository, dto and service  -DONE
       *        - Create classes  -DONE
       *        - Create relationship with Dentist and Patient class  -DONE
       *  21. Create User entity, repository, dto and service  - -- -DOING
       *        - Create classes  -MISSING
       *        - Create relationship with Dentist and Patient class  -MISSING
       *  22. Add security and AUTH to Users  -MISSING
       *  .
       *  .
       *  . MANDATORY: ORM, JPA, EXCEPTIONS HANDLER, SPRING SECURITY, DTO
       *  . OPTIONAL: RESPONSE ENTITY, OPTIONAL
       * */
   }

}
