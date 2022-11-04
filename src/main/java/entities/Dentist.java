package entities;

public class Dentist {
   // Attributes
   private Long id;
   private String name;
   private String lastName;
   private String license;

   // Constructor
   public Dentist() {}
   public Dentist(Long id, String name, String lastName, String license) {
      this.id = id;
      this.name = name;
      this.lastName = lastName;
      this.license = license;
   }

   public Dentist(String name, String lastName, String license) {
      this.name = name;
      this.lastName = lastName;
      this.license = license;
   }

   // Getters and setters
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getLicense() {
      return license;
   }

   public void setLicense(String license) {
      this.license = license;
   }
}