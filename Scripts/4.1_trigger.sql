USE tvondemand;

-- INSERT LOG TRIGGER FOR RENTAL --
DROP TRIGGER IF EXISTS insert_log_rental;
DELIMITER $

CREATE TRIGGER insert_log_rental
AFTER INSERT
ON rental
FOR EACH ROW
BEGIN	
	DECLARE fname varchar(45);
	DECLARE lname varchar(45);
	DECLARE executionMsg varchar(45);

		-- on success --
	SET executionMsg = 'SUCCEED';

	SELECT DISTINCT first_name, last_name INTO fname, lname FROM customer c 
	WHERE NEW.customer_id = c.customer_id;
	
	INSERT INTO log(`username`, `action_name`, `table_name`, `date`, `execution`)
	VALUES
	(CONCAT(fname, " ", lname), 'INSERT', 'RENTAL', NOW(), executionMsg);
END$

DELIMITER ;

-- UPDATE LOG TRIGGER FOR RENTAL --
DROP TRIGGER IF EXISTS update_log_rental;
DELIMITER $

CREATE TRIGGER update_log_rental
AFTER UPDATE 
ON rental
FOR EACH ROW
BEGIN	
	DECLARE fname varchar(45);
	DECLARE lname varchar(45);
	DECLARE executionMsg varchar(45);

		-- on success --
	SET executionMsg = 'SUCCEED';

	SELECT DISTINCT first_name, last_name INTO fname, lname FROM customer c 
	WHERE NEW.customer_id = c.customer_id;
	
	INSERT INTO log(`username`, `action_name`, `table_name`, `date`, `execution`)
	VALUES
	(CONCAT(fname, " ", lname), 'UPDATE', 'RENTAL', NOW(), executionMsg);
END$

DELIMITER ;

-- DELETE LOG TRIGGER FOR RENTAL --
DROP TRIGGER IF EXISTS delete_log_rental;
DELIMITER $

CREATE TRIGGER delete_log_rental
AFTER DELETE 
ON rental
FOR EACH ROW
BEGIN	
	DECLARE fname varchar(45);
	DECLARE lname varchar(45);
	DECLARE executionMsg varchar(45);

		-- on success --
	SET executionMsg = 'SUCCEED';

	SELECT DISTINCT first_name, last_name INTO fname, lname FROM customer c 
	WHERE OLD.customer_id = c.customer_id;
	
	INSERT INTO log(`username`, `action_name`, `table_name`, `date`, `execution`)
	VALUES
	(CONCAT(fname, " ", lname), 'DELETE', 'RENTAL', NOW(), executionMsg);
END$

DELIMITER ;

-- INSERT LOG TRIGGER FOR PAYMENT --
DROP TRIGGER IF EXISTS insert_log_payment;
DELIMITER $

CREATE TRIGGER insert_log_payment
AFTER INSERT
ON payment
FOR EACH ROW
BEGIN	
	DECLARE fname varchar(45);
	DECLARE lname varchar(45);
	DECLARE executionMsg varchar(45);

		-- on success --
	SET executionMsg = 'SUCCEED';

	SELECT DISTINCT first_name, last_name INTO fname, lname FROM customer c 
	WHERE NEW.customer_id = c.customer_id;
	
	INSERT INTO log(`username`, `action_name`, `table_name`, `date`, `execution`)
	VALUES
	(CONCAT(fname, " ", lname), 'INSERT', 'PAYMENT', NOW(), executionMsg);
END$

DELIMITER ;

-- UPDATE LOG TRIGGER FOR PAYMENT --
DROP TRIGGER IF EXISTS update_log_payment;
DELIMITER $

CREATE TRIGGER update_log_payment
AFTER UPDATE 
ON payment
FOR EACH ROW
BEGIN	
	DECLARE fname varchar(45);
	DECLARE lname varchar(45);
	DECLARE executionMsg varchar(45);

		-- on success --
	SET executionMsg = 'SUCCEED';

	SELECT DISTINCT first_name, last_name INTO fname, lname FROM customer c 
	WHERE NEW.customer_id = c.customer_id;
	
	INSERT INTO log(`username`, `action_name`, `table_name`, `date`, `execution`)
	VALUES
	(CONCAT(fname, " ", lname), 'UPDATE', 'PAYMENT', NOW(), executionMsg);
END$

DELIMITER ;

-- DELETE LOG TRIGGER FOR PAYMENT --
DROP TRIGGER IF EXISTS delete_log_payment;
DELIMITER $

CREATE TRIGGER delete_log_payment
AFTER DELETE 
ON payment
FOR EACH ROW
BEGIN	
	DECLARE fname varchar(45);
	DECLARE lname varchar(45);
	DECLARE executionMsg varchar(45);

		-- on success --
	SET executionMsg = 'SUCCEED';

	SELECT DISTINCT first_name, last_name INTO fname, lname FROM customer c 
	WHERE OLD.customer_id = c.customer_id;
	
	INSERT INTO log(`username`, `action_name`, `table_name`, `date`, `execution`)
	VALUES
	(CONCAT(fname, " ", lname), 'DELETE', 'PAYMENT', NOW(), executionMsg);
END$

DELIMITER ;