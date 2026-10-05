# SpeedFast

Sistema de gestión de pedidos, repartidores y entregas desarrollado en Java para la actividad de Desarrollo Orientado a Objetos II.

## Descripción

SpeedFast permite gestionar distintos tipos de pedidos y asignar repartidores según las características de cada entrega. El sistema contempla tres tipos de pedidos:

- Pedido de comida
- Pedido de encomienda
- Pedido express

Cada tipo de pedido posee su propia lógica para calcular el tiempo estimado de entrega y para gestionar su despacho. Además, el sistema persiste toda la información (repartidores, pedidos y entregas) en una base de datos MySQL, y cuenta con una interfaz gráfica desarrollada en Swing para gestionar los datos de forma visual.

## Características

- Herencia mediante una clase abstracta `Pedido`.
- Polimorfismo mediante sobrescritura de métodos.
- Interfaces para definir comportamientos específicos.
- Diferentes tipos de pedidos: comida, encomienda y express.
- Asignación de pedidos a repartidores.
- Cálculo del tiempo estimado de entrega.
- Despacho y cancelación de pedidos.
- Seguimiento del estado de los pedidos.
- Gestión de una zona de carga.
- Procesamiento concurrente de los repartidores mediante `ExecutorService`.
- Registro e impresión de información relacionada con los pedidos.
- Persistencia de datos en MySQL mediante JDBC.
- CRUD completo (crear, leer, actualizar, eliminar) para repartidores, pedidos y entregas.
- Interfaz gráfica de escritorio desarrollada con Swing.
- Validación de datos y manejo de errores en la interfaz.

## Estructura del proyecto

- `Pedido.java`: clase abstracta base para los distintos tipos de pedidos.
- `PedidoComida.java`: representa pedidos de comida.
- `PedidoEncomienda.java`: representa pedidos de encomiendas.
- `PedidoExpress.java`: representa pedidos express.
- `Despachable.java`: interfaz para los pedidos que pueden ser despachados.
- `Cancelable.java`: interfaz para los pedidos que pueden ser cancelados.
- `Rastreable.java`: interfaz relacionada con el seguimiento de pedidos.
- `EstadoPedido.java`: representa los distintos estados de un pedido.
- `Repartidor.java`: representa a los repartidores encargados de entregar los pedidos.
- `ZonaDeCarga.java`: administra los pedidos pendientes dentro de la zona de carga.
- `Entrega.java`: representa la asignación de un pedido a un repartidor, con fecha y hora.
- `ConexionDB.java`: centraliza la conexión a la base de datos MySQL.
- `PedidoDAO.java`: gestiona las operaciones CRUD de pedidos sobre la base de datos.
- `RepartidorDAO.java`: gestiona las operaciones CRUD de repartidores sobre la base de datos.
- `EntregaDAO.java`: gestiona las operaciones CRUD de entregas sobre la base de datos.
- `SpeedFastGUI.java`: interfaz gráfica Swing para gestionar repartidores, pedidos y entregas.
- `Main.java`: punto de entrada del programa, inicia la interfaz gráfica.

## Conceptos de Programación Orientada a Objetos

El proyecto aplica los siguientes conceptos:

- **Abstracción:** mediante la clase abstracta `Pedido`.
- **Encapsulamiento:** mediante atributos y métodos de acceso.
- **Herencia:** las clases específicas de pedidos heredan de `Pedido`.
- **Polimorfismo:** mediante la sobrescritura de métodos y el uso de referencias de tipo `Pedido`.
- **Interfaces:** utilizadas para definir comportamientos como despacho, cancelación y rastreo.
- **Composición y asociación:** utilizadas para relacionar pedidos, repartidores y la zona de carga.

## Concurrencia

El sistema incorpora procesamiento concurrente para gestionar a los repartidores.

Se utiliza `ExecutorService` junto con un `FixedThreadPool` para ejecutar las tareas de los repartidores de manera concurrente.

En la ejecución principal se utilizan tres repartidores, permitiendo procesar los pedidos de forma simultánea.

## Persistencia de datos

El sistema utiliza JDBC para conectarse a una base de datos MySQL (`speedfast_db`), compuesta por tres tablas relacionadas entre sí:

- `repartidores`: almacena el id y nombre de cada repartidor.
- `pedidos`: almacena dirección, tipo y estado de cada pedido.
- `entregas`: relaciona un pedido con un repartidor, registrando fecha y hora de entrega, mediante llaves foráneas hacia las tablas `pedidos` y `repartidores`.

Cada entidad cuenta con su propia clase DAO (`PedidoDAO`, `RepartidorDAO`, `EntregaDAO`), que utiliza `PreparedStatement` y `ResultSet` para ejecutar las operaciones de creación, lectura, actualización y eliminación sobre la base de datos.

## Interfaz gráfica

La interfaz gráfica fue desarrollada con Swing (`SpeedFastGUI.java`) y cuenta con tres pestañas:

- **Repartidores:** registrar, editar, eliminar y listar repartidores.
- **Pedidos:** registrar, editar, eliminar y filtrar pedidos por estado y tipo.
- **Entregas:** asignar un pedido a un repartidor con fecha y hora, y gestionar las entregas registradas.

## Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que esté configurado un JDK compatible.
3. Tener un servidor MySQL en ejecución y crear la base de datos `speedfast_db` con sus tablas (`repartidores`, `pedidos`, `entregas`).
4. Configurar el usuario y contraseña de conexión en `ConexionDB.java`.
5. Ejecutar la clase `Main`.
6. Utilizar la interfaz gráfica para registrar y gestionar repartidores, pedidos y entregas.

## Historial de avances

### Semana 5

En esta semana se incorporó el procesamiento concurrente de los pedidos mediante `ExecutorService` y un grupo fijo de tres repartidores.

Además, se integró la gestión de los pedidos mediante `ZonaDeCarga`, permitiendo agregar y procesar distintos tipos de pedidos.

### Semana 8

En esta semana se incorporó la persistencia de datos mediante JDBC y MySQL, junto con una interfaz gráfica desarrollada en Swing.

- Se implementaron las clases DAO (`PedidoDAO`, `RepartidorDAO`, `EntregaDAO`) para gestionar las operaciones CRUD sobre la base de datos.
- Se diseñó el modelo relacional de la base de datos `speedfast_db`, con las tablas `repartidores`, `pedidos` y `entregas` vinculadas mediante llaves foráneas.
- Se desarrolló `SpeedFastGUI.java`, una interfaz gráfica con pestañas para gestionar repartidores, pedidos y entregas de forma visual.
- Se agregaron validaciones y manejo de errores en la interfaz gráfica.

## Autor

Esteban Duoc
