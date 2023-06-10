package archivo;

import java.util.List;

import model.Atraccion;
import model.Paquete;
import model.Usuario;

public class Archivo {

	private List<Usuario> usuarios;
	private List<Atraccion> atracciones;
	private List<Paquete> paquetes;
	
	public Archivo() throws Exception {
		cargarArchivos();
	}

	private void cargarArchivos() throws Exception {
		usuarios = ArchivoUsuario.leerArchivo("usuario");
		atracciones = ArchivoAtraccion.leerArchivo("atraccion");
		paquetes = ArchivoPaquete.leerArchivo("paquete", atracciones);
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public List<Paquete> getPaquetes() {
		return paquetes;
	}
}
