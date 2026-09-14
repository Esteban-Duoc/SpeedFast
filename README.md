# SpeedFast

Sistema de gestión de entregas desarrollado en Java para la actividad de Desarrollo Orientado a Objetos II.

## Descripción

SpeedFast permite gestionar distintos tipos de pedidos y asignar repartidores según las características de cada entrega.

El sistema contempla tres tipos de pedidos:

- Pedido de comida
- Pedido de encomienda
- Pedido express

Cada tipo de pedido posee su propia lógica para calcular el tiempo estimado de entrega y para asignar repartidores.

## Características

- Herencia mediante una clase abstracta `Pedido`.
- Polimorfismo mediante sobrecarga y sobrescritura de métodos.
- Interfaces para definir comportamientos específicos.
- Asignación manual y automática de repartidores.
- Cálculo de tiempo estimado de entrega.
- Despacho y cancelación de pedidos.
- Registro e impresión del historial de pedidos.

## Estructura del proyecto

- `Pedido.java`: clase abstracta base para los pedidos.
- `PedidoComida.java`: representa pedidos de comida.
- `PedidoEncomienda.java`: representa pedidos de encomiendas.
- `PedidoExpress.java`: representa pedidos express.
- `Despachable.java`: interfaz para pedidos que pueden ser despachados.
- `Cancelable.java`: interfaz para pedidos que pueden ser cancelados.
- `Rastreable.java`: interfaz relacionada con el seguimiento de pedidos.
- `ControladorDeEnvios.java`: controla el despacho, cancelación e historial de pedidos.
- `Main.java`: ejecuta y demuestra el funcionamiento del sistema.

## Conceptos de Programación Orientada a Objetos

El proyecto aplica los siguientes conceptos:

- **Abstracción:** mediante la clase abstracta `Pedido`.
- **Encapsulamiento:** mediante atributos protegidos y métodos de acceso.
- **Herencia:** las clases específicas de pedidos heredan de `Pedido`.
- **Polimorfismo:** mediante sobrecarga y sobrescritura de métodos.
- **Interfaces:** utilizadas para definir comportamientos como despacho, cancelación y rastreo.

## Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que esté configurado un JDK compatible.
3. Ejecutar la clase `Main`.
4. Revisar en la consola los resultados de las operaciones realizadas.

## Ejemplo

El programa crea distintos pedidos, asigna repartidores, calcula tiempos estimados, realiza despachos y permite cancelar pedidos express.

## Autor

Esteban Duoc
