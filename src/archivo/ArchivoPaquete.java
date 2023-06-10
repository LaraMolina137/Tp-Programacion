package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Atraccion;
import model.Paquete;
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
				TipoPromocion tipo = TipoPromocion.valueOf(lineaScanner.next().toUpperCase().trim());
				String[] nombresAtraccion = lineaScanner.next().split("-");
				for (String atraccion : nombresAtraccion) {
					nombresAtracciones.add(atraccion.trim());
				}
				String valor = lineaScanner.next();

				Paquete paquete = new Paquete(nombre, nombresAtracciones, tipo, atracciones);
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
}
