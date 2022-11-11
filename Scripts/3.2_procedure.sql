USE tvondemand;

DELIMITER $
DROP PROCEDURE IF EXISTS getNumberOfRentals$

CREATE PROCEDURE getNumberOfRentals(IN email varchar(50), IN rentalDate DATE, OUT no_of_rentals INT, OUT maxDate DATETIME)
BEGIN
	DECLARE cusID INT;
	DECLARE rentals INT;
	DECLARE dat DATETIME;

	SELECT c.customer_id INTO cusID FROM customer c
	WHERE c.email = email;

	SELECT COUNT(r.rental_id), MAX(r.rental_date) INTO rentals, dat
	FROM rental r
	WHERE r.customer_id = cusID AND DATE(r.rental_date) = rentalDate;

	SET no_of_rentals = rentals;
	SET maxDate = dat;
-- 	SELECT COUNT(r.rental_id) AS 'NO. OF RENTALS'
-- 	FROM rental r
-- 	WHERE r.customer_id = cusID AND DATE(r.rental_date) = rentalDate;
END$

DELIMITER ;

CALL getNumberOfRentals('LUIS.YANEZ@sakilacustomer.org', '2005-06-15', @rentals, @maxDate);

SELECT * FROM payment p WHERE DATE(p.payment_date) = '2005-06-15' AND p.customer_id = 402;