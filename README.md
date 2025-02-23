# API RESTful de Usuarios y Post

Este proyecto es una API RESTful  que permite manejar usuarios y posts .La API soporta las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para ambos recursos (usuarios y posts),
asegurando que cada usuario solo pueda acceder a sus propios posts.

## Tecnologías utilizadas

- **Java 17**: Lenguaje de programación utilizado para desarrollar la API.
- **Spring Boot 3.4.3**: Framework de desarrollo para crear aplicaciones Java basadas en microservicios.
- **Swagger/OpenAPI**: Para la documentación y prueba interactiva de los endpoints de la API.
- **JWT (JSON Web Tokens)**: Para la autenticación y autorización de los usuarios.
- **MySQL**: Base de datos relacional utilizada para almacenar los datos de la aplicación.

## Requisitos previos

- **Java 17** instalado en tu máquina. Puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).
- **MySQL** instalado y configurado. Asegúrate de tener acceso a una base de datos MySQL para probar la aplicación.
- **Maven** para gestionar las dependencias y construir el proyecto. Si no lo tienes instalado, puedes seguir las instrucciones de instalación [aquí para Maven](https://maven.apache.org/install.html) 
- **Postman** para realizar pruebas sobre la API.

## Configuración de la Base de Datos

1. **Crear una base de datos** en MySQL:
   - Accede a tu servidor de MySQL y ejecuta el siguiente comando para crear una base de datos:
     ```sql
     CREATE DATABASE usuarios;
     USE usuarios;
     ```
   
2. **Configura las credenciales de la base de datos** en el archivo `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:jdbc:mysql://localhost:3306/usuarios?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
   spring.datasource.username=root
   spring.datasource.password=root
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   spring.jpa.show-sql=true


# Pasos para levantar la aplicación

**1 - Clonar el repositorio**:

Si aún no tienes el proyecto en tu máquina, clónalo con Git:

git clone https://github.com/cesararamayo3068/ChallengeJavaSpringBootTsg
cd user

**2 - **Instalar las dependencias**:**:
Si estás usando **Maven**, ejecuta:
mvn clean install

**3 - **Levantar la aplicación**:**
Ejecuta el siguiente comando para iniciar la aplicación:
mvn spring-boot:run

**4 - **Acceder a la API**:**
La API debería estar corriendo en `http://localhost:8080`

## Autenticación con JWT

Para acceder a los endpoints protegidos, primero debes obtener un **token JWT**.

**1 - **Obtener un token JWT**:**

Realiza un **POST** a `http://localhost:8080/auth/login` con el siguiente cuerpo en JSON:

{ 
"username": "tu_usuario", 
"password": "tu_contraseña"
}

Respuesta:
{
"token": "JWT_TOKEN_AQUI"
}



# API de Usuarios


Esta API permite gestionar usuarios dentro del sistema.

### 🔧 Requisitos previos

-   Tener el servidor en ejecución (`localhost:8080` por defecto).
-   Postman instalado (opcional, pero recomendado).
-   **Las colecciones de Postman** se encuentran en:
    
    src/main/resources/static/Postman-colecciones

    Puedes importarlas en Postman para facilitar las pruebas.

**Autenticación**:  
Todos los endpoints requieren un **token de autenticación** en los headers de la petición.  
Debes incluirlo en cada solicitud como:

`Authorization: Bearer <TOKEN_AQUI>` 

_(Reemplaza `<TOKEN_AQUI>` con el token obtenido al iniciar sesión)._

##
### 📌 Endpoints y cómo probarlos

#### 🔹 1. Registrar un Usuario

-   **Método**: `POST`
-   **URL**:

         http://localhost:8080/auth/register


**Headers**:
`Authorization: Bearer <TOKEN_AQUI>
Content-Type: application/json`

##
-   **Body (JSON)**:
    
    `{
      "username": "cesar",
      "password": "contraseñaSegura"
    }` 
    
-   **Respuesta**:
    
    `"Usuario registrado"` 
    
-   **Notas**:
    -   La contraseña se encripta antes de guardarse en la base de datos.
    -   Si ya existe un usuario con el mismo nombre, la API podría devolver un error.

----------

#### 🔹 2. Obtener Todos los Usuarios

-   **Método**: `GET`
-   **URL**:
    
    `http://localhost:8080/users` 

**Headers**:

`Authorization: Bearer <TOKEN_AQUI>`
    
-   **Respuesta esperada (`200 OK`)**:
    
    `[
      {
        "id": 1,
        "username": "cesar"
      },
      {
        "id": 2,
        "username": "juan"
      }
    ]` 
    

----------

#### 🔹 3. Obtener un Usuario por ID

-   **Método**: `GET`
-   **URL**:
    
    `http://localhost:8080/users/{userId}` 

**Headers**:

`Authorization: Bearer <TOKEN_AQUI>`
    
    (Reemplazar `{userId}` con el ID del usuario deseado)
-   **Respuesta esperada (`200 OK`)**:
    `{
      "id": 1,
      "username": "cesar"
    }` 
    

----------

#### 🔹 4. Actualizar un Usuario

-   **Método**: `PUT`
-   **URL**:
    
    `http://localhost:8080/users/{userId}` 
    
  **Headers**:

`Authorization: Bearer <TOKEN_AQUI>
 Content-Type: application/json`

    (Reemplazar `{userId}` con el ID del usuario a actualizar)
-   **Body (JSON)**:
    
    `{
      "username": "cesar_actualizado",
      "password": "nuevaContraseña"
    }` 
    
-   **Respuesta esperada (`200 OK`)**:
    
    `{
      "id": 1,
      "username": "cesar_actualizado"
    }` 
    

----------

#### 🔹 5. Eliminar un Usuario

-   **Método**: `DELETE`
-   **URL**:
 
    
    `http://localhost:8080/users/{userId}` 

 **Headers**:
`Authorization: Bearer <TOKEN_AQUI>`
    
    (Reemplazar `{userId}` con el ID del usuario a eliminar)
-  
    _(No devuelve contenido si la eliminación fue exitosa)_

----------

### 🚀 Importar colección de Postman

Para facilitar las pruebas, puedes importar la colección de Postman ubicada en:

`src/main/resources/static/Postman-colecciones` 

1.  Abre **Postman**.
2.  Ve a **Importar** y selecciona el archivo `.json` dentro de `Postman-colecciones`.
3.  Ejecuta las peticiones desde la colección importada.

----------

### 📢 Notas

-   Si la API tiene seguridad habilitada (`Spring Security`), es posible que necesites autenticarte antes de acceder a estos endpoints.
-   Si recibes un error `401 Unauthorized`, verifica que estés enviando el token de autorización en los headers.
-   Asegúrate de que los `userId` usados en las pruebas existen en la base de datos.

## 📌 API de Posts

Esta API permite gestionar publicaciones (**Posts**) asociadas a usuarios.

### 🔧 Requisitos previos

-   Tener el servidor en ejecución (`localhost:8080` por defecto).
    
-   Postman instalado (opcional, pero recomendado).
    
-   **Las colecciones de Postman** se encuentran en:
    
    `src/main/resources/static/Postman-colecciones` 
    
    Puedes importarlas en Postman para facilitar las pruebas.
    
-   **Autenticación**:  
    Todos los endpoints requieren un **token de autenticación** en los headers de la petición.  
    Debes incluirlo en cada solicitud como:
  
    
    `Authorization: Bearer <TOKEN_AQUI>` 
    
    _(Reemplaza `<TOKEN_AQUI>` con el token obtenido al iniciar sesión)._
    

----------

### 📌 Endpoints y cómo probarlos

#### 🔹 1. Crear un Post

-   **Método**: `POST`
-   **URL**:
    
    `http://localhost:8080/posts/{userId}` 
    
    _(Reemplazar `{userId}` con el ID de un usuario válido)_
-   **Headers**:
    
    `Authorization: Bearer <TOKEN_AQUI>
    Content-Type: application/json` 
    
-   **Body (JSON)**:
    
    `{
      "title": "Mi primer post",
      "content": "Este es un post de prueba."
    }` 
    
-   **Respuesta esperada (`201 Created`)**:
    
    `{
      "id": 1,
      "title": "Mi primer post",
      "content": "Este es un post de prueba.",
      "user": {
        "id": 6,
        "username": "cesar"
      }
    }` 
    

----------

#### 🔹 2. Obtener Posts de un Usuario

-   **Método**: `GET`
-   **URL**:
    
    `http://localhost:8080/posts/user/{userId}` 
    
    _(Reemplazar `{userId}` con el ID de un usuario válido)_
-   **Headers**:
    
    `Authorization: Bearer <TOKEN_AQUI>` 
    
-   **Respuesta esperada (`200 OK`)**:
    
    `[
      {
        "id": 1,
        "title": "Mi primer post",
        "content": "Este es un post de prueba."
      },
      {
        "id": 2,
        "title": "Segundo post",
        "content": "Otro post de prueba."
      }
    ]` 
    

----------

#### 🔹 3. Actualizar un Post

-   **Método**: `PUT`
-   **URL**:
    
    `http://localhost:8080/posts/{postId}` 
    
    _(Reemplazar `{postId}` con el ID del post a actualizar)_
-   **Headers**:
    
    `Authorization: Bearer <TOKEN_AQUI>
    Content-Type: application/json` 
    
-   **Body (JSON)**:
    
    `{
      "title": "Título actualizado",
      "content": "Contenido actualizado del post."
    }` 
    
-   **Respuesta esperada (`200 OK`)**:
    
    `{
      "id": 1,
      "title": "Título actualizado",
      "content": "Contenido actualizado del post."
    }` 
    

----------

#### 🔹 4. Eliminar un Post

-   **Método**: `DELETE`
-   **URL**:
    
    `http://localhost:8080/posts/{postId}` 
    
    _(Reemplazar `{postId}` con el ID del post a eliminar)_
-   **Headers**:
    
    `Authorization: Bearer <TOKEN_AQUI>` 
    
-   **Respuesta esperada (`204 No Content`)**:  
    _(No devuelve contenido si la eliminación fue exitosa)_

----------

### 🚀 Importar colección de Postman

Para facilitar las pruebas, puedes importar la colección de Postman ubicada en:

`src/main/resources/static/Postman-colecciones` 

1.  Abre **Postman**.
2.  Ve a **Importar** y selecciona el archivo `.json` dentro de `Postman-colecciones`.
3.  Ejecuta las peticiones desde la colección importada.

----------

### 📢 Notas

-   **Autenticación requerida**: Todos los endpoints requieren un **token de autenticación** en el header `Authorization`.
-   Si recibes un error `401 Unauthorized`, verifica que estés enviando el token correctamente.
-   Asegúrate de que los `userId` y `postId` usados en las pruebas existen en la base de datos.
