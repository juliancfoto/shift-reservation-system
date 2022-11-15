-- Query to create Dentists table
-- DROP TABLE Dentists;
CREATE TABLE IF NOT EXISTS Dentists(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    license VARCHAR(255) NOT NULL
);

-- Query to create Patients table
-- DROP TABLE Patients;
CREATE TABLE IF NOT EXISTS Patients(
   id INT AUTO_INCREMENT PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   lastname VARCHAR(255) NOT NULL,
   address VARCHAR(255) NOT NULL,
   dni VARCHAR(255) NOT NULL,
   discharge_date DATE NOT NULL
);