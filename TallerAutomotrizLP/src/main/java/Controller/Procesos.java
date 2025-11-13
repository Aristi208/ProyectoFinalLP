package Controller;

import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class Procesos {

    static final String RUTA_ARCHIVO_SOLICITUD_SERVICIOS = "src/main/resources/Precios.csv";
    static final String RUTA_ARCHIVO_REPUESTOS = "src/main/resources/ReparacioneseInsumos.csv";
    static final String RUTA_ARCHIVO_ESCRITURA_ORDENCONDATOS = "src/main/resources";

    // Método público que genera la factura a partir de los datos proporcionados por la UI
    public static String generarFactura(
            String nombreCliente,
            String cedulaCliente,
            String numeroCliente,
            String marcaVehiculo,
            String placaVehiculo,
            String tipoVehiculo,
            List<String> serviciosSeleccionados,
            Collection<String> serviciosExtra,
            Collection<String> repuestosSeleccionados
    ) {
        BufferedReader brServicios = null;
        BufferedReader brRepuestos = null;
        try {
            Map<String, Integer> preciosServicios = leerPreciosServicios();
            Map<String, Map<String, Integer>> mapaRepuestos = leerMapaRepuestos();

            StringBuilder totalServiciosTexto = new StringBuilder();
            int precioServiciosSuma = 0;
            int contadorServicios = 0;

            if (serviciosSeleccionados != null) {
                for (String s : serviciosSeleccionados) {
                    contadorServicios++;
                    int precio = buscarPrecioServicio(preciosServicios, s);
                    precioServiciosSuma += precio;
                    totalServiciosTexto.append("- Servicio # ").append(contadorServicios).append(" : ").append(s)
                            .append(" = ")
                            .append(precio > 0 ? precio + " $" : "Precio no encontrado")
                            .append("\n");
                }
            }

            StringBuilder repuestosTexto = new StringBuilder();
            int precioRepuestosSuma = 0;
            if (repuestosSeleccionados != null) {
                for (String r : repuestosSeleccionados) {
                    // formato esperado: "Servicio - Repuesto"
                    String servicioParte = "";
                    String repuestoParte = r;
                    if (r.contains(" - ")) {
                        String[] parts = r.split(" - ", 2);
                        servicioParte = parts[0].trim();
                        repuestoParte = parts[1].trim();
                    }
                    int precio = buscarPrecioRepuesto(mapaRepuestos, servicioParte, repuestoParte);
                    precioRepuestosSuma += precio;
                    repuestosTexto.append("- ").append(r).append(" = ")
                            .append(precio > 0 ? precio + " $" : "Precio no encontrado")
                            .append("\n");
                }
            }

            // Tratamiento para servicios extra: se asigna un precio fijo por servicio extra (se puede ajustar)
            int precioPorServicioExtra = 30000; // asunción razonable
            int precioServiciosExtraSuma = 0;
            StringBuilder serviciosExtraTexto = new StringBuilder();
            if (serviciosExtra != null && !serviciosExtra.isEmpty()) {
                for (String se : serviciosExtra) {
                    serviciosExtraTexto.append("- ").append(se).append(" = ").append(precioPorServicioExtra).append(" $").append("\n");
                    precioServiciosExtraSuma += precioPorServicioExtra;
                }
            }

            int totalSinIVA = precioServiciosSuma + precioRepuestosSuma + precioServiciosExtraSuma;
            int totalConIVA = AgregarIVA(precioServiciosSuma, precioServiciosExtraSuma, precioRepuestosSuma);

            StringBuilder factura = new StringBuilder();
            factura.append("////////// Informacion Cliente //////////\n")
                    .append("Nombre: ").append(nombreCliente).append("\n")
                    .append("Cédula: ").append(cedulaCliente).append("\n")
                    .append("Telefono: ").append(numeroCliente).append("\n\n")
                    .append("////////// Informacion Vehículo //////////\n")
                    .append("Tipo: ").append(tipoVehiculo).append("\n")
                    .append("Marca: ").append(marcaVehiculo).append("\n")
                    .append("Placa: ").append(placaVehiculo).append("\n\n")
                    .append("//////////    Servicios    //////////\n\n")
                    .append(totalServiciosTexto.toString()).append("\n")
                    .append("//////////    Servicios Extra    //////////\n\n")
                    .append(serviciosExtraTexto.toString()).append("\n")
                    .append("//////////    Repuestos / Insumos    //////////\n\n")
                    .append(repuestosTexto.toString()).append("\n")
                    .append("--------------------------------\n")
                    .append("Subtotal (sin IVA): ").append(totalSinIVA).append(" $").append("\n")
                    .append("Total (con IVA 19%): ").append(totalConIVA).append(" $").append("\n");

            // Escribir archivo en resources con nombre del cliente
            String nombreArchivo = sanearNombreArchivo(nombreCliente) + ".txt";
            File salida = new File(RUTA_ARCHIVO_ESCRITURA_ORDENCONDATOS + File.separator + nombreArchivo);
            try (FileWriter fw = new FileWriter(salida); PrintWriter pw = new PrintWriter(fw)) {
                pw.println(factura.toString());
            }

            return salida.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR: " + e.getMessage();
        } finally {
            try { if (brServicios != null) brServicios.close(); } catch (IOException ignored) {}
            try { if (brRepuestos != null) brRepuestos.close(); } catch (IOException ignored) {}
        }
    }

    // Lee Precios.csv y devuelve un mapa name->precio
    private static Map<String, Integer> leerPreciosServicios() throws IOException {
        Map<String, Integer> mapa = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO_SOLICITUD_SERVICIOS))) {
            String line;
            // saltar encabezado si existe
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.trim().startsWith("#")) continue;
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String key = normalizar(parts[1]);
                    int precio = 0;
                    try { precio = Integer.parseInt(parts[2].trim()); } catch (NumberFormatException ignored) {}
                    mapa.put(key, precio);
                }
            }
        }
        return mapa;
    }

    // Lee ReparacioneseInsumos.csv y devuelve mapa servicio -> (repuesto -> precio)
    private static Map<String, Map<String, Integer>> leerMapaRepuestos() throws IOException {
        Map<String, Map<String, Integer>> mapa = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO_REPUESTOS))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length >= 3) {
                    String servicio = normalizar(parts[0]);
                    String repuesto = normalizar(parts[1]);
                    int precio = 0;
                    try { precio = Integer.parseInt(parts[2].trim()); } catch (NumberFormatException ignored) {}
                    mapa.computeIfAbsent(servicio, k -> new LinkedHashMap<>()).put(repuesto, precio);
                }
            }
        }
        return mapa;
    }

    private static int buscarPrecioServicio(Map<String, Integer> mapa, String servicioBuscado) {
        if (servicioBuscado == null || servicioBuscado.trim().isEmpty()) return 0;
        String keyBuscada = normalizar(servicioBuscado);
        // Busqueda exacta
        if (mapa.containsKey(keyBuscada)) return mapa.get(keyBuscada);
        // Buscar por coincidencia parcial
        for (Map.Entry<String, Integer> e : mapa.entrySet()) {
            if (e.getKey().contains(keyBuscada) || keyBuscada.contains(e.getKey())) {
                return e.getValue();
            }
        }
        return 0;
    }

    private static int buscarPrecioRepuesto(Map<String, Map<String, Integer>> mapa, String servicio, String repuesto) {
        if (repuesto == null) return 0;
        String servKey = normalizar(servicio);
        String repKey = normalizar(repuesto);
        if (mapa.containsKey(repKey)) { // caso accidental: archivo puede tener reordenado
            Map<String,Integer> inner = mapa.get(repKey);
            for (Map.Entry<String,Integer> e: inner.entrySet()) {
                if (e.getKey().equals(repKey)) return e.getValue();
            }
        }
        // buscar por servicio
        for (Map.Entry<String, Map<String, Integer>> entry : mapa.entrySet()) {
            String serv = entry.getKey();
            if (serv.contains(servKey) || servKey.contains(serv)) {
                Map<String, Integer> inner = entry.getValue();
                // buscar repuesto exacto/ parcial
                for (Map.Entry<String, Integer> r : inner.entrySet()) {
                    if (r.getKey().equals(repKey) || r.getKey().contains(repKey) || repKey.contains(r.getKey())) {
                        return r.getValue();
                    }
                }
            }
        }
        return 0;
    }

    private static String normalizar(String s) {
        if (s == null) return "";
        String trimmed = s.trim().toLowerCase();
        // quitar acentos
        String n = Normalizer.normalize(trimmed, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        // eliminar espacios extra
        n = n.replaceAll("\\s+", " ");
        return n;
    }

    private static String sanearNombreArchivo(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return "orden" + System.currentTimeMillis();
        String n = nombre.trim().replaceAll("[^a-zA-Z0-9\\._-]", "_");
        return n;
    }

    // Mantengo la funcion AgregarIVA pero con la misma firma; ahora la usamos pasando subtotal de servicios, insumos y repuestos
    public static int AgregarIVA(int precioServicioSINIVA, int precioInsumoRequerido, int precioRepuestoUsado) {

        try {
            int IVA = (((precioServicioSINIVA + precioInsumoRequerido + precioRepuestoUsado) * 19) / 100);
            int precioServicioIVA = (precioServicioSINIVA + precioInsumoRequerido + precioRepuestoUsado) + IVA;
            return precioServicioIVA;
        } catch (Exception e) {
            return -999;
        }
    }

}
