Este proyecto es un ejemplo de Spring Security y JWT.
Para ejecutarlo se tienen que realizar dos peticiones:
**** Petición de Autenticación

En el cuerpo de la petición, introducir en formato JSON los valores:
username:javainuse
password:password
http://localhost:8083/authenticate
Request Body raw JSON
{
    "username":"javainuse",
     "password":"password"
}
Esta llamada devolverá un token, por ejemplo:
 {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXZhaW51c2UiLCJleHAiOjE2NzE3Njk5OTQsImlhdCI6MTY3MTc1MTk5NH0.EMW3GyzkKUBATF0rvwQ-KFkQknTG5u_tQORAAsh9MkQjiisGaMM6tk0MAlgt6qv5GyBp5buOpyMS4nUSUS0CLQ"
}

**** Llamada al aplicativo (A HelloWorldController)

Se invoca a http://localhost:8083/hello
con los siguientes datos en la CABECERA de la petición

Request Headers 
Key --> Autentication
Value --> Bearer		  		eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYXZhaW51c2UiLCJleHAiOjE2NzE3Njk5OTQsImlhdCI6MTY3MTc1MTk5NH0.EMW3GyzkKUBATF0rvwQ-KFkQknTG5u_tQORAAsh9MkQjiisGaMM6tk0MAlgt6qv5GyBp5buOpyMS4nUSUS0CLQ

El par clave valor debe ser así. El valor debe incluir la palabra Bearer un espacio al menos y el token

****************** Funcionamiento********************************

La primera llamada autentica al usuario y genera el token. El flujo entre las diferentes clases se explica en la imagen flujoGeneracionJWT.jpeg

La segunda llamada valida el token y si es correcto permite acceder al aplicativo. Se explica en la imagen flujoValidacionJWT.jpeg





