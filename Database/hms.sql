CREATE DATABASE IF NOT EXISTS hms;
USE hms;

CREATE TABLE IF NOT EXISTS user_login (
    id VARCHAR(100) PRIMARY KEY NOT NULL,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO user_login (id, username, password)
VALUES ('1', 'Aditya', '12345');

CREATE TABLE IF NOT EXISTS doctor_record (
    id VARCHAR(100) PRIMARY KEY NOT NULL,
    DoctorName TEXT(100) NOT NULL,
    Specialization TEXT(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS patient_record (
    id VARCHAR(50) PRIMARY KEY NOT NULL,
    Name VARCHAR(100) NOT NULL,
    Disease VARCHAR(100) NOT NULL,
    Date VARCHAR(30) NOT NULL
);
