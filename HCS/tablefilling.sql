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
INSERT INTO staff (id, fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES (1, 'John F.', 'Gilbert', '08-JAN-1961',  'JohnFGilbert@teleworm.us', 
'96 Rue de la Gauchetière E Montreal', 'L1H 7K5',  '905-431-1311', '673747515', 1, 1, 1,
'susanin', 'nGxum2uOdpU=');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES ('Robert J.', 'Moore', '24-APR-1945' ,  'RobertJMoore@dayrep.com' , 
'3708 Avenue Henri Julien Montreal', 'V2S 2H7' ,  '604-853-8638', '366987444', 1, 1, 2,
'test', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES ('Shirley', 'Minor', '14-NOV-1967'  ,  'ShirleyJMinor@armyspy.com', 
'4293 Avenue de Esplanade Montreal' , 'T5J 2R4' ,  '780-996-5578', '726768716', 2, 2, 1,
'test1', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES ('Lane W.', 'Bentley', '18-JAN-1965' ,  'LaneWBentley@rhyta.com' , 
'252 Ch de la Côte-Sainte-Catherine Montreal', 'P0T 2L0'  ,  '807-349-5758', '090601576', 3, 1, 3,
'test2', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES ('Drew B.', 'Dalley', '23-DEC-1971',  'DrewBDalley@dayrep.com ', 
'444 Avenue Champagneur Montreal ', 'M4W 1J7' ,  '416-318-8430', '521398032', 3, 3, 3,
'test3', 'nGxum2uOdpU=' );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES ('Frank I.', 'Sims', '27-JUL-1980' ,  'FrankISims@armyspy.com' , 
'274 67e Av Laval' , 'V7L 2C1' ,  '604-960-7790', '709809909', 4, 3, 4,
'test4', 'nGxum2uOdpU=' );

INSERT INTO drugstores (id, name, address, phone, openh) VALUES (1, 'Pharmaprix', '1500 Rue Sainte-Catherine Ouest', '514-933-4744', '8:00 am - 12:00 am daily');

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Jean Coutu' , '7145 St Denis Montreal', '514-495-8686', '9:00 am - 10:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Proxim' , '7684 Blvd St. Michel Montreal', '514-725-4738', '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Brunet Pharmacies Affiliees', '151 Atwater Av Montreal', '514-935-5637',  '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Uniprix', '4349 Belanger Rue Montreal', '514-725-5273', '9:00 am - 9:00 pm daily' );


INSERT INTO drugs (id, name, dose, price, avlb) VALUES (1, 'ACCOLATE', '20 MG TABLET', 135.80, 'Y');
INSERT INTO drugs (name, dose, price, avlb) VALUES ('KETOCONAZOLE', '2% CREAM', 12.46, 'N' );
INSERT INTO drugs (name, dose, price, avlb) VALUES ('SILDENAFIL', '20 MG TABLET', 24.31, 'Y' );
INSERT INTO drugs (name, dose, price, avlb) VALUES ('PREVACID', '15 MG CAPSULE', 300.77, 'Y' );
INSERT INTO drugs (name, dose, price, avlb) VALUES ('VASOTEC', '20 MG TABLET', 306.02, 'N' );
INSERT INTO drugs (name, dose, price, avlb) VALUES ('ELMIRON', '100 MG CAPSULE', 220.31, 'Y');
INSERT INTO drugs (name, dose, price, avlb) VALUES ('TAZORAC', '0.05% GEL' , 299.86, 'Y');
INSERT INTO drugs (name, dose, price, avlb) VALUES ('TIZANIDINE', '2 MG TABLET', 11.45, 'N'  );
INSERT INTO drugs (name, dose, price, avlb) VALUES ('TYLENOL Extra Strength' , '500 mg caplets', 12.99, 'Y');



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