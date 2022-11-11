USE tvondemand;

DROP PROCEDURE IF EXISTS getActorsFullName;

DELIMITER $

CREATE PROCEDURE getActorsFullName(IN lastName VARCHAR(45))
BEGIN
	
	DECLARE countOfActors INT;

	SELECT count(*) INTO countOfActors FROM actor a
	WHERE
	MATCH(a.last_name) AGAINST(lastName IN NATURAL LANGUAGE MODE);
	
	SELECT a.first_name, a.last_name 
	FROM actor a
	WHERE
	MATCH(a.last_name) AGAINST(lastName IN NATURAL LANGUAGE MODE);

	IF(countOfActors > 1)
		THEN
			SELECT countOfActors AS 'NO. OF ACTORS WITH THE SPECIFIC LAST NAME';
	END IF;
END$
	 
DELIMITER ;
	
CALL getActorsFullName('Walters');