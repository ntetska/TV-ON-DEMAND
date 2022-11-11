-- HANDLING BEFORE UPDATE LOG TRIGGER FOR CUSTOMER EMAIL --
DROP TRIGGER IF EXISTS handling_update_email_log_customer;
DELIMITER $

CREATE TRIGGER handling_update_email_log_customer
BEFORE UPDATE 
ON customer
FOR EACH ROW
BEGIN	
	
	DECLARE MESSAGE_TEXT varchar(128);

    IF NEW.email <> (SELECT email FROM `customer` WHERE customer_id = NEW.customer_id) THEN
    	SIGNAL SQLSTATE '45000' -- error --
    		SET MESSAGE_TEXT = 'EMAIL CANNOT BE CHANGED!';
    END IF;
END$

DELIMITER ;

-- UPDATE customer AS c SET c.email = 'HALAL.YANEZ@sakilacustomer.org' WHERE c.customer_id = 402;