package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Atraccion;
import model.Paquete;
import model.Promocion;
import model.PromocionAXB;
import model.PromocionAbsoluta;
import model.PromocionPorcentaje;
import tipo.TipoAtraccion;
import tipo.TipoPromocion;

class ArchivoPaquete {

	public static List<Paquete> leerArchivo(String nombreArchivo, List<Atraccion> atracciones) throws Exception {
		List<Paquete> paquetes = new ArrayList<>();
		Scanner scanner = null;
		List<String> nombresAtracciones = new ArrayList<>();

		try {
			File file = new File("archivos/" + nombreArchivo);
			scanner = new Scanner(file);
			scanner.useLocale(new Locale("es_AR"));

			while (scanner.hasNextLine()) {
				nombresAtracciones = new ArrayList<>();
				String linea = scanner.nextLine();

				Scanner lineaScanner = new Scanner(linea);
				lineaScanner.useDelimiter(",\\s*");

				TipoAtraccion nombre = TipoAtraccion.valueOf(lineaScanner.next().toUpperCase());
				String nombrePromocion = lineaScanner.next();
				String[] nombresAtraccion = lineaScanner.next().split("-");
				for (String atraccion : nombresAtraccion) {
					nombresAtracciones.add(atraccion.trim());
				}

				List<Atraccion> atraccionesPaquete = filtrarAtracciones(atracciones, nombresAtracciones);

				String valor = lineaScanner.next();

				Promocion promocion = crearPromocion(nombrePromocion, valor, atracciones);

				Paquete paquete = new Paquete(nombre, atraccionesPaquete, promocion);

				paquetes.add(paquete);

				lineaScanner.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return paquetes;
	}

	private static List<Atraccion> filtrarAtracciones(final List<Atraccion> todasAtracciones,
			final List<String> nombresAtracciones) {
		List<Atraccion> atracciones = new ArrayList<>();
		for (String nombreAtraccion : nombresAtracciones) {
			for (Atraccion atraccion : todasAtracciones) {
				if (atraccion.getNombre().equals(nombreAtraccion)) {
					atracciones.add(atraccion);
				}
			}
		}

		return atracciones;
	}

	private static Promocion crearPromocion(String nombrePromocion, String valor, List<Atraccion> atracciones)
			throws Exception {
		if (TipoPromocion.PORCENTAJE.esPromocion(nombrePromocion))
			return new PromocionPorcentaje(Integer.parseInt(valor));
		if (TipoPromocion.ABSOLUTO.esPromocion(nombrePromocion))
			return new PromocionAbsoluta(Integer.parseInt(valor));
		if (TipoPromocion.AXB.esPromocion(nombrePromocion)) {
			Atraccion atraccion = buscarAtraccion(atracciones, valor);
			return new PromocionAXB(atraccion);
		}
		throw new Exception("No coincide con ningun promocion");
	}

	private static Atraccion buscarAtraccion(final List<Atraccion> atracciones, final String nombreAtraccion)
			throws Exception {

		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equalsIgnoreCase(nombreAtraccion)) {
				return atraccion;
			}
		}

		throw new Exception("No existe atraccion " + "'" + nombreAtraccion + "'");
	}
}
