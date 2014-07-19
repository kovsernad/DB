DROP TABLE position 		CASCADE CONSTRAINTS;
DROP TABLE qualification	CASCADE CONSTRAINTS;
DROP TABLE staff			CASCADE CONSTRAINTS;
DROP TABLE patient 			CASCADE CONSTRAINTS;
DROP TABLE hospital 		CASCADE CONSTRAINTS;
DROP TABLE laboratory		CASCADE CONSTRAINTS;
DROP TABLE drugstores   	CASCADE CONSTRAINTS;
DROP TABLE drugs 			CASCADE CONSTRAINTS;
DROP TABLE tests 			CASCADE CONSTRAINTS;
DROP TABLE schedule 		CASCADE CONSTRAINTS;
DROP TABLE prescription 	CASCADE CONSTRAINTS;
DROP TABLE staffSchedule 	CASCADE CONSTRAINTS;


DROP SEQUENCE position_seq;
DROP SEQUENCE qualification_seq;
DROP SEQUENCE staff_seq;
DROP SEQUENCE patient_seq;
DROP SEQUENCE hospital_seq;
DROP SEQUENCE laboratory_seq;
DROP SEQUENCE drugstores_seq;
DROP SEQUENCE drugs_seq;
DROP SEQUENCE tests_seq;
DROP SEQUENCE schedule_seq;
DROP SEQUENCE prescription_seq;

--position table 
CREATE TABLE position (
id NUMBER(7) CONSTRAINT position_id_pk PRIMARY KEY,
posdesc VARCHAR2(60)
);

CREATE SEQUENCE position_seq;

CREATE OR REPLACE TRIGGER position_bir
BEFORE INSERT ON position
FOR EACH ROW

BEGIN
SELECT position_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Qualification table
CREATE TABLE qualification (
id NUMBER(7) CONSTRAINT qualification_id_pk PRIMARY KEY,
qualdesc VARCHAR2(60)
);

CREATE SEQUENCE qualification_seq;

CREATE OR REPLACE TRIGGER qualification_bir
BEFORE INSERT ON qualification
FOR EACH ROW

BEGIN
SELECT qualification_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Laboratory Table
CREATE TABLE laboratory (
id NUMBER(5) CONSTRAINT laboratory_id_pk PRIMARY KEY,
labname  VARCHAR2(60),
labaddress  VARCHAR2(60),
labphone VARCHAR2(17)
);

CREATE SEQUENCE laboratory_seq;

CREATE OR REPLACE TRIGGER laboratory_bir
BEFORE INSERT ON laboratory
FOR EACH ROW

BEGIN
SELECT laboratory_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Test Table
CREATE TABLE tests (
id NUMBER(20) CONSTRAINT test_id_pk PRIMARY KEY,
res VARCHAR(1000),
ardate DATE,
depdate DATE,
labid NUMBER(5),
CONSTRAINT tests_labid_fk FOREIGN KEY (labid) REFERENCES laboratory(id)
);

CREATE SEQUENCE tests_seq;

CREATE OR REPLACE TRIGGER tests_bir
BEFORE INSERT ON tests
FOR EACH ROW

BEGIN
SELECT tests_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Drugstore Information Table
CREATE TABLE drugstores (
id NUMBER(5) CONSTRAINT drugstores_id_pk PRIMARY KEY,
name VARCHAR2(60),
address VARCHAR2(60),
phone VARCHAR2(17),
openh VARCHAR2(40)
);

CREATE SEQUENCE drugstores_seq;

CREATE OR REPLACE TRIGGER drugstores_bir
BEFORE INSERT ON drugstores
FOR EACH ROW

BEGIN
SELECT drugstores_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Drugs Table
CREATE TABLE drugs (
id NUMBER(5) CONSTRAINT drugs_id_pk PRIMARY KEY,
storeid NUMBER(5),
name VARCHAR2(60),
dose VARCHAR2(30),
price NUMBER(6,2),
avlb CHAR(1) DEFAULT 'N',
remburs CHAR(1) DEFAULT 'Y',
drugstoreid NUMBER(5),
CONSTRAINT drugstores_drugstoreid_fk FOREIGN KEY (drugstoreid) REFERENCES drugstores(id)
);

