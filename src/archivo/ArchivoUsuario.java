package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Usuario;
import tipo.TipoAtraccion;

class ArchivoUsuario {

	public static List<Usuario> leerArchivo(String nombreArchivo) throws Exception {
		List<Usuario> usuarios  = new ArrayList<>();
		Scanner scanner = null;

		try {
			File file = new File("archivos/" + nombreArchivo);
			scanner = new Scanner(file);
			scanner.useLocale(new Locale("es_AR"));

			while (scanner.hasNextLine()) {
			    String linea = scanner.nextLine();

				Scanner lineaScanner = new Scanner(linea);
				lineaScanner.useDelimiter(",\\s*"); 
				
				String nombre = lineaScanner.next();
//				String perfil = lineaScanner.next();
				String preferencia = lineaScanner.next();
				String presupuesto = lineaScanner.next();
				String tiempoDisponible = lineaScanner.next();

				Usuario usuario = crearUsuario(nombre, preferencia, presupuesto, tiempoDisponible);
				usuarios.add(usuario);

				lineaScanner.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return usuarios;
	}


	private static Usuario crearUsuario(final String nombre, String preferencia, String presupuesto, String tiempoDisponible) throws Exception {
		return new Usuario(nombre, TipoAtraccion.valueOf(preferencia.toUpperCase()), Integer.valueOf(presupuesto), Float.valueOf(tiempoDisponible));
	}
}
