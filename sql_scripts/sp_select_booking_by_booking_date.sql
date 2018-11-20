DELIMITER $$
DROP PROCEDURE IF EXISTS sp_select_booking_by_booking_date$$

CREATE PROCEDURE sp_select_booking_by_booking_date 
(
	IN p_booking_date date 
)
BEGIN
	SELECT
			id,
			court_name,
            booking_date,
			start_time,
			end_time,
			customer_name,
			cost,
			is_completed

    FROM booking
    WHERe 
		booking_date = p_booking_date
;
END
$$
DELIMITER ;