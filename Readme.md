Proyecto JDBC con Java y MySQL - OpenWebinars
Descripción general
Este proyecto forma parte de la práctica de la academia OpenWebinars, en el cual se desarrolla un CRUD completo (Crear, Leer, Actualizar, Eliminar) sobre dos entidades relacionadas: Producto y Categoría (relación 1:N), utilizando Java con JDBC y MySQL como sistema gestor de bases de datos.

Requisitos implementados
🔧 Entorno con Docker (2 puntos)
Para facilitar la gestión y despliegue del entorno, se ha utilizado Docker con un archivo docker-compose.yml que levanta dos servicios:

MySQL: motor de base de datos

phpMyAdmin: herramienta de administración vía web

Una vez ejecutado el docker-compose, se puede acceder a phpMyAdmin desde http://localhost:8081, donde se visualizan y gestionan las tablas creadas automáticamente.

💡 Patrón DAO (2 puntos)
La aplicación sigue el patrón de diseño DAO (Data Access Object), lo que permite una separación clara entre la lógica de acceso a datos y la lógica de negocio, haciendo el código más organizado, escalable y mantenible.

🔄 Pool de Conexiones (2 puntos)
Para optimizar el rendimiento de la aplicación y evitar la sobrecarga de conexiones, se ha configurado un pool de conexiones, mejorando así la eficiencia en la gestión de recursos de base de datos.

📋 Menú interactivo (2 puntos)
Se incluye un menú en consola que permite al usuario acceder de forma sencilla a todas las funcionalidades del CRUD:

Crear productos o categorías

Consultar registros

Actualizar información

Eliminar entradas

✅ Examen (2 puntos)
El proyecto ha sido complementado con el examen oficial de OpenWebinars, obteniendo un resultado de 18/20 respuestas correctas.

Recursos adicionales
Curso: Java y MySQL JDBC: Elaboración de un CRUD – OpenWebinars

Apuntes de apoyo extraídos del campus virtual (Moodle)