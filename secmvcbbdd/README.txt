SecurityMethodConfig:
 La propiedad prePostEnabled permite anotaciones Spring Security pre/post annotations
 La propiedad securedEnabled permite habilitar @Secured anotacion
 La propiedad jsr250Enabled permite el uso de anotaciones @RoleAllowed

application.properties:
 Spring Boot crea automáticamente el esquema de un DataSource incorporado.
 Inicializar siempre el DataSource independientemente de su type
 spring.datasource.initialization-mode=always

 En una aplicación basada en JPA, puede elegir dejar que Hibernate cree el esquema o usar schema.sql , pero no puede hacer ambas cosas.
 Deshabilitamos ya que usamos schema.sql 
 spring.jpa.hibernate.ddl-auto=none
 