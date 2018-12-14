DELIMITER $$
DROP PROCEDURE IF EXISTS sp_update_booking_by_id$$

CREATE PROCEDURE sp_update_booking_by_id 
(
	IN p_booking_id int,
	IN p_tenant_id int,
	IN p_member_nickname varchar(45),
	IN p_item_id int,
	IN p_start_time date,
	IN p_end_time date,
	IN p_actual_start_time date,
	IN p_actual_end_time date,
	IN p_cost int,
	IN p_is_paid int
)
BEGIN

	DECLARE l_member_id  INT; 

	SET l_member_id = (SELECT member_id FROM member WHERE nickname like p_member_nickname AND tenant_id = p_tenant_id) ;


UPDATE booking
SET
    member_id = l_member_id,
	start_time = p_start_time,
    end_time = p_end_time,
    actual_start_time = p_actual_start_time,
    actual_end_time = p_actual_end_time,
    cost = p_cost,
    is_paid = p_is_paid
 WHERE 
	booking_id = p_booking_id
    AND tenant_id = p_tenant_id
    AND item_id = p_item_id
;
END
$$
DELIMITER ;