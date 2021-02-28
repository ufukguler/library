INSERT INTO AUTHOR(name,comment,active,create_date,update_date,operation_type) VALUES('Author A','Comment A',true,NOW(),NOW(),'SAVE');
INSERT INTO AUTHOR(name,comment,active,create_date,update_date,operation_type) VALUES('Author B','Comment B',true,NOW(),NOW(),'SAVE');

INSERT INTO PUBLISHER(name,comment,active,create_date,update_date,operation_type) VALUES('Publisher A','Comment A',true,NOW(),NOW(),'SAVE');
INSERT INTO PUBLISHER(name,comment,active,create_date,update_date,operation_type) VALUES('Publisher B','Comment B',true,NOW(),NOW(),'SAVE');


INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book A','Alt A','Series A',111111111,'About A',1,1,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book B','Alt B','Series B',222222222,'About B',1,1,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book C','Alt C','Series C',333333333,'About C',1,2,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book D','Alt D','Series D',444444444,'About D',1,2,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book E','Alt E','Series E',555555555,'About E',2,1,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book F','Alt F','Series F',666666666,'About F',2,1,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book G','Alt G','Series G',777777777,'About G',2,2,true,NOW(),NOW(),'SAVE');
INSERT INTO BOOK(title,alt,series,isbn,comment,author_id,publisher_id, active,create_date,update_date,operation_type) VALUES('Book H','Alt H','Series H',888888888,'About H',2,2,true,NOW(),NOW(),'SAVE');
