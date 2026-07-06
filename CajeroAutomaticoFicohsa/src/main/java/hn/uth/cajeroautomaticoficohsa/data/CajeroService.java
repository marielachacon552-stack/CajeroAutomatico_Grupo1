package hn.uth.cajeroautomaticoficohsa.data;

import java.io.*;
import java.util.*;

public class CajeroService {
    public static List<Cliente> cargarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        // Ajusta la ruta si es necesario. En aplicaciones web, esto se suele cargar desde el ClassLoader
        try (InputStream is = CajeroService.class.getClassLoader().getResourceAsStream("clientes.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                clientes.add(new Cliente(datos[0], datos[1], Double.parseDouble(datos[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
