USE tvondemand;

DELIMITER $
DROP PROCEDURE IF EXISTS getIncomePerMonth$

CREATE PROCEDURE getIncomePerMonth()
BEGIN
	SELECT * FROM (
	SELECT DATE_FORMAT(p.payment_date, "%Y-%m") AS `YEAR_MONTH`, SUM(p.amount) AS INCOME, 'MOVIES' AS `TYPE` FROM payment p
	INNER JOIN rental r ON r.rental_id = p.rental_id
	INNER JOIN inventory i ON i.inventory_id = r.inventory_id AND i.serie_id IS NULL
	WHERE r.isPaid = true
	GROUP BY `YEAR_MONTH`, `TYPE`
	UNION
	SELECT DATE_FORMAT(p.payment_date, "%Y-%m") AS `YEAR_MONTH`, SUM(p.amount) AS INCOME, 'SERIES' AS `TYPE` FROM payment p
	INNER JOIN rental r ON r.rental_id = p.rental_id
	INNER JOIN inventory i ON i.inventory_id = r.inventory_id AND i.serie_id IS NOT NULL
	WHERE r.isPaid = true
	GROUP BY `YEAR_MONTH`, `TYPE`
	)AS T
	GROUP BY `YEAR_MONTH`, `TYPE`
	ORDER BY `YEAR_MONTH` ASC;
	
END$
	
DELIMITER ;
	
CALL getIncomePerMonth();