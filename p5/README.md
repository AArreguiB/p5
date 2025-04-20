# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta                  | Descripción                                        | Respuestas                                                                                                      |
|--------|-----------------------|----------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| POST   | /api/users            | Permite registrar un nuevo usuario                 | `201 Created` si el registro es correcto <br> `409 Conflict` si hay error de validacion                         |
| POST   | /api/users/me/session | Permite iniciar sesión y crea una cookie de sesion | `200 OK` y se establece la cookie de inicio de sesión<br> `401 Unathorized` si las credenciales son incorrectas |
| GET    | /api/users/me         | Obtiene los datos del usuario                      | `200 OK` con los datos del usuario<br> `401 Unathorized` si no hay sesion activa                                |
| PUT    | /api/users/me         | Permite modificar los datos del usuario            | `200 OK` si se actualiza correctamente<br> `401 Unathorized` si no hay sesion activa                            |
| DELETE | /api/users/me/session | Cierra la sesion del usuario                       | `204 No Content` y elimina la cookie de sesion<br> `401 Unathorized` si no hay sesion activa                    |
| DELETE | /api/users/me         | Elimina la cuenta del usuario                      | `204 No Content` si se borra correctamente <br> `401 Unathorized` si no hay sesion valida                       |                                                                                         


## Comandos 

- Construcción: 
  ```sh
  ./mvnw clean package
  ```

- Ejecución: 
  ```sh
  ./mvnw spring-boot:run
  ```

- Tests:
  ```sh
  ./mvnw test
  ```
