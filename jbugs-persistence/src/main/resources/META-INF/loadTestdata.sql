<<<<<<< HEAD
DELETE FROM ROLE_PERMISSION;
DELETE FROM PERMISSION;
DELETE FROM ROLE;
DELETE FROM USER;


INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION) VALUES ('user11','John','Smith','pass','1', 'john_smith@msggroup.com','1');

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
=======
DELETE FROM ROLE_PERMISSION;
DELETE FROM PERMISSION;
DELETE FROM ROLE;
DELETE FROM USER;


INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD) VALUES ('user11','John','Smith','pass');

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
>>>>>>> 804bf73cfe90df790ab388e15050ab4ac5812502
>>>>>>> edb8b65b9aa93df16319ec746e4a452bec6df4b3
