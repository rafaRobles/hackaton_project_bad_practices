# Objetivo

Realizar una limpieza general del código siguiendo los principios de Clean Code de Robert C. Martin.

El objetivo es mejorar:

- legibilidad;
- mantenibilidad;
- claridad;
- cohesión;
- bajo acoplamiento;
- simplicidad del diseño interno.

Todos los cambios deben realizarse mediante refactorización segura, sin modificar el comportamiento externo del software.

---

# Restricción principal: Refactorización según Martin Fowler

Todo cambio debe cumplir estrictamente con la definición de refactorización de Martin Fowler:

> "Un cambio realizado en la estructura interna del software para mejorar su diseño sin alterar su comportamiento observable."

Por lo tanto, todas las modificaciones deben cumplir:

- Mejorar únicamente la estructura interna del código.
- Mantener exactamente el mismo comportamiento observable.
- Mantener los mismos resultados para las mismas entradas.
- Mantener la compatibilidad con consumidores existentes.

Está estrictamente prohibido:

- modificar el comportamiento externo del software;
- agregar funcionalidades;
- eliminar funcionalidades;
- modificar reglas de negocio;
- modificar lógica funcional;
- cambiar algoritmos para obtener nuevos resultados;
- cambiar flujos funcionales;
- cambiar contratos existentes.

Si una mejora requiere modificar comportamiento, NO debe realizarse.

---

# Principio de preservación del comportamiento

Antes de realizar cualquier cambio evaluar:

1. ¿El cambio únicamente modifica estructura interna?
2. ¿El usuario externo observará exactamente el mismo resultado?
3. ¿Los contratos existentes permanecen iguales?
4. ¿Las pruebas existentes continúan validando lo mismo?

Si existe duda sobre la preservación del comportamiento:

NO realizar el cambio.

---

# Compatibilidad y contratos públicos

No modificar contratos públicos.

Esto incluye:

## APIs REST

No modificar:

- endpoints;
- rutas;
- métodos HTTP;
- parámetros;
- headers;
- códigos HTTP;
- estructuras JSON;
- nombres de campos;
- formatos de respuesta.

## Librerías y módulos expuestos

No modificar:

- interfaces públicas;
- clases públicas;
- métodos públicos;
- firmas utilizadas por otros módulos;
- contratos de librerías.

## Comunicación entre sistemas

No modificar:

- eventos;
- mensajes;
- esquemas;
- formatos intercambiados;
- contratos de integración.

---

# Restricciones sobre arquitectura

La limpieza de código NO debe convertirse en una modificación arquitectónica.

No realizar:

- cambios de arquitectura;
- migraciones tecnológicas;
- cambios de framework;
- incorporación de nuevas capas;
- cambios de patrones arquitectónicos;
- rediseños completos.

Mantener la arquitectura existente.

---

# Flujo obligatorio antes de modificar código

Antes de aplicar cambios:

1. Analizar completamente el contexto.
2. Identificar problemas reales de calidad.
3. Identificar oportunidades de mejora.
4. Determinar qué principio de Clean Code aplica.
5. Identificar la refactorización necesaria.
6. Evaluar riesgos.
7. Explicar los cambios antes de realizarlos.

No realizar modificaciones sin una justificación clara.

---

# Nombres descriptivos

Usar nombres descriptivos que revelen claramente la intención.

Aplicar a:

- clases;
- interfaces;
- métodos;
- funciones;
- variables;
- constantes;
- parámetros.

Reglas:

- Seguir convenciones Java.
- Seguir convenciones Spring Boot.
- Usar nombres completos.
- Evitar abreviaturas ambiguas.
- Evitar nombres genéricos.
- Mantener terminología consistente en todo el proyecto.

Ejemplos:

Evitar:

```java
usr
proc()
calc()
data
info
obj
```

Preferir:

```java
user
processPayment()
calculateTransactionAmount()
customerInformation
paymentRequest
```

---

# Métodos y funciones

Los métodos deben:

- ser pequeños;
- realizar una única responsabilidad;
- tener un único nivel de abstracción;
- expresar claramente su intención.

Aplicar:

- Single Responsibility Principle.
- Código fácil de leer.

Evitar:

- métodos demasiado largos;
- múltiples responsabilidades;
- lógica mezclada;
- demasiados niveles de anidamiento.

---

# Parámetros

Minimizar parámetros.

Evitar:

- demasiados parámetros;
- parámetros innecesarios;
- parámetros booleanos que cambian comportamiento.

Preferir:

- objetos que agrupen información relacionada;
- encapsulación.

---

# Retorno de valores

Preferir retornar valores.

Evitar:

- modificar parámetros recibidos;
- efectos secundarios ocultos.

No retornar `null` cuando exista una alternativa segura.

Preferir:

- Optional;
- excepciones;
- objetos de resultado existentes.

---

# Eliminación de duplicación

Aplicar principio DRY:

> Don't Repeat Yourself

Eliminar código duplicado cuando:

- exista duplicación real;
- la extracción mejore claridad;
- no modifique comportamiento.

No crear abstracciones innecesarias solamente para eliminar pocas líneas.

---

# Código autoexplicativo

El código debe expresar su intención mediante:

