USE tvondemand;

DROP PROCEDURE IF EXISTS getActors;

DELIMITER $

CREATE PROCEDURE getActors(IN f CHAR(10), IN s CHAR(10))
BEGIN
	SELECT a.first_name, a.last_name 
	FROM actor a
	WHERE
	MATCH(a.last_name) AGAINST(concat(f , '*') IN BOOLEAN MODE);
END$
	 
DELIMITER ;
	
CALL getActors('WAR','WAT');