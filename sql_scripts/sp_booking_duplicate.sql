DELIMITER $$
DROP PROCEDURE IF EXISTS sp_booking_duplicate$$

CREATE PROCEDURE sp_booking_duplicate 
(
	IN p_booking_date VARCHAR(30) 
)
BEGIN

INSERT INTO booking(
	court_name, 
	start_time,
	end_time,
    booking_date,
	customer_name, 
	cost,
    is_completed
    )
SELECT	
	court_name, 
	DATE_ADD(start_time, INTERVAL 7 DAY) as start_time,
	DATE_ADD(end_time, INTERVAL 7 DAY) as end_time,
    DATE_ADD(booking_date, INTERVAL 7 DAY) as booking_date,
	customer_name, 
	cost,
    0 as is_completed
FROM booking 
	WHERE booking_date = DATE_ADD(p_booking_date, INTERVAL -7 DAY);
END
$$
DELIMITER ;

DELIMITER $$