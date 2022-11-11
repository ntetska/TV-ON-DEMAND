USE tvondemand;

DELIMITER $
DROP PROCEDURE IF EXISTS getMovieOrSerieInfo$

CREATE PROCEDURE getMovieOrSerieInfo(IN c CHAR, IN li INT, IN st DATE, IN en DATE) 
BEGIN
	IF(c = 'm') 
		THEN
			SELECT f.film_id AS ID, f.title AS title,
			COUNT(r.rental_id) AS RENTALS FROM film f
			INNER JOIN inventory i 
			ON i.film_id = f.film_id 
			INNER JOIN rental r 
			ON r.inventory_id = i.inventory_id 
-- 			WHERE r.rental_date BETWEEN st AND en
-- EMPLOYEE GUI PURPOSE
			WHERE r.rental_date BETWEEN DATE_ADD(st,INTERVAL -1 MONTH) AND DATE_ADD(en,INTERVAL -1 MONTH)
			GROUP BY f.film_id
			ORDER BY RENTALS DESC 
			LIMIT li;
	ELSEIF(c = 's')
		THEN
			SELECT s.serie_id AS ID, s.title AS title,
			COUNT(s2.episode_id)  AS RENTALS
			FROM serie s	
			INNER JOIN season s2 
			ON s.serie_id = s2.serie_id
			INNER JOIN inventory i 
			ON i.serie_id = s.serie_id 
			INNER JOIN rental r 
			ON r.inventory_id = i.inventory_id 
-- 			WHERE r.rental_date BETWEEN st AND en
-- EMPLOYEE GUI PURPOSE
			WHERE r.rental_date BETWEEN DATE_ADD(st,INTERVAL -1 MONTH) AND DATE_ADD(en,INTERVAL -1 MONTH)
			GROUP BY s.serie_id 
			ORDER BY RENTALS DESC 
			LIMIT li;
	ELSE SIGNAL SQLSTATE VALUE '45000' SET MESSAGE_TEXT = 'Invalid input!';
	END IF;	
END$

delimiter ;

CALL getMovieOrSerieInfo('s', 5, '2021-10-01', '2021-10-30');