- nombres adecuados;
- métodos pequeños;
- clases cohesivas;
- estructuras simples.

No depender de comentarios para explicar código mal diseñado.

---

# Comentarios

Usar comentarios únicamente cuando aporten información que el código no pueda expresar.

Eliminar:

- comentarios obsoletos;
- comentarios redundantes;
- comentarios que describen literalmente el código.

Preferir mejorar el código antes que agregar comentarios.

---

# Organización de código

Agrupar métodos relacionados.

Considerar:

- atributos utilizados;
- dependencias compartidas;
- colaboración con clases externas;
- responsabilidad funcional.

No mover código únicamente por preferencia personal.

Toda reorganización debe mejorar claramente la cohesión.

---

# Clases

Cada clase debe tener una única responsabilidad.

Aplicar:

- Single Responsibility Principle.
- Alta cohesión.
- Bajo acoplamiento.

Las clases deben:

- ser pequeñas;
- tener propósito claro;
- ocultar detalles internos;
- evitar responsabilidades mezcladas.

---

# Encapsulamiento

Favorecer encapsulamiento.

Aplicar:

- ocultar detalles de implementación;
- evitar exponer datos internos;
- proteger estado interno;
- reducir dependencias innecesarias.

---

# Manejo de errores

Cuando sea apropiado:

- utilizar excepciones en lugar de códigos de error;
- proporcionar mensajes claros;
- utilizar excepciones existentes del proyecto;
- mantener consistencia con Spring Boot.

Evitar:

- valores mágicos;
- retornos ambiguos;
- múltiples formas de representar errores.

---

# Uso de librerías existentes

Antes de crear una solución personalizada:

Verificar si ya existe una solución en:

- Java Standard Library;
- Spring Framework;
- Spring Boot;
- dependencias actuales del proyecto.

Preferir utilizar:

- Collections Framework;
- java.time;
- Optional;
- Streams;
- Bean Validation;
- Spring Utilities;
- componentes existentes.

No rehacer funcionalidades que ya existen.

---

# Dependencias nuevas

No agregar nuevas dependencias.

Solo agregar una dependencia si:

- existe una necesidad real;
- no existe solución disponible;
- está permitido por la arquitectura del proyecto.

---

# Patrones de diseño

Aplicar patrones de diseño únicamente cuando sean totalmente necesarios para resolver un problema real de diseño.

No aplicar patrones por:

- moda;
- preferencia personal;
- complejidad aparente;
- intención de "mejorar arquitectura".

Antes de aplicar un patrón justificar:

1. Problema real existente.
2. Limitación del diseño actual.
3. Razón por la cual el patrón resuelve el problema.
4. Complejidad introducida.
5. Beneficio obtenido.

Está prohibido:

- crear abstracciones innecesarias;
- crear jerarquías artificiales;
- introducir patrones donde código simple sea suficiente.

Preferencia:

1. Código simple.
2. Código claro.
3. Refactorización directa.
4. Patrón únicamente si es necesario.

---

# Composición sobre herencia

Preferir composición sobre herencia cuando sea conveniente.

No crear jerarquías de clases innecesarias.

---

# Pruebas End-to-End

Las pruebas End-to-End representan el comportamiento externo.

Deben funcionar exactamente igual antes y después de la refactorización.

Está prohibido modificar pruebas End-to-End.

No modificar:

- escenarios;
- datos esperados;
- validaciones;
- contratos;
- respuestas esperadas.

Si una prueba End-to-End falla:

Corregir el código.

Nunca modificar una prueba E2E para aceptar un cambio funcional.

---

# Pruebas unitarias e integración

Las pruebas unitarias e integración pueden modificarse únicamente para adaptarse a cambios estructurales derivados de una refactorización.

Cambios permitidos:

- actualizar llamadas a métodos modificados;
- actualizar firmas de métodos;
- actualizar constructores;
- actualizar mocks afectados;
- actualizar referencias internas;
- actualizar imports.

Las pruebas deben conservar:

- mismo propósito;
- mismos escenarios;
- mismas validaciones;
- mismos datos;
- mismos resultados esperados.

Está prohibido:

- eliminar pruebas;
- reducir cobertura;
- modificar reglas de validación;
- cambiar assertions funcionales;
- agregar nuevos escenarios.

---

# Validación obligatoria después de cambios

Después de modificar código:

1. Verificar compilación.
2. Ejecutar pruebas unitarias.
3. Ejecutar pruebas de integración.
4. Ejecutar pruebas End-to-End.
5. Verificar que los contratos permanecen iguales.
6. Generar resumen de cambios.

El resumen debe incluir:

- archivos modificados;
- cambios realizados;
- principio aplicado;
- refactorización aplicada;
- pruebas ejecutadas.

---

# Restricciones finales

No realizar cambios que impliquen:

- cambios funcionales;
- cambios arquitectónicos;
- nuevas funcionalidades;
- nuevos patrones innecesarios;
- nuevas dependencias innecesarias;
- cambios en APIs públicas;
- cambios en contratos públicos;
- cambios en protocolos de comunicación;
- modificaciones de reglas de negocio.

La prioridad absoluta es:

Mejorar la estructura interna manteniendo exactamente la misma funcionalidad externa.