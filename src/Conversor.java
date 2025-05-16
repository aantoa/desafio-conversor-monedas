import java.util.*;

public class Conversor {
    private static final Map<Integer, String> opcionesMonedas = new LinkedHashMap<>() {{
        put(1, "ARS");
        put(2, "BOB");
        put(3, "COP");
        put(4, "CLP");
        put(5, "USD");
        put(6, "BRL");
    }};

    private final Set<String> codigosMonedas = new HashSet<>(opcionesMonedas.values());
    private final APIConversorMonedas apiConversor;

    public Conversor(APIConversorMonedas apiConversor) {
        this.apiConversor = apiConversor;
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("****************************************");
        System.out.println("Bienvenido/a al Conversor de Monedas 💱");
        System.out.println("****************************************");
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
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.print("\n¿Deseas hacer otra conversión? (s/n): ");
            String resp = scanner.nextLine().trim().toLowerCase();
            if (!resp.equals("s") && !resp.equals("si")) {
                System.out.println("👋 Gracias por usar el conversor. ¡Hasta luego!");
                break;
            }
            mostrarMonedas();
        }

        scanner.close();
    }

    private void mostrarMonedas() {
        System.out.print("Monedas disponibles: [");
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
