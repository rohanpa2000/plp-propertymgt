DELIMITER $$
DROP PROCEDURE IF EXISTS sp_insert_booking$$

CREATE PROCEDURE sp_insert_booking 
(
	p_id int,
	p_court_name varchar(45),
	p_booking_date date,
	p_start_time datetime,
	p_end_time datetime,
	p_customer_name varchar(45),
	p_cost int,
	p_is_completed varchar(45)
)
BEGIN
INSERT INTO booking
(
	id,
	court_name,
	start_time,
	end_time,
	booking_date,
	customer_name,
	cost,
	is_completed
)
VALUES
(
	p_id,
	p_court_name,
	p_start_time,
	p_end_time,
	p_booking_date,
	p_customer_name,
	p_cost,
	p_is_completed
)
;
END
$$
DELIMITER ;