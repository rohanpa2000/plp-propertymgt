DELIMITER $$
DROP PROCEDURE IF EXISTS sp_insert_booking$$

CREATE  PROCEDURE sp_insert_booking(
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

	DECLARE l_id  INT; 

	SET l_id = (SELECT id FROM booking WHERE id = p_id) ;
    
    select l_id;
    

	IF (l_id IS NULL ) THEN

		INSERT INTO booking
		(
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
			p_court_name,
			p_start_time,
			p_end_time,
			p_booking_date,
			p_customer_name,
			p_cost,
			p_is_completed
		)
    ;END IF
;END$$
DELIMITER ;