CREATE SEQUENCE drugs_seq;

CREATE OR REPLACE TRIGGER drugs_bir
BEFORE INSERT ON drugs
FOR EACH ROW

BEGIN
SELECT drugs_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Prescription table
CREATE TABLE prescription (
id NUMBER(10) CONSTRAINT prescription_id_pk PRIMARY KEY,
prescription VARCHAR(100),
drugid NUMBER(5),
CONSTRAINT prescription_drugid_fk FOREIGN KEY (drugid) REFERENCES drugs(id)
);

CREATE SEQUENCE prescription_seq;

CREATE OR REPLACE TRIGGER prescription_bir
BEFORE INSERT ON prescription
FOR EACH ROW

BEGIN
SELECT prescription_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Patient General Information table
 CREATE TABLE patient (
id NUMBER(11) CONSTRAINT patient_id_pk PRIMARY KEY,
fname VARCHAR2(60),
lname VARCHAR2(60),
bdate DATE,
email VARCHAR2(40),
address VARCHAR2(60),
zip VARCHAR2(7),
phone VARCHAR2(17),
medcard VARCHAR2(15),
insurrance VARCHAR2(300),
ssn VARCHAR2(9),
anamnesis VARCHAR2(3000),
diagnosis VARCHAR2(1000),
prescid NUMBER(10),
CONSTRAINT patient_prescid_fk FOREIGN KEY (prescid) REFERENCES prescription(id)
);

CREATE SEQUENCE patient_seq;

CREATE OR REPLACE TRIGGER patient_bir
BEFORE INSERT ON patient
FOR EACH ROW

BEGIN
SELECT patient_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


--Hospital Information Table
CREATE TABLE hospital (
id NUMBER(5) CONSTRAINT hospital_id_pk PRIMARY KEY,
name VARCHAR2(60),
address VARCHAR2(60),
phone VARCHAR2(17)
);

CREATE SEQUENCE hospital_seq;

CREATE OR REPLACE TRIGGER hospital_bir
BEFORE INSERT ON hospital
FOR EACH ROW

BEGIN
SELECT hospital_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Staff Information
CREATE TABLE staff (
id NUMBER(7) CONSTRAINT staff_id_pk PRIMARY KEY,
fname VARCHAR2(60),
lname VARCHAR2(60),
bdate DATE,
email VARCHAR2(40),
address VARCHAR2(60),
zip VARCHAR2(7),
phone VARCHAR2(17),
SSN VARCHAR2(9),
posid NUMBER(7),
qualid NUMBER(7),
login VARCHAR2(16),
password VARCHAR2(128),
sess CHAR(1) DEFAULT 'N',
CONSTRAINT staff_pos_fk FOREIGN KEY (posid) REFERENCES position(id),
CONSTRAINT staff_qual_fk FOREIGN KEY (qualid) REFERENCES qualification(id)
);

CREATE SEQUENCE staff_seq;

CREATE OR REPLACE TRIGGER staff_bir
BEFORE INSERT ON staff
FOR EACH ROW

BEGIN
SELECT staff_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


-- staff schedual table
CREATE TABLE staffSchedule (
staffid NUMBER(7),
hospid NUMBER(5),
workday VARCHAR2(4),
workhour VARCHAR2(60),
CONSTRAINT staffsh_hospid_fk FOREIGN KEY (hospid) REFERENCES hospital(id),
CONSTRAINT staffsh_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT staffsh_stidhid_wday_pk PRIMARY KEY (hospid, staffid, workday)
);

--Schedule Table
CREATE TABLE schedule (
id NUMBER(20), 
patientid NUMBER(11),
sdate DATE,
staffid NUMBER(7),
testid NUMBER(11),
CONSTRAINT schedule_id_patientid_pk PRIMARY KEY (id, patientid),
CONSTRAINT schedule_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT schedule_testid_fk FOREIGN KEY (testid) REFERENCES tests(id),
CONSTRAINT schedule_patientid_fk FOREIGN KEY (patientid) REFERENCES patient(id)
);

