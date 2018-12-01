DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_bookings_by_ids`(
	p_ids varchar(45)
)
BEGIN

    set @qry = concat('DELETE FROM booking WHERE id IN (',p_ids,')');
    prepare stmp from @qry;
    execute stmp ;
    deallocate prepare stmp;
END$$
DELIMITER ;
