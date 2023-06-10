package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Atraccion;
import tipo.TipoAtraccion;

class ArchivoAtraccion {

	public static List<Atraccion> leerArchivo(String nombreArchivo) throws Exception {
		List<Atraccion> atracciones = new ArrayList<>();
		Scanner scanner = null;

		try {
			File file = new File("archivos/" + nombreArchivo);
			scanner = new Scanner(file);
			scanner.useLocale(Locale.ENGLISH);

			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();

				Scanner lineaScanner = new Scanner(linea);
				lineaScanner.useDelimiter(",\\s*"); 
				
				String nombre = lineaScanner.next();
				int costo = lineaScanner.nextInt();
				float tiempoEnRecorrer = Float.parseFloat(lineaScanner.next());
				int cupo = lineaScanner.nextInt();
				String preferencia = lineaScanner.next();
				TipoAtraccion tipo = TipoAtraccion.valueOf(preferencia.toUpperCase());

				Atraccion atraccion = new Atraccion(nombre, costo, tiempoEnRecorrer, cupo, tipo);
				atracciones.add(atraccion);

				lineaScanner.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return atracciones;
	}

}
