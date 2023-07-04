package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Sugerencia {

	public static void sugerir(final Usuario usuario, final List<Paquete> paquetes, final List<Atraccion> atracciones) {

		List<Paquete> paquetesPreferidos = usuario.obternerPaquetesPreferidos(paquetes);
		List<Atraccion> atraccionesPreferidas = usuario.obtenerAtraccionesPreferidas(atracciones);

		sugerirPaquetes(usuario, paquetesPreferidos);
		sugerirAtracciones(usuario, atraccionesPreferidas);

		sugerirPaquetes(usuario, obtenerPaquetesSobrantes(paquetes, paquetesPreferidos));
		sugerirAtracciones(usuario, obtenerAtraccionesSobrantes(atracciones, atraccionesPreferidas));

	}

	private static List<Paquete> obtenerPaquetesSobrantes(final List<Paquete> paquetes,
			final List<Paquete> paquetesPreferidos) {
		final List<Paquete> paquetesSobrantes = new ArrayList<>();

		for (Paquete paquete : paquetes) {
			if (!paquetesPreferidos.contains(paquete)) {
				paquetesSobrantes.add(paquete);
			}
		}

		return paquetesSobrantes;
	}

	private static List<Atraccion> obtenerAtraccionesSobrantes(final List<Atraccion> atracciones,
			final List<Atraccion> atraccionesPreferidas) {
		final List<Atraccion> atraccionesSobrantes = new ArrayList<>();

		for (Atraccion atraccion : atracciones) {
			if (!atraccionesPreferidas.contains(atraccion)) {
				atraccionesSobrantes.add(atraccion);
			}
		}

		return atraccionesSobrantes;
	}

	private static void sugerirPaquetes(final Usuario usuario, final List<Paquete> paquetes) {
		if (!usuario.tieneTiempoYPresupuesto())
			return;

		for (Paquete paquete : paquetes) {
			if (paquete.hayCuposEnPaquete() && !usuario.algunaAtraccionEstaEnItinerario(paquete)
					&& usuario.alcanzaTiempoYCosto(paquete.calcularDuracion(), paquete.calcularCostoConDescuento())) {
				if (sugerirPaquete(paquete)) {
					System.out.println("Aceptada!");
					usuario.agregarPaqueteAlItinerario(paquete);
				}

				System.out.println("-------------------------------------------------------------------");
				System.out.println("");

			}

		}
	}

	private static void sugerirAtracciones(final Usuario usuario, List<Atraccion> atracciones) {
		if (!usuario.tieneTiempoYPresupuesto())
			return;

		Collections.sort(atracciones, Atraccion.ORDEN_COSTO_TIEMPO);

		for (Atraccion atraccion : atracciones) {
			if (atraccion.hayCupos() && !usuario.atraccionEstaEnItinerario(atraccion)
					&& usuario.alcanzaTiempoYCosto(atraccion.getTiempoEnRecorrer(), atraccion.getCosto())) {

				if (sugerirAtraccion(atraccion)) {
					System.out.println("Aceptada!");
					usuario.agregarAtraccionAlItinerario(atraccion);
				}

				System.out.println("-------------------------------------------------------------------");
				System.out.println("");

			}
		}
	}

	private static boolean sugerirPaquete(final Paquete paquete) {
		System.out.println("Promocion: ");
		System.out.println("-Atracciones incluidas: " + paquete.toString());
		System.out.println("-Duracion: " + paquete.calcularDuracion());
		System.out.println("-Precio original: $" + paquete.calcularCosto());
		System.out.println("-Precio con descuento: $" + paquete.calcularCostoConDescuento());
		System.out.println("");

		Scanner scanner = new Scanner(System.in);
		String respuesta;

		do {
			System.out.println("Acepta sugerencia? Ingrese S o N");
			respuesta = scanner.nextLine();
		} while (!respuesta.toUpperCase().equals("S") && !respuesta.toUpperCase().equals("N"));

		return respuesta.equalsIgnoreCase("S");
	}

	private static boolean sugerirAtraccion(final Atraccion atraccion) {

		System.out.println("Atracción: ");
		System.out.println("Nombre: [" + atraccion.getNombre() + "]");
		System.out.println("-Precio: " + atraccion.getCosto());
		System.out.println("-Duración: " + atraccion.getTiempoEnRecorrer());
		System.out.println("");

		String respuesta;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Acepta sugerencia? Ingrese S o N");
			respuesta = scanner.nextLine();
		} while (!respuesta.toUpperCase().equals("S") && !respuesta.toUpperCase().equals("N"));

		return respuesta.equalsIgnoreCase("S");

	}

}
