insert into users(username, password, enabled) values ('alumno', '{bcrypt}$2a$04$JI9kb21KRCIJbZ/vzavSJ.XnujoFV/ZUwJFFQKPovjAlaHz3/AEKW', true);
insert into authorities(username, authority) values ('alumno', 'ROLE_ALUM');

insert into users(username, password, enabled) values ('profesor', '{bcrypt}$2a$04$N.2kXPvQB6Nf9CRKsABaDOhXGGp1eXsawPSrP/l9eEEPuKz00ZjT6', true);
insert into authorities(username, authority) values ('profesor', 'ROLE_PROF');