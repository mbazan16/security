/*CREATE TABLE users (
  username VARCHAR(50) NOT NULL,
  password VARCHAR(100) NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (username)
);
   
CREATE TABLE authorities (
  username VARCHAR(50) NOT NULL,
  authority VARCHAR(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES users(username)
);
 
CREATE UNIQUE INDEX ix_auth_username
  on authorities (username,authority);*/
  
 CREATE TABLE Editorial (
  id NUMBER NOT NULL,
  name VARCHAR(50) NOT NULL,
  telefono VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Libro (
  id NUMBER NOT NULL,
  editorial_id NUMBER NOT NULL,
  autor VARCHAR(50) NOT NULL,
  titulo VARCHAR(100) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (editorial_id) REFERENCES Editorial(id)
);
