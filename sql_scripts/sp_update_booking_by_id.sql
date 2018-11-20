DELIMITER $$
DROP PROCEDURE IF EXISTS sp_update_booking_by_id$$

CREATE PROCEDURE sp_update_booking_by_id 
(
	p_id int,
	p_court_name varchar(45),
	p_start_time date,
	p_end_time date,
	p_booking_date date,
	p_customer_name varchar(45),
	p_cost int,
	p_is_completed varchar(45)
)
BEGIN
UPDATE booking
SET
	court_name = p_court_name,
	start_time = p_start_time,
	end_time = p_end_time,
	booking_date = p_booking_date,
	customer_name = p_customer_name,
	cost = p_cost,
	is_completed = p_is_completed
WHERE id = p_id
;
END
$$
DELIMITER ;