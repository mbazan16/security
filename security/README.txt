Documentación Swagger:localhost:8083/swagger-ui.html

BBDD H2:localhost:8083/h2-console

H2 Tutorial:http://www.h2database.com/html/tutorial.html

application.properties:
 Spring Boot crea automáticamente el esquema de un DataSource incorporado.
 Inicializar siempre el DataSource independientemente de su tipo
 spring.datasource.initialization-mode=always

 En una aplicación basada en JPA, puede elegir dejar que Hibernate cree el esquema o usar schema.sql , pero no puede hacer ambas cosas.
 Deshabilitamos ya que usamos schema.sql 
 spring.jpa.hibernate.ddl-auto=none