CREATE SEQUENCE schedule_seq;

CREATE OR REPLACE TRIGGER schedule_bir
BEFORE INSERT ON schedule
FOR EACH ROW

BEGIN
SELECT schedule_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--hospitals
INSERT INTO hospital (id, name, address, phone) VALUES (1, 'The Jewish General Hospital' , 
'3755 Cote Ste Catherine Montreal Qc', '514-340-8222');

INSERT INTO hospital (name, address, phone) VALUES ('St. Marys Hospital', 
'3830 Lacombe Avenue Montreal Qc' , '514-345-3511');

INSERT INTO hospital (name, address, phone) VALUES ('Lakeshore General Hospital' , 
'160 Stillview Suite 1249 Pointe-Claire Qc', '514-630-2081');

INSERT INTO hospital (name, address, phone) VALUES ('Montreal General Hospital', 
'1650 Cedar Avenue Montreal Qc', '514-934-1934');

INSERT INTO hospital (name, address, phone) VALUES ('CHU Sainte-Justine', 
'3175 Chemin de la Côte-Sainte-Catherine Montreal Qc', '514-345-4931');

-- qualification
INSERT INTO qualification (id, qualdesc) VALUES (1, 'GP CatA');
INSERT INTO qualification (qualdesc) VALUES ('GP CatB');
INSERT INTO qualification (qualdesc) VALUES ('Physician CatB');
INSERT INTO qualification (qualdesc) VALUES ('Surgeon CatA');
INSERT INTO qualification (qualdesc) VALUES ('Registered Nurse');
INSERT INTO qualification (qualdesc) VALUES ('Medical Administrative Assistant');
INSERT INTO qualification (qualdesc) VALUES ('Medical Laboratory Assistant');
INSERT INTO qualification (qualdesc) VALUES ('Medical Laboratory Technologist');
INSERT INTO qualification (qualdesc) VALUES ('Medical Officer');

-- position
INSERT INTO position (id, posdesc)
VALUES (1, 'GP');
INSERT INTO position (id, posdesc)
VALUES (2, 'NS');
INSERT INTO position (id, posdesc)
VALUES (3, 'MA');
INSERT INTO position (id, posdesc)
VALUES (4, 'MO');


