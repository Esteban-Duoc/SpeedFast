# SpeedFast

Sistema de gestión de entregas desarrollado en Java para la actividad de Desarrollo Orientado a Objetos II.

## Descripción

SpeedFast permite gestionar distintos tipos de pedidos y asignar repartidores según las características de cada entrega.

El sistema contempla tres tipos de pedidos:

- Pedido de comida
- Pedido de encomienda
- Pedido express

Cada tipo de pedido posee su propia lógica para calcular el tiempo estimado de entrega y para gestionar su despacho.

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
- `ControladorDeEnvios.java`: controla la gestión de los envíos.
- `Main.java`: ejecuta y demuestra el funcionamiento del sistema.

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

## Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que esté configurado un JDK compatible.
3. Ejecutar la clase `Main`.
4. Revisar en la consola los resultados de las operaciones realizadas.

## Ejemplo

El programa crea distintos tipos de pedidos y los agrega a una zona de carga.

Posteriormente, se crean tres repartidores que procesan los pedidos de manera concurrente. El programa muestra en consola la cantidad de pedidos presentes en la zona de carga y, una vez finalizado el procesamiento, informa que los pedidos han sido entregados correctamente.

## Semana 5

En esta semana se incorporó el procesamiento concurrente de los pedidos mediante `ExecutorService` y un grupo fijo de tres repartidores.

Además, se integró la gestión de los pedidos mediante `ZonaDeCarga`, permitiendo agregar y procesar distintos tipos de pedidos.

## Autor

Esteban Duoc
