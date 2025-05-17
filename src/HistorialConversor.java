import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistorialConversor {
    private final List<String> historial = new ArrayList<>();

    public void agregarRegistroConTimestamp(String origen, String destino, double monto, double resultado) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String registro = String.format("[%s] %.2f %s → %.2f %s", timestamp, monto, origen, resultado, destino);
        historial.add(registro);
    }

    public void mostrarHistorial() {
        if (historial.isEmpty()) {
            System.out.println("\n----------------------------------------------");
            System.out.println("No hay conversiones registradas.");
            System.out.println("\n----------------------------------------------");
        } else {

            System.out.println("\n----------------------------------------------");
            System.out.println("\n📜 Historial de conversiones:");
            historial.forEach(System.out::println);
            System.out.println("\n----------------------------------------------");
        }
    }
}