-- staff
INSERT INTO staff (id, fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES (1, 'John F.', 'Gilbert', '08-JAN-1961',  'JohnFGilbert@teleworm.us', 
'96 Rue de la Gauchetière E Montreal', 'L1H 7K5',  '905-431-1311', '673747515', 1, 1, 
'susanin', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert J.', 'Moore', '24-APR-1945' ,  'RobertJMoore@dayrep.com' , 
'3708 Avenue Henri Julien Montreal', 'V2S 2H7' ,  '604-853-8638', '366987444', 1, 1, 
'test', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Shirley', 'Minor', '14-NOV-1967'  ,  'ShirleyJMinor@armyspy.com', 
'4293 Avenue de Esplanade Montreal' , 'T5J 2R4' ,  '780-996-5578', '726768716', 2, 5, 
'test1', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Lane W.', 'Bentley', '18-JAN-1965' ,  'LaneWBentley@rhyta.com' , 
'252 Ch de la Côte-Sainte-Catherine Montreal', 'P0T 2L0'  ,  '807-349-5758', '090601576', 3, 6, 
'test2', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Drew B.', 'Dalley', '23-DEC-1971',  'DrewBDalley@dayrep.com ', 
'444 Avenue Champagneur Montreal ', 'M4W 1J7' ,  '416-318-8430', '521398032', 3, 7, 
'test3', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Frank I.', 'Sims', '27-JUL-1980' ,  'FrankISims@armyspy.com' , 
'274 67e Av Laval' , 'V7L 2C1' ,  '604-960-7790', '709809909', 4, 9, 
'test4', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('John', 'Green', '18-JAN-1981',  'JohnGreenn@teleworm.us', 
'196 Rue de la Gauchetière O Montreal', 'R1H 9C5',  '435-031-1313', '333747555', 1, 1, 
'susanin', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert', 'Marwell', '04-APR-1975' ,  'RobertMarwell@teleworm.com' , 
'708 Avenue Lajeunesse Montreal', 'F2C 2H7' ,  '435-853-8838', '333987744', 1, 1, 
'test', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Jeremy', 'Gilbert', '02-JUN-1971',  'JerGilbert@teleworm.us', 
'196 Avenue Westmore Montreal', 'K1H 2V5',  '514-431-1313', '343747415', 1, 1, 
'susanin', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Richard', 'Morse', '24-MAY-1979' ,  'RichMorse@dayrep.com' , 
'38 Boulvard Poirier', 'V2N 6N7' ,  '514-893-6638', '336987334', 1, 1, 
'test', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Jill', 'Johns', '18-AUG-1961',  'JillJohns@teleworm.us', 
'296 Boulvard Lebeau Montreal', 'M1H 1K5',  '514-451-1355', '343347535', 1, 1, 
'susanin', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert', 'Johns', '24-APR-1975' ,  'RobertJohns@dayrep.com' , 
'8 Boulvard Lebeau Montreal', 'M2S 2K7' ,  '514-851-1638', '343987435', 1, 1, 
'test', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('John', 'Jefferson', '08-AUG-1971',  'JohnJefferson@teleworm.us', 
'196 Rue de la Gauchetière E Montreal', 'L4H 7K5',  '514-444-1981', '343722525', 1, 1, 
'susanin', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert', 'Jefferson', '24-APR-1975' ,  'RobertJefferson@dayrep.com' , 
'38 Avenue Henri Julien Montreal', 'V5S 2H7' ,  '514-623-2678', '344447234', 1, 1, 
'test', 'nGxum2uOdpU=' );

INSERT INTO drugstores (id, name, address, phone, openh) VALUES (1, 'Pharmaprix', '1500 Rue Sainte-Catherine Ouest', '514-933-4744', '8:00 am - 12:00 am daily');

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Jean Coutu' , '7145 St Denis Montreal', '514-495-8686', '9:00 am - 10:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Proxim' , '7684 Blvd St. Michel Montreal', '514-725-4738', '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Brunet Pharmacies Affiliees', '151 Atwater Av Montreal', '514-935-5637',  '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Uniprix', '4349 Belanger Rue Montreal', '514-725-5273', '9:00 am - 9:00 pm daily' );


INSERT INTO drugs (id, name, dose, price, avlb, remburs) VALUES (1, 'ACCOLATE', '20 MG TABLET', 135.80, 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('KETOCONAZOLE', '2% CREAM', 12.46, 'N', 'Y' );
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('SILDENAFIL', '20 MG TABLET', 24.31, 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('PREVACID', '15 MG CAPSULE', 300.77, 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('VASOTEC', '20 MG TABLET', 306.02, 'N', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('ELMIRON', '100 MG CAPSULE', 220.31, 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('TAZORAC', '0.05% GEL' , 299.86, 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('TIZANIDINE', '2 MG TABLET', 11.45, 'N', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('TYLENOL Extra Strength' , '500 mg caplets', 12.99, 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('ACCUPRIL' , '20 MG TABLET', 93.22 , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('ADVAIR ' , '100-50 DISKUS ', 254.13 , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('ELIDEL' , '51% CREAM (VAL) ', 234.65 , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('ETODOLAC' , '400 MG TABLET', 56.67 , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('ELMIRON' , '100 MG CAPSULE ', 220.31 , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('KEPPRA' , '1,000 MG TABLET', 848.32 , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('KENALOG AEROSOL ' , 'SPRAY ', 300.06 , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('PRINIVIL' , '20 MG TABLET ', 46.26 , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('PATANASE' , '0.6% NASAL SPRAY', 225.36 , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('PROGESTERONE' , '100 MG CAPSULE ', 49.00 , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('SERTRALINE HCL ' , '100 MG TABLET ', 11.35  , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('SELENIUM SULFIDE ' , '2.5% LOTION ', 16.70  , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('SUMATRIPTAN' , '6 MG/0.5 ML REFILL ', 268.77  , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('STARLIX' , '120 MG TABLET  ', 82.62  , 'Y', 'Y');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('VERAMYST' , '27.5 MCG NASAL SPRAY ', 131.48  , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('VYTORIN ' , '10-40 MG TABLET  ', 204.57  , 'Y', 'N');
INSERT INTO drugs (name, dose, price, avlb, remburs) VALUES ('VIIBRYD' , '40 MG TABLET ', 203.66  , 'Y', 'N');



INSERT INTO prescription (id, prescription, drugid) 
VALUES(1, 'bed rest and TYLENOL Extra Strength, 3 times a day', 9);
INSERT INTO prescription (prescription, drugid) 
VALUES('Tizanidine, twice a day', 8);
INSERT INTO prescription (prescription, drugid) 
VALUES('SILDENAFIL, once a day', 3);
INSERT INTO prescription (prescription, drugid) 
VALUES('KETOCONAZOLE, once a day', 2);
INSERT INTO prescription (prescription, drugid) 
VALUES('VASOTEC, once a day', 5);
INSERT INTO prescription (prescription, drugid) 
VALUES('TAZORAC twice a day ', 7);

-- patients
INSERT INTO patient (id, fname, lname, bdate, email, address, 
zip, phone, medcard, insurrance, ssn, anamnesis, diagnosis, prescid)
VALUES (1, 'Carol B.', 'Chapman', '21-MAR-1983', 'CarolBChapman@rhyta.com',  
'1214 Avenue Shorecrest Laval', 'P0C 1A0', '514-555-0142' ,'CHAC 1234 5678', 
'1Z 132 162 58 7392 178 6', '552180598', 'Headache and fever (39.3 C). The general indisposition ', 
'the flu', 1);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, prescid)
VALUES ('Margaret', 'Egan', '25-DEC-1925' , 'MargaretDEgan@jourrapide.com',
'7493 Rue André Breton Laval', 'M5H 1P6', '514-435-0172'  ,'EGAM 5521 8059', 
'1Z 578 473 93 3515 049 9', '756945994',
'tingling, pins and needles or numbness, muscle weakness, very pronounced reflexes, ' ||
'muscle spasms, or difficulty in moving; difficulties with coordination and balance (ataxia); ' ||
'problems with speech or swallowing, visual problems (nystagmus, optic neuritis or double vision), ' || 
'feeling tired, acute or chronic pain, and bladder ','Multiple sclerosis',2);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, prescid)
VALUES ('Walter', 'Thompson', '21-NOV-1955' , 'WalterLThompson@rhyta.com', 
'66 Rue Caumartin Laval', 'Y0B 1G', '514-785-0112' ,'THOW 1234 5678', 
'1Z 878 A10 27 1315 029 1', '226724300',' shortness of breath, fatigue, non-productive cough,' ||
' angina pectoris, fainting or syncope' ,'Pulmonary hypertension , redness, itching ' || 
'and discomfort between the fingers mycosis', 4);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, prescid)
VALUES ('Susan', 'Patterson', '13-JUN-1958' , 'SusanWPatterson@jourrapide.com',  
'12216 Rue Richer Montreal', 'V6B 3K9', '514-565-0122' ,'PATS 2267 2430', 
'11Z 891 W99 44 6594 797 8', '466050168', 'repetetive headaches', 'Migraine', 3);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, prescid)
VALUES ('John', 'Pinckney', '21-MAR-1983', 'JohnIPinckney@armyspy.com' ,  
'12751 Rue Tracy Montreal', 'V3C 4S7', '514-995-0170' ,'PINJ 4660 5016', 
'1Z 100 693 94 9107 530 0', '026961748','headaches particularly at the back of ' || 
'the head and in the morning, as well as lightheadedness, vertigo, tinnitus ' || 
'(buzzing or hissing in the ears), high pressure 1790/120', 'Hypertension', 5);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, prescid)
VALUES ('Andy', 'Evans', '8-JUN-1945' , 'AndyMEvans@jourrapide.com' ,  
'4740 Boulevard Saint Joseph Montreal', 'S4P 3Y2', '514-258-0106' , 
'EVAA 0269 6174', '1Z 189 63E 17 4015 269 7', '473349066', 
'Raised areas of inflamed skin covered with silvery white scaly skin on the ' || 
'elbows, knees, scalp, and back. Inflammation and exfoliation ' || 
'of the skin in these areas', 'Psoriasis', 6 );


-- laboratory
INSERT INTO laboratory (id, labname, labaddress, labphone) 
VALUES (1, 'CDL Medical Laboratories', '5990, ch de la Côte-des-Neiges, Montréal, QC H3S 1Z5', '514-344-8022');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Radiologie Varad ', '150, rue Sainte-Catherine O, Montréal, QC H2X 3Y2', '514-281-1355');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES (' RMD Sommeil ','142-100, ch Rockland, Mont-Royal, QC H3P 2V9','514-341-2111');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Clinique Médicale Plexo ', '6100 Avenue du Boisé, Montréal, QC H3S 2W1', '514-251-9331');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('PrélèvExpress Enr ', '510-201, ch du Club-Marin, Verdun, QC H3E 1T4', '514-644-7264');

-- tests
INSERT INTO tests (id, res, ardate, depdate, labid) VALUES (1, 'good', '02-JUL-2014', '03-JUL-2014', 1);
INSERT INTO tests (res, ardate, depdate, labid) VALUES ('very good', '05-JUL-2014', '06-JUL-2014', 2);
INSERT INTO tests (res, ardate, depdate, labid) VALUES ('not good', '07-JUL-2014','09-JUL-2014', 3);
INSERT INTO tests (res, ardate, depdate, labid) VALUES ('excellent', '02-JUL-2014', '03-JUL-2014', 4 );
INSERT INTO tests (res, ardate, depdate, labid) VALUES ('perfect', '05-JUL-2014', '07-JUL-2014', 5);
INSERT INTO tests (res, ardate, depdate, labid) VALUES ('so so', '07-JUL-2014', '08-JUL-2014', 3);

-- staff schedual table
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (1,1,'Mon', '9.00-10.00 AM; 9.00-10.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (1,2,'Mon', '12.00-1.00 PM; 2.00-3.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (2,1,'Mon', '9.00-10.00 AM; 9.00-10.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (3,1,'Mon', '10.00-11.00 AM; 5.00-7.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (2,2,'Tue', '9.00-10.00 AM; 9.00-10.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (3,3,'Wed', '9.00-10.00 AM; 9.00-10.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (10,1,'Tue', '8.00-10.00 AM; 1.00-3.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (10,2,'Thu', '9.00-10.00 AM; 9.00-10.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (4,4,'Wed', '11.00-12.00 AM; 2.00-4.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (4,5,'Mon', '10.00-11.00 PM; 12.00-4.00 AM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (5,5,'Fri', '10.00-11.00 AM; 10.00-11.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (5,1,'Mon', '9.00-10.00 AM; 9.00-10.00 PM');
INSERT INTO staffSchedule (staffid, hospid, workday, workhour) VALUES (5,2,'Thu', '10.00-12.00 AM; 1.00-4.00 PM');

-- schedule
INSERT INTO schedule (id, patientid, sdate, staffid, testid) 
VALUES (1, 1, to_date('2014/08/02 12:30', 'yyyy/mm/dd hh24:mi'), 1, 1);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (2,to_date('2014/08/02 13:00', 'yyyy/mm/dd hh24:mi'), 1, 2);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (3, to_date('2014/08/02 13:00', 'yyyy/mm/dd hh24:mi'), 2, 3);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (1, to_date('2014/08/02 13:30', 'yyyy/mm/dd hh24:mi'), 3, 4);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (4, to_date('2014/08/02 12:30', 'yyyy/mm/dd hh24:mi'), 2, 5);




commit;




