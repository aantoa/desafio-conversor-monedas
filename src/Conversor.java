import java.util.*;

public class Conversor {
    private static final Map<Integer, String> opcionesMonedas = new LinkedHashMap<>() {{
        put(1, "ARS");
        put(2, "BOB");
        put(3, "COP");
        put(4, "CLP");
        put(5, "USD");
        put(6, "BRL");
        put(7, "EUR");
        put(8, "MXN");
        put(9, "PEN");
        put(10, "GBP");
        put(11, "JPY");
        put(12, "KRW");
    }};

    private final Set<String> codigosMonedas = new HashSet<>(opcionesMonedas.values());
    private final APIConversorMonedas apiConversor;
    private final HistorialConversor historial;

    Scanner scanner = new Scanner(System.in);

    public Conversor(APIConversorMonedas apiConversor, HistorialConversor historial) {
        this.apiConversor = apiConversor;
        this.historial = historial;
    }

    public void iniciar() {
        System.out.println("****************************************");
        System.out.println("Bienvenido/a al Conversor de Monedas 💱");
        mostrarMonedas();

        while (true) {
            System.out.println("\nIngrese la moneda de origen:");
            String origen = leerMoneda(scanner);

            System.out.println("Ingrese la moneda de destino:");
            String destino = leerMoneda(scanner);

            System.out.print("Ingrese el monto a convertir: ");
            double monto = leerMonto(scanner);

            try {
                double resultado = apiConversor.convertir(origen, destino, monto);
                System.out.printf("El valor de %.2f %s corresponde a %.2f %s%n",
                        monto, origen, resultado, destino);
                historial.agregarRegistroConTimestamp(origen, destino, monto, resultado);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            while (true) {
                System.out.println("\n¿Qué deseas hacer ahora?");
                System.out.println("1. Realizar otra conversión");
                System.out.println("2. Ver historial de conversiones");
                System.out.println("3. Salir");
                System.out.print("👉 Ingresa una opción (1/2/3): ");
                String opcion = scanner.nextLine().trim();

                if (opcion.equals("1")) {
                    mostrarMonedas();
                    break; // sale del menú y vuelve a convertir
                } else if (opcion.equals("2")) {
                    historial.mostrarHistorial();
                    // vuelve a mostrar el menú después de ver historial
                } else if (opcion.equals("3") || opcion.equalsIgnoreCase("salir") || opcion.equals("0")) {
                    System.out.println("👋 Gracias por usar el conversor. ¡Hasta luego!");
                    scanner.close();
                    return;
                } else {
                    System.out.println("❌ Opción inválida. Intenta nuevamente.");
                }
            }
        }
    }

    private void mostrarMonedas() {
        System.out.println("****************************************");
        System.out.print("\nMonedas disponibles: [");
        opcionesMonedas.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.print(entry.getKey() + ". " + entry.getValue() + ", "));
        System.out.println("\b\b]");
        System.out.println("Para salir escribe 0 o SALIR en cualquier momento.");
    }

    private String leerMoneda(Scanner scanner) {
        String entrada;
        while (true) {
            System.out.print("👉 Ingresa opción (número o código): ");
            entrada = scanner.nextLine().trim().toUpperCase();

            if (entrada.equals("0") || entrada.equals("SALIR")) {
                System.out.println("👋 Saliendo del conversor...");
                System.exit(0);
            }

            try {
                int numero = Integer.parseInt(entrada);
                if (opcionesMonedas.containsKey(numero)) {
                    return opcionesMonedas.get(numero);
                }
            } catch (NumberFormatException ignored) {
            }

            if (codigosMonedas.contains(entrada)) {
                return entrada;
            }

            System.out.println("❌ Opción inválida. Intenta nuevamente.");
        }
    }

    private double leerMonto(Scanner scanner) {
        double monto;
        while (true) {
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("salir") || entrada.equals("0")) {
                System.out.println("👋 Saliendo del conversor...");
                System.exit(0);
            }
            try {
                monto = Double.parseDouble(entrada);
                if (monto < 0) {
                    System.out.print("Por favor, ingresa un monto positivo: ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.print("Monto inválido. Intenta de nuevo: ");
            }
        }
        return monto;
    }
}
