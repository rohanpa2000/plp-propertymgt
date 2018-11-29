DELIMITER $$
DROP PROCEDURE IF EXISTS sp_insert_member$$

CREATE PROCEDURE sp_insert_member 
(
	p_tenant_id INT,
	p_name varchar(45),
	p_nickname varchar(45),
	p_display_name varchar(45),
	p_phone varchar(45),
	p_email varchar(45),
	p_postal varchar(45)
)
BEGIN
INSERT INTO booking
(
	member_id,
	tenant_id,
	name,
	nickname,
	display_name,
	phone,
	email,
	postal
)
VALUES
(
	p_member_id,
	p_tenant_id,
	p_name,
	p_nickname,
	p_display_name,
	p_phone,
	p_email,
	p_postal
)
;
END
$$
DELIMITER ;