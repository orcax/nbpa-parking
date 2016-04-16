DROP DATABASE IF EXISTS nbpa_parking;
CREATE DATABASE nbpa_parking;

-- Table for demo
CREATE TABLE nbpa_parking.Demo
(
Id int NOT NULL AUTO_INCREMENT,
Name varchar(255),
PRIMARY KEY (Id)
);

-- Tables for app
CREATE TABLE nbpa_parking.Location
(
Id int NOT NULL AUTO_INCREMENT,
Name varchar(255),
Capacity int,
PRIMARY KEY (Id)
);

CREATE TABLE nbpa_parking.Occupancy
(
Id int(20) NOT NULL AUTO_INCREMENT,
LocationId int,
StartHour timestamp,
Weekday int,
TicketNo int,
CcExpressNo int,
MonthlyNo int,
DailyTotalNo int,
Occupancy decimal(10,2),
PRIMARY KEY (Id),
FOREIGN KEY (LocationId) REFERENCES nbpa_parking.Location(Id)
);