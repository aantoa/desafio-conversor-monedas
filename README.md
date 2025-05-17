# 💱 Conversor de Monedas - Challenge

Este proyecto es una aplicación en consola desarrollada en Java que permite realizar conversiones de monedas utilizando tasas de cambio en tiempo real, obtenidas desde la API [ExchangeRate-API](https://www.exchangerate-api.com/).

## 🚀 Funcionalidades

- Consulta de tasas de cambio actualizadas en tiempo real.
- Conversión entre monedas usando monto ingresado por el usuario.
- Interfaz interactiva por consola.
- Soporte para ingreso por **código de moneda** (`ARS`) o **número asociado** (`1`).
- Menú posterior a cada operación para elegir entre:
    - Realizar una nueva conversión
    - Ver el historial de conversiones realizadas
    - Salir de la aplicación
- **🆕 Soporte para más monedas**: Se amplió la lista de monedas disponibles para permitir a los usuarios convertir entre una mayor variedad de opciones monetarias.

## 🧪 Monedas disponibles

El sistema permite convertir entre las siguientes monedas:

| Número | Código | Nombre                   |
|--------|--------|---------------------------|
| 1      | ARS    | Peso argentino            |
| 2      | BOB    | Boliviano                 |
| 3      | COP    | Peso colombiano           |
| 4      | CLP    | Peso chileno              |
| 5      | USD    | Dólar estadounidense      |
| 6      | BRL    | Real brasileño            |
| 7      | EUR    | Euro                      |
| 8      | MXN    | Peso mexicano             |
| 9      | PEN    | Sol peruano               |
| 10     | GBP    | Libra esterlina           |
| 11     | JPY    | Yen japonés               |
| 12     | KRW    | Won surcoreano            |

## 🛠️ Tecnologías utilizadas

- **Java 17 o superior**
- **Gson 2.10.1** (para parsear JSON)
- **HttpClient** (para consumir APIs REST)
- **IDE**: IntelliJ IDEA (o cualquier IDE compatible)

## 📦 Estructura del proyecto

```plaintext
desafio-conversor-moneda/
│
├── src/
│   ├── APIConversorMonedas.java     # Lógica de conexión a la API y conversión
│   ├── Conversor.java               # Clase principal con menú interactivo
│   ├── HistorialConversiones.java   # Clase para almacenar y mostrar el historial
│   └── Main.java                    # Punto de entrada del programa (método main)
│
├── gson-2.10.1.jar                  # Dependencia para manipular JSON
└── README.md                        # Descripción del proyecto

```
## 📌 Instrucciones para ejecutar
1. Clona el repositorio o descarga los archivos.
2. Asegúrate de tener Java y el JDK configurados correctamente.
3. Abre el proyecto en tu IDE favorito.
4. Asegúrate de incluir la biblioteca gson-2.10.1.jar en tu classpath.
5. Ejecuta la clase Main.

## 📝 Notas
* Recuerda reemplazar la variable apiKey en APIConversorMonedas.java con tu propia clave gratuita de ExchangeRate-API.
* Si la API devuelve error 404, probablemente tu API Key es incorrecta o ha expirado.

## 🤝 Autor
Desarrollado como parte del desafío propuesto en el programa ONE - Oracle Next Education