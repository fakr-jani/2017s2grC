
DELETE FROM ROLE_PERMISSION;
DELETE FROM PERMISSION;
DELETE FROM ROLE;
DELETE FROM USER;


INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION) VALUES ('user11','John','Smith','pass','0', 'john_smith@msggroup.com','1');
INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION) VALUES ('user12','Mary','Smith','pass','1', 'mary_smith@msggroup.com','1');
INSERT INTO USER(USERNAME, FIRSTNAME, LASTNAME, PASSWORD,ACTIVE, EMAIL, LOCKVERSION) VALUES ('user13','Kitty','Smith','pass','0', 'kitty_smith@msggroup.com','1');

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

INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (1,1);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (2,2);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (3,4);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (4,2);
INSERT INTO ROLE_PERMISSION(IDROLE, IDPERMISSION) VALUES (5,3);