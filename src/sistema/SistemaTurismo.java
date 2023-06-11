package sistema;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import archivo.Archivo;
import model.*;

public class SistemaTurismo {
	
	public static void ejecutarSistema() throws Exception {
		Archivo archivo= new Archivo();
		
		List<Atraccion> atracciones = archivo.getAtracciones();
		List<Usuario> usuarios = archivo.getUsuarios();
		List<Paquete> paquetes = archivo.getPaquetes();

		for (Usuario usuario : usuarios)
		{
			System.out.println("Nombre del visitante: " + usuario.getNombre());
			System.out.println("");
			Sugerencia.sugerir(usuario, paquetes, atracciones);
			
			generarResumen(usuario);
			escribirResumen("Resumen " + usuario.getNombre() + ".txt", usuario);
		}
	}

	private static void generarResumen(final Usuario usuario)
	{
		int costoTotalItinerario = 0;
		float duracionTotalItinerario = 0;
		
		System.out.println("Resumen de su itinerario:");
		System.out.println("Atracciones a realizar:");
		
		for (Atraccion atraccion : usuario.getItinerario())
		{
			System.out.println("\t- " + atraccion.getNombre());
			costoTotalItinerario =  costoTotalItinerario + atraccion.getCosto();
			duracionTotalItinerario =  duracionTotalItinerario + atraccion.getTiempoEnRecorrer();
		}
		
		System.out.println("Total de monedas: " + costoTotalItinerario);
		System.out.println("Total de horas: " + duracionTotalItinerario);
		System.out.println("-------------------------------------------------------------------");
	}
	
	private static void escribirResumen(String nombreArchivo, Usuario usuario)
	{
		int costoTotalItinerario = 0;
		float duracionTotalItinerario = 0;
		
		try {
			FileWriter escritor = new FileWriter(nombreArchivo);
			
			escritor.write("Resumen de compra para el usuario " + usuario.getNombre() + "\n");
			escritor.write("Atracciones adquiridas:\n");
			
			for (Atraccion atraccion : usuario.getItinerario())
			{
				escritor.write("\t- " + atraccion.getNombre() + "\n");
				costoTotalItinerario =+ atraccion.getCosto();
				duracionTotalItinerario =+ atraccion.getTiempoEnRecorrer();
			}
			
			escritor.write("Total de horas: " + duracionTotalItinerario + "\n");
			escritor.write("Total de monedas: " + costoTotalItinerario + "\n");
			
		    escritor.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		finally {
			
		}
	}

}
