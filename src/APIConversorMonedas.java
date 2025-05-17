import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class APIConversorMonedas {
    private final Map<String, Double> tasas = new HashMap<>();
    private final Set<String> monedasValidas = Set.of("ARS", "BOB", "BRL", "CLP", "COP", "USD", "EUR","MXN", "PEN", "GBP", "JPY", "KRW");

    public APIConversorMonedas() {
        cargarTasas();
    }

    private void cargarTasas() {
        String apiKey = "5409246d4304acbaf6601826"; // Reemplaza con tu propia API key
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

                for (String moneda : monedasValidas) {
                    if (conversionRates.has(moneda)) {
                        tasas.put(moneda, conversionRates.get(moneda).getAsDouble());
                    }
                }
            } else {
                System.out.println("Error al obtener los datos. Código: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error de conexión con la API.");
        }
    }

    public double convertir(String monedaOrigen, String monedaDestino, double monto) {
        Double tasaOrigen = tasas.get(monedaOrigen);
        Double tasaDestino = tasas.get(monedaDestino);

        if (tasaOrigen == null || tasaDestino == null) {
            throw new IllegalArgumentException("Moneda no válida.");
        }

        return monto / tasaOrigen * tasaDestino;
    }

    public Set<String> getMonedasDisponibles() {
        return tasas.keySet();
    }
}
