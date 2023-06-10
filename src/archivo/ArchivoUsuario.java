package archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.Usuario;
import tipo.TipoPerfil;

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
				String perfil = lineaScanner.next();

				Usuario usuario = crearUsuario(nombre,perfil);
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


	private static Usuario crearUsuario(final String nombre, final String perfil) throws Exception {
		if(TipoPerfil.FRODO.esPerfil(perfil))
			return new Usuario(nombre,
						TipoPerfil.FRODO.getPreferencia(),
						TipoPerfil.FRODO.getPresupuesto(),
						TipoPerfil.FRODO.getTiempo()
					);
		if(TipoPerfil.GALARDIEL.esPerfil(perfil))
			return new Usuario(nombre,
						TipoPerfil.GALARDIEL.getPreferencia(),
						TipoPerfil.GALARDIEL.getPresupuesto(),
						TipoPerfil.GALARDIEL.getTiempo()
					);
		if(TipoPerfil.SAM.esPerfil(perfil))
			return new Usuario(nombre,
						TipoPerfil.SAM.getPreferencia(),
						TipoPerfil.SAM.getPresupuesto(),
						TipoPerfil.SAM.getTiempo()
					);
		throw new Exception("No coincide con ningun perfil");
	}
}
