DELETE FROM USER_ROLE;
DELETE FROM ATTACHMENT;
DELETE FROM BUG;
DELETE FROM ROLE_PERMISSION;
DELETE FROM PERMISSION;
DELETE FROM ROLE;
DELETE FROM USER;
INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION,COUNTER,PHONENUMBER) VALUES ('SmithS','Samantha','Smith','pass','1', 'samy_smith@msggroup.com','1','0','+40747567350');
INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION,COUNTER,PHONENUMBER) VALUES ('SmithJ','John','Smith','pass','0', 'john_smith@msggroup.com','1','0','+40747567350');
INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION,COUNTER,PHONENUMBER) VALUES ('SmithM','Mary','Smith','pass','1', 'mary_smith@msggroup.com','1','0','+40747567350');
INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION,COUNTER,PHONENUMBER) VALUES ('SmithK','Kitty','Smith','pass','0', 'kitty_smith@msggroup.com','1','0','+40747567350');

INSERT INTO PERMISSION(NAMEPERMISSION,DETAILPERMISSION, LOCKVERSION) VALUES ('BUG_MANAGEMENT','', 1);
INSERT INTO PERMISSION(NAMEPERMISSION,DETAILPERMISSION, LOCKVERSION) VALUES ('PERMISSION_MANAGEMENT','', 1);
INSERT INTO PERMISSION(NAMEPERMISSION,DETAILPERMISSION, LOCKVERSION) VALUES ('USER_MANAGEMENT','', 1);
INSERT INTO PERMISSION(NAMEPERMISSION,DETAILPERMISSION, LOCKVERSION) VALUES ('BUG_CLOSE','', 1);
INSERT INTO PERMISSION(NAMEPERMISSION,DETAILPERMISSION, LOCKVERSION) VALUES ('BUG_EXPORT_PDF','', 1);

INSERT INTO ROLE(ROLENAME, LOCKVERSION) VALUES('ADMINISTRATOR', 1);
INSERT INTO ROLE(ROLENAME, LOCKVERSION) VALUES('PROJECT_MANAGER', 1);
INSERT INTO ROLE(ROLENAME, LOCKVERSION) VALUES('TEST_MANAGER', 1);
INSERT INTO ROLE(ROLENAME, LOCKVERSION) VALUES('DEVELOPER', 1);
INSERT INTO ROLE(ROLENAME, LOCKVERSION) VALUES('TESTER', 1);

INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(1,1);
INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(2,2);
INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(3,2);
INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(4,3);
INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(2,4);
INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(1,4);
INSERT INTO USER_ROLE(IDUSER, IDROLE) VALUES(3,1);

INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (1,1);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (2,2);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (3,4);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (4,2);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (5,3);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (4,3);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (2,3);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (4,4);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (2,5);

INSERT INTO BUG(TITLEBUG, DESCRIPTIONBUG, VERSION,SEVERITY, STATUS, LOCKVERSION,ASSIGNEDTO_IDUSER,CREATEDBY_IDUSER,TARGETDATE,VERSIONFIXED) VALUES ('Bug1', 'Bug1 description','1.2.3','LOW','CLOSED','1','1','2',STR_TO_DATE('12-12-2017', '%d-%m-%Y'),'1.2.4');
INSERT INTO BUG(TITLEBUG, DESCRIPTIONBUG,VERSION,SEVERITY, STATUS, LOCKVERSION,ASSIGNEDTO_IDUSER,CREATEDBY_IDUSER,TARGETDATE,VERSIONFIXED) VALUES ('Bug2', 'Bug2 description','1.2.3','MEDIUM','REJECTED','1','1','3',STR_TO_DATE('12-12-2017', '%d-%m-%Y'),'1.2.4');
INSERT INTO BUG(TITLEBUG, DESCRIPTIONBUG, VERSION,SEVERITY, STATUS, LOCKVERSION,ASSIGNEDTO_IDUSER,CREATEDBY_IDUSER,TARGETDATE,VERSIONFIXED) VALUES ('Bug3', 'Bug3 description', '1.2.3','CRITICAL','FIXED','1','2','1',STR_TO_DATE('12-12-2017', '%d-%m-%Y'),'1.2.4');
INSERT INTO BUG(TITLEBUG, DESCRIPTIONBUG, VERSION,SEVERITY, STATUS, LOCKVERSION,ASSIGNEDTO_IDUSER,CREATEDBY_IDUSER,TARGETDATE,VERSIONFIXED) VALUES ('Bug4', 'Bug4 description','1.2.3','HIGH','REJECTED','1','3','1',STR_TO_DATE('12-12-2017', '%d-%m-%Y'),'1.2.4');


INSERT INTO ATTACHMENT(BUG_IDBUG,FILENAME,FILEBYTES,LOCKVERSION) VALUES ('2','mario.png','T:\10_CODE\2017s2grC\jbugs-persistence\src\main\resources\META-INF\mario.png','1')