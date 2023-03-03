/*CommonDao.updateWorkflowDetails = UPDATE t_workflow_dtls \
	SET Status_Id = :statusID \
	, Actor_Id = :actorID \
	, Actor_Name = :actorName \
	, Role_Id = :roleID \
	, Role_Name = :roleName \
	, Action_Date = CURRENT_TIMESTAMP \
WHERE Application_Number = :applicationNumber*/
/*publicDao.getMinistries= SELECT Ministry_Id AS ministry_Id, GRV_Catagory_Desc AS header_Name FROM t_grievance_catagory*/

publicDao.getMinistriesReport=SELECT Ministry_Id AS ministry_Id, Ministry_Name AS ministryName, Ministry_Short_Desc AS ministry_Short_Desc  FROM t_ministry_lookup;
publicDao.getMinistries=SELECT Ministry_Id AS ministry_Id, Ministry_Name AS ministryName, Ministry_Short_Desc AS ministry_Short_Desc  FROM t_ministry_lookup WHERE Ministry_Id!='22' ;
publicDao.getDepartments=SELECT Department_Id AS department_Id, Department_Name AS department_Name, Department_Short_Desc AS department_Short_Desc, Ministry_Id AS ministry_Id FROM t_department_lookup;
/*
publicDao.getApplicationSerial = SELECT Last_Application_Number AS request_Service_Id FROM t_application_sequence WHERE Service_Id = :globalServiceTypeId*/

publicDao.getDocumnets = SELECT d.Document_Name filename,d.Upload_URL uploadUrl,d.UUID uuid, d.Document_Type AS documentType FROM t_document d WHERE d.Application_Number=?;
publicDao.getDivisions=SELECT Dept_Division_Id AS dept_Division_Id, Dept_Division AS dept_Division, Department_id AS department_id FROM t_dept_division_lookup;