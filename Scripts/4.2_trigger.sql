USE tvondemand;

-- INSERT LOG TRIGGER FOR RENTAL --
DROP TRIGGER IF EXISTS handlingDiscount;
DELIMITER $

CREATE TRIGGER handlingDiscount
BEFORE INSERT
ON payment
FOR EACH ROW
BEGIN	
	DECLARE email VARCHAR(128);
	DECLARE rentalDate DATE;
	DECLARE maxRentalDate DATETIME;
	

	SELECT c.email, DATE(r.rental_date), MAX(r.rental_date) INTO email, rentalDate, maxRentalDate FROM customer c
	INNER JOIN rental r ON r.rental_id = NEW.rental_id
	WHERE NEW.customer_id = c.customer_id;

	CALL getNumberOfRentals(
		email,
		rentalDate,
		@no_of_rentals,
		@max_date -- LAST rental date
	);
	
	IF((@no_of_rentals % 3) = 0)
		THEN
			IF(@max_date = maxRentalDate)	
				THEN
					SET NEW.amount = NEW.amount * 0.5;
			END IF;
	END IF;
	
END$

DELIMITER ;