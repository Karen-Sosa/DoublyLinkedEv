# Lista Doblemente Enlazada

Aplicación en Java para gestionar registros de personas usando una lista doblemente enlazada implementada desde cero.
El proyecto permite dividir los registros según un criterio de edad, eliminar registros que cumplan dicho criterio y ordenar la información utilizando Selection Sort, todo trabajando directamente sobre nodos enlazados sin depender de las colecciones de Java.

## Características

* División de una lista en dos sublistas según la edad de cada persona (`retirementAge`).
* Eliminación de registros cuya edad sea inferior a `retirementAge`, reutilizando la lógica de división.
* Manejo de múltiples listas mediante una lista doblemente enlazada que contiene otras listas doblemente enlazadas.
* Ordenamiento de registros mediante el algoritmo Selection Sort.
* Implementación propia de la lista doblemente enlazada, sin uso de colecciones de Java.

## Criterio de división y eliminación (`retirementAge`)

* Para las pruebas se utilizó **25** como valor de `retirementAge`.
* Es un valor definido por mí, sin representar una edad real de jubilación.
* Se usa como criterio para comparar la edad de cada persona.
* Por el momento, el valor está definido directamente en el código.

## Estructura utilizada

* Implementación propia de una lista doblemente enlazada, con nodos y enlaces `next` y `previous`.
* Las distintas listas se organizan dentro de una lista doblemente enlazada principal:

```text
DoublyLinked
     │
     ├── Lista 0
     ├── Lista 1
     ├── Lista 2
     └── ...
```

* Esto permite que cada lista se mantenga independiente y sea identificada mediante su posición.

## Funcionamiento

### División de la lista

1. Se selecciona la lista a procesar.
2. Se recorren sus nodos con un `while`.
3. Se calcula la edad de cada persona.
4. Se compara la edad con `retirementAge`:
    * Edad mayor a 25 → **Lista 1**.
    * Edad menor o igual a 25 → **Lista 2**.
5. Se muestran las tres listas (original, Lista 1 y Lista 2).

Las listas resultantes de esta operación no se almacenan permanentemente; su objetivo es mostrarle al usuario el resultado de la división.

### Eliminación de registros

1. Se selecciona la lista a procesar.
2. Se recorren sus nodos con un `while`.
3. Se calcula la edad de cada persona.
4. Se compara la edad con `retirementAge` para identificar los registros a eliminar.
5. Se guarda la lista resultante con dichos registros.
6. Se elimina esa lista de la estructura principal mediante `removeAt()`, usando su posición.

La lista original se mantiene intacta, de modo que el usuario puede realizar varias operaciones sobre los mismos datos.

### Ordenamiento

* Se utiliza **Selection Sort** para ordenar los registros.
* Se eligió por ser un método sencillo de implementar sobre listas doblemente enlazadas, facilitando el manejo de `next` y `previous`, aunque no sea el método más eficiente.

## Decisiones de diseño

* Se desarrollaron la división y la eliminación en conjunto porque ambas dependen de la edad y de `retirementAge`; primero se implementó la división y luego se reutilizó su lógica para la eliminación.
* En la eliminación se optó por construir una lista resultante y borrarla con `removeAt()`, en lugar de eliminar nodo por nodo, ya que se consideró un trabajo adicional innecesario para cumplir con lo solicitado.
* Conservar la lista original permite que el usuario repita la operación sobre los mismos datos cuantas veces lo necesite.

## Notas

* No se utilizan colecciones de Java; toda la lógica de listas está implementada manualmente.
* El valor de `retirementAge` está fijado en el código y puede modificarse según se requiera.
