BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "patient_residential_address_list" (
	"patient_patient_id"	bigint NOT NULL,
	"address"	varchar(255)
);
CREATE TABLE IF NOT EXISTS "patient_phone_number_list" (
	"patient_patient_id"	bigint NOT NULL,
	"number"	varchar(255)
);
CREATE TABLE IF NOT EXISTS "patient_known_parent_list" (
	"patient_patient_id"	bigint NOT NULL,
	"first_name"	varchar(255),
	"last_name"	varchar(255)
);
CREATE TABLE IF NOT EXISTS "patient_email_address_list" (
	"patient_patient_id"	bigint NOT NULL,
	"email"	varchar(255)
);
CREATE TABLE IF NOT EXISTS "patient" (
	"patient_id"	integer,
	"city_of_birth"	varchar(255),
	"date_of_birth"	timestamp,
	"established_diagnosis"	varchar(255),
	"first_name"	varchar(255),
	"genre"	tinyint CHECK(genre between 0 and 1),
	"health_insurance_number"	varchar(255),
	"is_doctor"	boolean,
	"is_healthcare_professional"	boolean,
	"last_name"	varchar(255),
	PRIMARY KEY("patient_id")
);
CREATE TABLE IF NOT EXISTS "medical_visit_diagnosis_list" (
	"medical_visit_medical_visit_id"	bigint NOT NULL,
	"description"	varchar(255),
	"treatment"	varchar(255)
);
CREATE TABLE IF NOT EXISTS "medical_visit" (
	"medical_visit_id"	integer,
	"notes_for_other_doctors"	varchar(255),
	"summary_of_the_visit_by_doctor"	varchar(255),
	"visit_date"	timestamp,
	"visited_establishment"	varchar(255),
	"doctor_seen_doctor_id"	bigint UNIQUE,
	PRIMARY KEY("medical_visit_id")
);
CREATE TABLE IF NOT EXISTS "medical_record_medical_visit_list" (
	"medical_record_medical_record_id"	bigint NOT NULL,
	"medical_visit_list_medical_visit_id"	bigint NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS "medical_record_medical_history_list" (
	"medical_record_medical_record_id"	bigint NOT NULL,
	"medical_history_list_medical_history_id"	bigint NOT NULL UNIQUE
);
CREATE TABLE IF NOT EXISTS "medical_record" (
	"medical_record_id"	integer,
	"patient_patient_id"	bigint,
	PRIMARY KEY("medical_record_id")
);
CREATE TABLE IF NOT EXISTS "medical_history_illness_list" (
	"medical_history_medical_history_id"	bigint NOT NULL,
	"description"	varchar(255),
	"end_of_illness_date"	timestamp,
	"onset_of_illness_date"	timestamp
);
CREATE TABLE IF NOT EXISTS "medical_history" (
	"medical_history_id"	integer,
	"diagnosis"	varchar(255),
	"treatment"	varchar(255),
	"primary_care_doctor_doctor_id"	bigint UNIQUE,
	PRIMARY KEY("medical_history_id")
);
CREATE TABLE IF NOT EXISTS "doctor" (
	"doctor_id"	integer,
	"first_name"	varchar(255),
	"last_name"	varchar(255),
	"specialization"	varchar(255),
	PRIMARY KEY("doctor_id")
);
INSERT INTO "patient_known_parent_list" VALUES (1,'James','Johnson');
INSERT INTO "patient_email_address_list" VALUES (1,'johnn@gmail.com');
INSERT INTO "patient" VALUES (1,'Montreal',19781010,'cancer','David',1,'ABCD123456789','False','False','Johnson');
INSERT INTO "medical_visit_diagnosis_list" VALUES (1,'description','treatement1');
INSERT INTO "medical_visit" VALUES (1,'note1','summery',20231011,'hopital1',1);
INSERT INTO "medical_record_medical_visit_list" VALUES (1,1);
INSERT INTO "medical_record_medical_history_list" VALUES (1,1);
INSERT INTO "medical_record" VALUES (1,1);
INSERT INTO "medical_history_illness_list" VALUES (1,'Fever',20231011,20220809);
INSERT INTO "medical_history" VALUES (1,'Common cold','Rest and fluids',1);
INSERT INTO "doctor" VALUES (1,'Jean','Delva','Cardiologue');
INSERT INTO "patient_phone_number_list" VALUES (1, 5145448989);
INSERT INTO "patient_residential_address_list" VALUES (1, "5454 rue dupuis Montréal H6Y 7U8");
COMMIT;
