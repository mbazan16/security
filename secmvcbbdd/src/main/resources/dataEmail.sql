insert into Editorial(id,name,telefono,email) values(1,'Anaya',68989381,'info@anaya.com');
insert into Editorial(id,name,telefono,email) values(2,'Alfaguara',690765434,'info@alfaguara.com');
insert into Editorial(id,name,telefono,email) values(3,'Oxford',635895421,'info@oxford.com');
	
	
insert into Libro(id,editorial_id,autor,titulo) values(1,1,'Alejandro Perez','Matematicas 3');
insert into Libro(id,editorial_id,autor,titulo) values(2,1,'Alejandro Perez','Matematicas 4');
insert into Libro(id,editorial_id,autor,titulo) values(3,1,'Rebeca Medina','Lengua 4');
insert into Libro(id,editorial_id,autor,titulo) values(4,1,'Rebeca Medina','Lengua 4');
insert into Libro(id,editorial_id,autor,titulo) values(5,2,'Esteban Diaz','El pajaro voló');
insert into Libro(id,editorial_id,autor,titulo) values(6,2,'Maribel Montiel','La casa encantada');
insert into Libro(id,editorial_id,autor,titulo) values(7,2,'Alba Luz','Volver');
insert into Libro(id,editorial_id,autor,titulo) values(8,3,'Richard Phill','Intermediate Student Book');
insert into Libro(id,editorial_id,autor,titulo) values(9,3,'Richard Phill','Avanced Strudent Book'); 

INSERT INTO users (name,email,password,enabled)
  values ('user1','user1@alguno.com',
    '$2y$04$Wa8i//c3zj7GFf3s.cGLluzIHd.6b..OGmPjYk1Jm7ABkov3Pqzai',
    1);
INSERT INTO users (name,email,password,enabled)
  values ('admin','admin@alguno.com',
    '$2y$04$dPiNvrHA/qzT0um0mIJxJeDK4oolZtXu18PAgQtksFDfeIVdrTLX6',
    1);
 
INSERT INTO authorities (email, authority)
  values ('user1@alguno.com', 'ROLE_USER');
INSERT INTO authorities (email, authority)
  values ('admin@alguno.com', 'ROLE_ADMIN');
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 