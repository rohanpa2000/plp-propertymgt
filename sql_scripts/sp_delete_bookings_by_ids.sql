DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_bookings_by_ids`(
	IN p_ids varchar(45),
	IN p_tenantids varchar(20)
)
BEGIN

    set @qry = concat('DELETE FROM booking WHERE id IN (',p_ids,') and tenant_id in (',p_tenantids,')');
    prepare stmp from @qry;
    execute stmp ;
    deallocate prepare stmp;
END$$
DELIMITER ;
