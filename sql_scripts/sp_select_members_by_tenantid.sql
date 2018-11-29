DELIMITER $$
DROP PROCEDURE IF EXISTS sp_select_members_by_tenantid$$

CREATE PROCEDURE sp_select_members_by_tenantid 
(
	IN p_tenantid INT 
)
BEGIN
	SELECT
			member_id,
			tenant_id,
            name,
			nickname,
			display_name,
			phone,
			email,
			postal

    FROM member
    WHERe 
		tenant_id = p_tenantid
;
END
$$
DELIMITER ;