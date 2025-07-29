DROP TABLE IF EXISTS ACCOUNT;

CREATE TABLE ACCOUNT
(
	id bigint NOT NULL,
	first_name varchar (100) NOT NULL,
	last_name varchar (100) NOT NULL,
	account_type ENUM('BASIC','FIXED_DEPOSIT','CURRENT','SAVINGS') NOT NULL,
	primary key (id)	
);