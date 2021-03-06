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
name VARCHAR(100),
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
storeid VARCHAR2(30),
name VARCHAR2(60),
dose VARCHAR2(30),
price NUMBER(6,2),
avlb CHAR(1) DEFAULT 'N',
remburs CHAR(1) DEFAULT 'Y'
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
gmedhistory VARCHAR2(3000),
illnesshistory VARCHAR2(3000),
medspechistory VARCHAR2(3000),
login VARCHAR2(16),
password VARCHAR2(128)
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

--Prescription table 
CREATE TABLE prescription (
id NUMBER(10),
patientid NUMBER(11),
prescription VARCHAR2(1000),
drugid VARCHAR2(60),
CONSTRAINT prescription_patientid_fk FOREIGN KEY (patientid) REFERENCES patient(id),
CONSTRAINT pres_drugid_patientid_pk PRIMARY KEY (id, patientid) 
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
workhouram VARCHAR2(60),
workhourpm VARCHAR2(60),
CONSTRAINT staffsh_hospid_fk FOREIGN KEY (hospid) REFERENCES hospital(id),
CONSTRAINT staffsh_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT staffsh_stidhid_wday_pk PRIMARY KEY (hospid, staffid, workday)
);

--Schedule Table
CREATE TABLE schedule (
id NUMBER(20), 
patientid NUMBER(11),
sdate TIMESTAMP,
staffid NUMBER(7),
testid NUMBER(11),
confirm CHAR (1),
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
'3175 Chemin de la C�te-Sainte-Catherine Montreal Qc', '514-345-4931');

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
'96 Rue de la Gaucheti�re E Montreal', 'L1H 7K5',  '905-431-1311', '673747515', 1, 1, 
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
VALUES ('Mary', 'Sheldon', '14-NOV-1987'  ,  'SheldonJMary@armyspy.com', 
'42 Avenue Henri Julien Montreal' , 'V1S 1H6' ,  '514-996-5578', '336768716', 2, 5, 
'Sheldon', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Lilliana', 'Ogden', '14-MAR-1977'  ,  'OgdenLill@armyspy.com', 
'93 Avenue de Esplanade Montreal' , 'T3J 2H4' ,  '514-966-5578', '33764416', 2, 5, 
'Ogden', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Kevin', 'Evered ', '24-NOV-1989'  ,  'EveredKevin@gmail.com', 
'29 67e Av Laval' , 'L5J 2R4' ,  '450-566-5566', '346734716', 2, 5, 
'Evered', 'nGxum2uOdpU=' );
  

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Noah', 'Easom', '14-DEC-1987'  ,  'EasomNoah@mail.com', 
'4 67e Av Laval' , 'L4J 2R4' ,  '450-656-4978', '333768744', 2, 5, 
'Easom', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Lane W.', 'Bentley', '18-JAN-1965' ,  'LaneWBentley@rhyta.com' , 
'252 Ch de la C�te-Sainte-Catherine Montreal', 'P0T 2L0'  ,  '807-349-5758', '090601576', 3, 6, 
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
'196 Rue de la Gaucheti�re O Montreal', 'R1H 9C5',  '435-031-1313', '333747555', 1, 1, 
'Green', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert', 'Marwell', '04-APR-1975' ,  'RobertMarwell@teleworm.com' , 
'708 Avenue Lajeunesse Montreal', 'F2C 2H7' ,  '435-853-8838', '333987744', 1, 1, 
'Marwell', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Jeremy', 'Gilbert', '02-JUN-1971',  'JerGilbert@teleworm.us', 
'196 Avenue Westmore Montreal', 'K1H 2V5',  '514-431-1313', '343747415', 1, 1, 
'GilbertJ', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Richard', 'Morse', '24-MAY-1979' ,  'RichMorse@dayrep.com' , 
'38 Boulvard Poirier', 'V2N 6N7' ,  '514-893-6638', '336987334', 1, 1, 
'Morse', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Jill', 'Johns', '18-AUG-1961',  'JillJohns@teleworm.us', 
'296 Boulvard Lebeau Montreal', 'M1H 1K5',  '514-451-1355', '343347535', 1, 1, 
'Johns', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert', 'Johns', '24-APR-1975' ,  'RobertJohns@dayrep.com' , 
'8 Boulvard Lebeau Montreal', 'M2S 2K7' ,  '514-851-1638', '343987435', 1, 1, 
'JohnsR', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('John', 'Jefferson', '08-AUG-1971',  'JohnJefferson@teleworm.us', 
'196 Rue de la Gaucheti�re E Montreal', 'L4H 7K5',  '514-444-1981', '343722525', 1, 1, 
'JeffersonJ', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, login, password )
VALUES ('Robert', 'Jefferson', '24-APR-1975' ,  'RobertJefferson@dayrep.com' , 
'38 Avenue Henri Julien Montreal', 'V5S 2H7' ,  '514-623-2678', '344447234', 1, 1, 
'JeffersonR', 'nGxum2uOdpU=' );

