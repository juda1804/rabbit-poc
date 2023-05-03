## Proyecto Spring Boot con RabbitMQ

Este proyecto de Spring Boot utiliza RabbitMQ como sistema de mensajería para comunicar diferentes componentes de la aplicación.

Para ejecutar este proyecto, se debe tener un servidor de RabbitMQ configurado y en funcionamiento. En este README se explicará cómo crear la topología básica de RabbitMQ con una cola y un exchange.

### Creación de un virtual host

Se recomienda crear un virtual host específico para el proyecto en RabbitMQ. Para ello, se pueden seguir los siguientes pasos:

1. Acceder a la consola de administración de RabbitMQ en el navegador web a través de la dirección `http://localhost:15672` (o la dirección correspondiente si se ha configurado en otro puerto o en otra dirección IP).

2. Iniciar sesión en la consola de administración con las credenciales de administrador de RabbitMQ.

3. Ir a la pestaña "Admin" y seleccionar la opción "Add a user" para crear un nuevo usuario con permisos de administrador para el virtual host. Se recomienda utilizar un nombre de usuario y una contraseña diferentes a las de administrador para mayor seguridad.

4. Ir a la pestaña "Admin" y seleccionar la opción "Add a virtual host" para crear un nuevo virtual host con un nombre específico, por ejemplo `juda-host`. Asignar los permisos de administrador al usuario creado anteriormente.

### Creación de la topología básica de RabbitMQ

Para crear la topología básica de RabbitMQ con una cola y un exchange, se pueden seguir los siguientes pasos:

1. Acceder a la consola de administración de RabbitMQ en el navegador web a través de la dirección `http://localhost:15672` (o la dirección correspondiente si se ha configurado en otro puerto o en otra dirección IP).

2. Iniciar sesión en la consola de administración con las credenciales de administrador de RabbitMQ.

3. Ir a la pestaña "Admin" y seleccionar la opción "Add a queue" para crear una nueva cola con un nombre específico, por ejemplo `juda-queue`. Se pueden dejar los demás valores por defecto.

4. Ir a la pestaña "Admin" y seleccionar la opción "Add an exchange" para crear un nuevo exchange con un nombre específico, por ejemplo `juda-exchange`. Se pueden dejar los demás valores por defecto, pero se debe seleccionar el tipo "Direct".

5. Ir a la pestaña "Admin" y seleccionar la opción "Bindings" para crear un nuevo binding entre la cola y el exchange. Seleccionar el exchange `juda-exchange`, la cola `juda-queue` y como routing key el valor `juda-routing-key`.

### Configuración del proyecto

En el proyecto de Spring Boot, se deben agregar las siguientes dependencias en el archivo `pom.xml` para poder utilizar RabbitMQ:

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
<dependency>
    <groupId>com.rabbitmq</groupId>
    <artifactId>amqp-client</artifactId>
    <version>${amqp-client.version}</version>
</dependency>
```


Luego, se deben agregar las siguientes propiedades de configuración en el archivo application.properties para conectar el proyecto con RabbitMQ:

```
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
spring.rabbitmq.virtual-host=juda-host
```