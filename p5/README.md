# P5
Aplicación web que usa Spring JPA para persistir los datos de un API REST de gestión de usuarios.
El API permite el registro de nuevos usuarios y su identificación mediante email y password.
Una vez identificados, se emplea una cookie de sesión para autenticar las peticiones que permiten 
a los usuarios leer, modificar y borrar sus datos. También existe un endpoint para cerrar la sesión.  

## Endpoints

// TODO#1: rellena la tabla siguiente analizando el código del proyecto

| Método | Ruta               | Descripción                             | Respuestas                                                                                                |
|------|--------------------|-----------------------------------------|-----------------------------------------------------------------------------------------------------------|
| POST | /api/register      | Permite registrar un nuevo usuario      | `201 Created` si el registro es correcto <br> `400 Bad request` si hay error de validacion                |
| POST | /api/inicio_sesion | Permite iniciar sesión                  | `200 OK` y se establece la cookie de inicio de sesión<br> `401 Unathorized` si las credenciales son incorrectas |
| GET  | /api/usuario       | Obtiene los datos del usuario           | `200 OK` con los datos del usuario<br> `401 Unathorized` si no hay sesion activa                          |
| PUT  | /api/usuario       | Permite modificar los datos del usuario | `200 OK` si se actualiza correctamente<br> `401 Unathorized` si no hay sesion activa                      |
| DELETE | /api/usuario       | Elimina la cuenta del usuario           | `204 No Content` si el borrado es correcto<br> `401 Unathorized` si no hay sesion activa                  |
| POST | /api/cerrar_sesion | Cerrar sesión (eliminar la cookie de sesión)     | `200 OK` o `204 No Content`                                                                               |                                                                                         


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