INSERT INTO drugstores (id, name, address, phone, openh) VALUES (1, 'Pharmaprix', '1500 Rue Sainte-Catherine Ouest', '514-933-4744', '8:00 am - 12:00 am daily');

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Jean Coutu' , '7145 St Denis Montreal', '514-495-8686', '9:00 am - 10:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Proxim' , '7684 Blvd St. Michel Montreal', '514-725-4738', '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Brunet Pharmacies Affiliees', '151 Atwater Av Montreal', '514-935-5637',  '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Uniprix', '4349 Belanger Rue Montreal', '514-725-5273', '9:00 am - 9:00 pm daily' );


INSERT INTO drugs (id, name, dose, price, avlb, remburs, storeid) VALUES (1, 'ACCOLATE', '20 MG TABLET', 135.80, 'Y', 'N', '1,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('KETOCONAZOLE', '2% CREAM', 12.46, 'N', 'Y' , '1,3,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('SILDENAFIL', '20 MG TABLET', 24.31, 'Y', 'Y', '4,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('PREVACID', '15 MG CAPSULE', 300.77, 'Y', 'N', '3,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('VASOTEC', '20 MG TABLET', 306.02, 'N', 'N', '1,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('ELMIRON', '100 MG CAPSULE', 220.31, 'Y', 'N', '1,3,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('TAZORAC', '0.05% GEL' , 299.86, 'Y', 'N', '1,4,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('TIZANIDINE', '2 MG TABLET', 11.45, 'N', 'Y', '1,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('TYLENOL Extra Strength' , '500 mg caplets', 12.99, 'Y', 'Y', '3,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('ACCUPRIL' , '20 MG TABLET', 93.22 , 'Y', 'Y', '4,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('ADVAIR ' , '100-50 DISKUS ', 254.13 , 'Y', 'N', '3,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('ELIDEL' , '51% CREAM (VAL) ', 234.65 , 'Y', 'N', '4,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('ETODOLAC' , '400 MG TABLET', 56.67 , 'Y', 'Y', '1,2,3');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('ELMIRON' , '100 MG CAPSULE ', 220.31 , 'Y', 'N', '1,2,4');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('KEPPRA' , '1,000 MG TABLET', 848.32 , 'Y', 'N', '1,2,3');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('KENALOG AEROSOL ' , 'SPRAY ', 300.06 , 'Y', 'N', '1,2,4');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('PRINIVIL' , '20 MG TABLET ', 46.26 , 'Y', 'Y', '1,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('PATANASE' , '0.6% NASAL SPRAY', 225.36 , 'Y', 'N', '3,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('PROGESTERONE' , '100 MG CAPSULE ', 49.00 , 'Y', 'Y', '4,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('SERTRALINE HCL ' , '100 MG TABLET ', 11.35  , 'Y', 'Y', '1,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('SELENIUM SULFIDE ' , '2.5% LOTION ', 16.70  , 'Y', 'Y', '1,4,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('SUMATRIPTAN' , '6 MG/0.5 ML REFILL ', 268.77  , 'Y', 'N', '1,3,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('STARLIX' , '120 MG TABLET  ', 82.62  , 'Y', 'Y', '3,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('VERAMYST' , '27.5 MCG NASAL SPRAY ', 131.48  , 'Y', 'N', '4,2,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('VYTORIN ' , '10-40 MG TABLET  ', 204.57  , 'Y', 'N', '1,3,5');
INSERT INTO drugs (name, dose, price, avlb, remburs, storeid) VALUES ('VIIBRYD' , '40 MG TABLET ', 203.66  , 'Y', 'N', '1,2,5');




-- patients
INSERT INTO patient (id, fname, lname, bdate, email, address, 
zip, phone, medcard, insurrance, ssn, anamnesis, diagnosis, gmedhistory, illnesshistory, medspechistory, login, password)
VALUES (1, 'Carol B.', 'Chapman', '21-MAR-1983', 'CarolBChapman@rhyta.com',  
'1214 Avenue Shorecrest Laval', 'P0C 1A0', '514-555-0142' ,'CHAC 1234 5678', 
'1Z 132 162 58 7392 178 6', '552180598', 'Headache and fever (39.3 C).; The general indisposition.;', 
'the flu.;',
'|Previous Doctor:|Dr.Smith|Previous Medical Institution:|St.Mary clinic|Date of the Last Exam:|12.06.2014|The Reason of the Last Exam:|severe headache|The Hepatitis virus, if any.|no|The heart disease if any.|no|The Tabacoo History, if any.|no|The Alcohol History, if any.|once a month|The chronic diseases, if any:|no|The Allergies, if any:|peanuts|The Drug reactions, if any:|no|Describe any special features:|no|',
'Shortness of breathe|Seasonal allergies|Tonsillitis|Headaches/Migraines|Anemia or blood problems|',
'Have you ever been hospitalized?|Yes|Have you ever been tested for Hepatitis?|No|Have you had a sexually transmitted disease?|No|Have you ever been tested for HIV disease?|No|Have you had a Tuberculosis (TB) Screening?|No|
Have you had a TB screen or an x-ray?|No|Could you provide the copies of tests?|No|Pneumovax:|2000|Hepatitis B:|2000|Hepatitis A:|2000|Varicella:|1988|Zostavax:|never|Gardisil:|never|',
'Chapman', 'nGxum2uOdpU=');

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, login, password)
VALUES ('Margaret', 'Egan', '25-DEC-1925' , 'MargaretDEgan@jourrapide.com',
'7493 Rue Andre Breton Laval', 'M5H 1P6', '514-435-0172'  ,'EGAM 5521 8059', 
'1Z 578 473 93 3515 049 9', '756945994',
'Tingling, pins and needles or numbness, muscle weakness, very pronounced reflexes. ;'||
'Muscle spasms, or difficulty in moving.; Difficulties with coordination and balance (ataxia). ;'||
'Problems with speech or swallowing.; Visual problems (nystagmus, optic neuritis or double vision).; ' ||
'Feeling tired, acute or chronic pain, and bladder.; ','Multiple sclerosis.;',  'Egan', 'nGxum2uOdpU=');

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, login, password)
VALUES ('Walter', 'Thompson', '21-NOV-1955' , 'WalterLThompson@rhyta.com', 
'66 Rue Caumartin Laval', 'Y0B 1G', '514-785-0112' ,'THOW 1234 5678', 
'1Z 878 A10 27 1315 029 1', '226724300','1 Shortness of breath.; Fatigue, non-productive cough,' ||
' angina pectoris, fainting or syncope.;'|| CHR(10) ||CHR(10) || '2 Pulmonary hypertension , redness, itching, ' || 
'discomfort between the fingers mycosis.;' || CHR(10) ||CHR(10) || '3 Pulmonary hypertension , redness, itching, ' || 
'discomfort between the fingers mycosis.;'|| CHR(10) ||CHR(10) || 'pulmonary hypertension , redness, itching, ' || 
'discomfort between the fingers mycosis.; Shortness of breath.; Fatigue, non-productive cough.;'|| CHR(10) ||CHR(10) || 'pulmonary hypertension , redness, itching, ' || 
'discomfort between the fingers mycosis.; Shortness of breath.; Fatigue, non-productive cough.;','Cardiac Arrhythmias.;'|| CHR(10) ||CHR(10) || ' Vasculitis.;' ,'Thompson', 'nGxum2uOdpU=');

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, login, password)
VALUES ('Susan', 'Patterson', '13-JUN-1958' , 'SusanWPatterson@jourrapide.com',  
'12216 Rue Richer Montreal', 'V6B 3K9', '514-565-0122' ,'PATS 2267 2430', 
'11Z 891 W99 44 6594 797 8', '466050168', 'repetetive headaches;', 'Migraine.;',  'Patterson', 'nGxum2uOdpU=');

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, login, password)
VALUES ('John', 'Pinckney', '21-MAR-1983', 'JohnIPinckney@armyspy.com' ,  
'12751 Rue Tracy Montreal', 'V3C 4S7', '514-995-0170' ,'PINJ 4660 5016', 
'1Z 100 693 94 9107 530 0', '026961748','headaches particularly at the back of ' || 
'the head and in the morning.; As well as lightheadedness, vertigo, tinnitus ' || 
'(buzzing or hissing in the ears).; High pressure 1790/120.;', 'Hypertension.;',  'Pinckney', 'nGxum2uOdpU=');

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, 
medcard, insurrance, ssn, anamnesis, diagnosis, login, password)
VALUES ('Andy', 'Evans', '8-JUN-1945' , 'AndyMEvans@jourrapide.com' ,  
'4740 Boulevard Saint Joseph Montreal', 'S4P 3Y2', '514-258-0106' , 
'EVAA 0269 6174', '1Z 189 63E 17 4015 269 7', '473349066', 
'Raised areas of inflamed skin covered with silvery white scaly skin on the ' || 
'elbows, knees, scalp, and back.; Inflammation and exfoliation ' || 
'of the skin in these areas.;', 'Psoriasis.;',  'Evans', 'nGxum2uOdpU=');

--prescription
INSERT INTO prescription (id, patientid, prescription, drugid) 
VALUES(1, 1, 'TYLENOL Extra Strength, 3 times a day, bed rest'|| CHR(10) ||'VIIBRYD, once a day in the morning'|| CHR(10) ||'ETODOLAC, once a day in the evening', '|9|26|13|');
INSERT INTO prescription (patientid, prescription, drugid) 
VALUES(1, 'Tizanidine, twice a day'|| CHR(10) ||'ELIDEL, once a day before sleep'|| CHR(10) ||'KETOCONAZOLE, once a day in the morning', '|12|2|8|');
INSERT INTO prescription (patientid, prescription, drugid) 
VALUES(2, 'SILDENAFIL, once a day'|| CHR(10) ||'SELENIUM SULFIDE, after shower'|| CHR(10) ||'STARLIX, twice a day', '|3|21|23|');
INSERT INTO prescription (patientid, prescription, drugid) 
VALUES(2, 'KETOCONAZOLE, once a day'|| CHR(10) ||'PROGESTERONE, once a day, when you feel it is necessary'|| CHR(10) ||'VASOTEC, twice a day before meal', '|2|19|5|');
INSERT INTO prescription (patientid, prescription, drugid) 
VALUES(3, 'VASOTEC, once a day'|| CHR(10) ||'ELMIRON, three times a day'|| CHR(10) ||'TAZORAC, once a day after shower', '|5|14|7|');
INSERT INTO prescription (patientid, prescription, drugid) 
VALUES(4, 'TAZORAC, twice a day'|| CHR(10) ||'ELMIRON, tree times a day during meal'|| CHR(10) ||'PATANASE, once a day in each nostril', '|7|14|18|');

-- laboratory
INSERT INTO laboratory (id, labname, labaddress, labphone) 
VALUES (1, 'CDL Medical Laboratories', '5990, ch de la C�te-des-Neiges, Montr�al, QC H3S 1Z5', '514-344-8022');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Radiologie Varad ', '150, rue Sainte-Catherine O, Montr�al, QC H2X 3Y2', '514-281-1355');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES (' RMD Sommeil ','142-100, ch Rockland, Mont-Royal, QC H3P 2V9','514-341-2111');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Clinique M�dicale Plexo ', '6100 Avenue du Bois�, Montr�al, QC H3S 2W1', '514-251-9331');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Pr�l�vExpress Enr ', '510-201, ch du Club-Marin, Verdun, QC H3E 1T4', '514-644-7264');

-- tests
INSERT INTO tests (id, name, res, ardate, depdate, labid) VALUES (1, 'ADA, BT', 'positive', '15-JUL-2014', '23-JUL-2014', 1);
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('Hemoglobin, BT','positive', '15-JUL-2014', '16-JUL-2014', 2);
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('Adenosylhomocysteine, BT','positive', '17-JUL-2014','19-JUL-2014', 3);
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('Free Androgen Index, BT','negative', '12-JUL-2014', '23-JUL-2014', 4 );
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('N-Acetylaspartic Acid, UT','positive', '25-JUL-2014', '07-JUL-2014', 5);
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('S-Adenosylmethionine, BT','negative', '27-JUL-2014', '08-AUG-2014', 1);
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('Lidocaine, BT','positive', '27-JUL-2014', '08-AUG-2014', 3);
INSERT INTO tests (name, res, ardate, depdate, labid) VALUES ('Trace Elements, UT','negative', '27-JUL-2014', '08-AUG-2014', 2);


-- staff schedual table
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (1,1,'Mon', '9.00-10.00', '9.00-10.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (1,2,'Thu', NULL, '12.00-1.00; 2.00-3.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (2,1,'Mon', '9.00-10.00', '9.00-10.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (2,1,'Wed', '10.00-11.15', '13.00-16.45');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (3,1,'Mon', '10.00-11.00','5.00-7.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (2,2,'Tue', '9.00-10.00','9.00-10.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (3,3,'Wed', '9.00-10.00','9.00-10.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (10,1,'Tue', '8.00-10.00','1.00-3.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (10,2,'Thu', '9.00-10.00','9.00-10.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (4,4,'Wed', '11.00-12.00','2.00-4.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (4,5,'Mon', '10.00-11.00','12.00-4.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (5,5,'Fri', '10.00-11.00','10.00-11.00');
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (5,1,'Mon', '9.00-10.00; 11.00-11.45', NULL);
INSERT INTO staffSchedule (staffid, hospid, workday, workhouram, workhourpm) VALUES (5,2,'Thu', '10.00-12.00','1.00-4.00');

-- schedule
INSERT INTO schedule (id, patientid, sdate, staffid, testid, confirm) 
VALUES (1, 1,  TO_TIMESTAMP('2014/06/12 12:30', 'yyyy/mm/dd hh24:mi'), 1, 1, 'Y');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (1, TO_TIMESTAMP('2014/08/11 12:30', 'yyyy/mm/dd hh24:mi'), 1, 2, 'N');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (1, TO_TIMESTAMP('2014/08/20 13:00', 'yyyy/mm/dd hh24:mi'), 2, 3, 'Y');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (2, TO_TIMESTAMP('2014/08/05 09:00', 'yyyy/mm/dd hh24:mi'), 1, 4, 'N');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (3,  TO_TIMESTAMP('2014/08/06 13:00', 'yyyy/mm/dd hh24:mi'), 2, 5, 'Y');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (1,  TO_TIMESTAMP('2014/08/06 13:30', 'yyyy/mm/dd hh24:mi'), 2, 6, 'N');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (4,  TO_TIMESTAMP('2014/08/11 12:30', 'yyyy/mm/dd hh24:mi'), 4, 7, 'Y');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (1,  TO_TIMESTAMP('2014/08/19 17:30', 'yyyy/mm/dd hh24:mi'), 2, 8, 'N');
INSERT INTO schedule (patientid, sdate, staffid, testid, confirm) 
VALUES (5,  TO_TIMESTAMP('2014/08/14 15:30', 'yyyy/mm/dd hh24:mi'), 5, null, 'Y');


commit;
