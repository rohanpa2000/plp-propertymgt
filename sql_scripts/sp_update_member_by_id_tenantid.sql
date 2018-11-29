DELIMITER $$
DROP PROCEDURE IF EXISTS sp_update_member_by_id_tenantid$$


CREATE PROCEDURE sp_update_member_by_id_tenantid 
(
	p_member_id INT,
	p_tenant_id INT,
	p_name varchar(45),
	p_nickname varchar(45),
	p_display_name varchar(45),
	p_phone varchar(45),
	p_email varchar(45),
	p_postal varchar(45)

)
BEGIN
UPDATE booking
SET
	member_id = p_member_id,
	tenant_id = p_tenant_id,
	name = p_name,
	nickname = p_nickname,
	display_name = p_display_name,
	phone = p_phone,
	email = p_email,
	postal = p_postal
WHERE 
	member_id = p_member_id and tenant_id = p_tenant_id
;
END
$$
DELIMITER ;