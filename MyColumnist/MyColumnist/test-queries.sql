-- Select columnist of a newspaper
SELECT 
	* 
FROM 
	column_table a , columnist_table b 
WHERE 
	a.columnist_id=b.columnist_id AND b.newspaper='Haberturk';
	
-- 
