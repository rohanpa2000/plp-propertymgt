DELIMITER $$
DROP PROCEDURE IF EXISTS sp_insert_booking$$

CREATE  PROCEDURE sp_insert_booking(
	p_id int,
	p_booking_date date,
	p_start_time datetime,
	p_end_time datetime,
	p_customer_name varchar(45),
	p_cost int,
	p_is_paid varchar(45),
    p_tenant_id int,
    p_item_id int
)
BEGIN

			DECLARE l_member_id  INT; 

			SET l_member_id = (SELECT member_id FROM member WHERE nickname like p_customer_name AND tenant_id = p_tenant_id) ;
    
    

		INSERT INTO booking
		(
			start_time,
			end_time,
			booking_date,
			cost,
			is_paid,
            tenant_id,
            item_id,
            member_id
		)
		VALUES
		(
			p_start_time,
			p_end_time,
			p_booking_date,
			p_cost,
			p_is_paid,    
            p_tenant_id,
			p_item_id,
            l_member_id
		)
;END$$
DELIMITER ;
