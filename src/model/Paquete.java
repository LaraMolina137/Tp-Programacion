package model;

import java.util.List;
import tipo.TipoAtraccion;

public class Paquete {
	private static final int INICIO_CADENA = 0;
	private static final int CANT_CARACTERES = 2;
	private TipoAtraccion nombre;
	private List<Atraccion> atracciones;
	private Promocion promocion;

	public Paquete(TipoAtraccion nombre, List<Atraccion> atracciones, Promocion promocion) {
		this.nombre = nombre;
		this.promocion = promocion;
		this.atracciones = atracciones;
	}

	public float calcularDuracion() {
		float duracionTotal = 0;
		for (Atraccion atraccion : atracciones) {
			duracionTotal += atraccion.getTiempoEnRecorrer();
		}

		return duracionTotal;
	}

	public float calcularCosto() {
		int costoTotal = 0;
		for (Atraccion atraccion : atracciones) {
			costoTotal += atraccion.getCosto();
		}
		return costoTotal;
	}

	public float calcularCostoConDescuento() {
		return promocion.aplicarDescuento(atracciones);
	}

	public TipoAtraccion getNombre() {
		return nombre;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public boolean hayCuposEnPaquete() {
		for (Atraccion atraccion : atracciones) {
			if (!atraccion.hayCupos()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		String nombresAtracciones = "";

		for (Atraccion atraccion : atracciones) {
			nombresAtracciones += atraccion.getNombre() + ", ";

		}

		nombresAtracciones = nombresAtracciones.substring(INICIO_CADENA, nombresAtracciones.length() - CANT_CARACTERES);

		return "[ " + nombresAtracciones + " ]";
	}

}
