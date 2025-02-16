# pruebacastores

El proyecto fue echo con Angular para el frontend y Spring Boot para el backend.

- Ide frontend: WebStorm
- Ide backend: IntelliJ IDEA Ultimate
- Version de Java: 17
- Version de Angular: 19.1.7
- Version de Spring Boot: 3.4.2
- DBMS: MySQL 8

# Pasos para correr el proyecto
- Clonar el repositorio [front-end](https://github.com/Jhonny123-123/pruebacastores) y back-end
- Ejecutar el script de la base de datos que se encuentra en la carpeta `documentos` llamado [database.sql](documentos/database.sql).
- Configurar el archivo [application.yml](src/main/resources/application.yml) del repositorio del backend con los datos de acceso a la base de datos.
- Correr el backend con el comando `mvnw spring-boot:run`.
- Correr el frontend con el comando `ng serve`.
- Abrir en el navegador `http://localhost:4200/` para ver la aplicación.

```bash
mvnw spring-boot:run
```