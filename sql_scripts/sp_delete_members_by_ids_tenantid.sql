DELIMITER $$
DROP PROCEDURE IF EXISTS sp_delete_members_by_ids_tenantid$$

CREATE PROCEDURE sp_delete_members_by_ids_tenantid 
(
	IN p_memberids varchar(100),
    IN p_tenantids varchar(20)
)

BEGIN

    set @qry = concat('DELETE FROM member WHERE member_id IN (',p_memberids,') and tenant_id in (',p_tenantids,')');
    prepare stmp from @qry;
    execute stmp ;
    deallocate prepare stmp;
END$$
DELIMITER ;