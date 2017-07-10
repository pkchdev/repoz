CREATE TABLE ROLE (
	rol_id 		BIGINT NOT NULL AUTO_INCREMENT,
	rol_name 	VARCHAR(30) NOT NULL,
	PRIMARY KEY (rol_id),
	UNIQUE (rol_name)
);

INSERT INTO ROLE(rol_name)
SELECT 'ROLE_ADMIN' FROM DUAL UNION
SELECT 'ROLE_USER' FROM DUAL;


CREATE TABLE USER (
	use_id 			BIGINT NOT NULL AUTO_INCREMENT,
	use_username 	VARCHAR(50) NOT NULL,
	use_password 	VARCHAR(100) NOT NULL,
	use_rol_id 		BIGINT(45) NOT NULL,
	use_creation_date 	DATETIME NOT NULL DEFAULT NOW(),
	PRIMARY KEY (use_id),
	UNIQUE (use_username),
	CONSTRAINT FK_USER_ROLE FOREIGN KEY (use_rol_id) REFERENCES ROLE (rol_id)
);

INSERT INTO USER(use_username, use_password, use_rol_id)
SELECT 'pkch', 'pkch', 1 FROM DUAL UNION
SELECT 'test', 'test', 1 FROM DUAL;

