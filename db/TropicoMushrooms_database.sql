#--- Create / Restore Database
DROP DATABASE IF EXISTS TropicoMushrooms;
CREATE DATABASE IF NOT EXISTS TropicoMushrooms;

#--- TropicoMushrooms Database
USE TropicoMushrooms;

#--- Create Tables
CREATE TABLE SQ(
SQno INT PRIMARY KEY,
Question VARCHAR(80)
);

CREATE TABLE User(
UserID INT PRIMARY KEY,
Name VARCHAR(50),
Address VARCHAR(100),
Mobile CHAR(12),
NIC CHAR(12),
Designation VARCHAR(20),
UserName VARCHAR(20),
Password VARCHAR(30),
Q1 INT,
Q2 INT,
Q3 INT,
A1 VARCHAR(30),
A2 VARCHAR(30),
A3 VARCHAR(30),
CONSTRAINT FOREIGN KEY(Q1) REFERENCES SQ(SQno) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(Q2) REFERENCES SQ(SQno) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(Q3) REFERENCES SQ(SQno) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Purchase(
PurchaseID INT PRIMARY KEY,
UserID INT,
Date DATE,
Description VARCHAR(100),
Qty DOUBLE,
Unit CHAR(5),
TotalPrice DOUBLE,
CONSTRAINT FOREIGN KEY(UserID) REFERENCES User(UserID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Customer(
CustomerID INT PRIMARY KEY,
Name VARCHAR(50),
Address VARCHAR(100),
Mobile CHAR(12),
NIC CHAR(12)
);

CREATE TABLE Item(
ItemCode INT PRIMARY KEY,
Description VARCHAR(80),
Price DOUBLE,
QtyOnHand DOUBLE,
Unit CHAR(5)
);

CREATE TABLE Orders(
OrderID INT PRIMARY KEY,
Date DATE,
CustomerID INT,
UserID INT,
CONSTRAINT FOREIGN KEY(CustomerID) REFERENCES Customer(CustomerID) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(UserID) REFERENCES User(UserID) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE OrderDetail(
OrderID INT,
ItemCode INT,
Qty DOUBLE,
Unit CHAR(5),
CONSTRAINT PRIMARY KEY (OrderID,ItemCode),
UnitPrice DOUBLE,CONSTRAINT FOREIGN KEY(ItemCode) REFERENCES Item(ItemCode) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Returns(
ReturnID INT PRIMARY KEY,
OrderID INT,
UserID INT,
Reason TEXT,
CONSTRAINT FOREIGN KEY(OrderID) REFERENCES Orders(OrderID) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT FOREIGN KEY(UserID) REFERENCES User(UserID) ON UPDATE CASCADE ON DELETE CASCADE
);

#--- Initial Data
INSERT INTO SQ VALUES
(0,'Select any question'),
(1,'What primary school did you attend?'),
(2,'In what town or city did your parents meet?'),
(3,'In what city or town was your first job?'),
(4,'What was your childhood nickname?'),
(5,'What is the name of your favorite childhood friend?'),
(6,'Who is your childhood sports hero?'),
(7,'what was your first pets name?'),
(8,'What was your first car?'),
(9,'What is your mother maiden name?'),
(10,'Where is your favorite place to vacation?');

#--- Demo Data
INSERT INTO User VALUES
(1,'Pasan Abeysekara','Panadura','0774866554','981280270V','Manager','admin','1234',1,3,10,'Royal','Colombo','Panadura'),
(2,'Tharanga Dissanayake','Mathugama','0719554500','982458963V','Employee','tharanga','1234',1,3,10,'Sasthralaya','Kalutara','Matara'),
(3,'Kavindi Samudika','Piliyandala','0754900804','19990842963','InActive','kavindi','1234',1,3,10,'Central','Maharagama','Kandy');

INSERT INTO Purchase VALUES
(1,1,'2021-03-08','Sugar',5,'KG',500),
(2,1,'2021-03-08','Mushroom Seeds',2,'KG',190),
(3,1,'2021-03-12','Polythene',2,'KG',210);

INSERT INTO Customer VALUES
(1,'Sapumal Stores','Maharagama','0112478563','853632488V'),
(2,'U B Vegetables','Bandaragama','0117896028','796842763V'),
(3,'M T B Super Mart','Kalutara','0777896347','864527963V');

INSERT INTO Item VALUES
(1,'Mushroom 1KG Pack',100,80,'Pack'),
(2,'Mushroom',50,40,'KG'),
(3,'Mushroom Flowers',95,75,'KG');

INSERT INTO Orders VALUES
(1,'2021-03-09',1,1),
(2,'2021-03-15',3,1);

INSERT INTO OrderDetail VALUES
(1,1,20,'Pack',100),
(1,2,35,'KG',50),
(2,2,15,'KG',50),
(2,3,25,'KG',95);

#---

