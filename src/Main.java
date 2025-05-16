import java.util.Set;

public class Main {
    public static void main(String[] args) {
        APIConversorMonedas api = new APIConversorMonedas();

        if (api.getMonedasDisponibles().isEmpty()) {
            System.out.println("No se pudo obtener la información de tasas. Saliendo...");
            System.exit(1);
        }

        Conversor conversor = new Conversor(api);
        conversor.iniciar();
    }
}
