package model;

import java.util.ArrayList;
import java.util.List;

import tipo.TipoAtraccion;
import tipo.TipoPromocion;

public class Paquete {
	private TipoAtraccion nombre;
	private TipoPromocion tipo;
	private List<Atraccion> atracciones;

	public Paquete(TipoAtraccion nombre, List<String> nombresAtracciones, TipoPromocion tipo,
			List<Atraccion> atracciones) {
		this.nombre = nombre;
		this.tipo = tipo;
		cargaAtracciones(atracciones, nombresAtracciones);
	}

	private void cargaAtracciones(final List<Atraccion> todasAtracciones, final List<String> nombresAtracciones) {
		atracciones = new ArrayList<>();
		for (Atraccion atraccion : todasAtracciones) {
			for (String nombreAtraccion : nombresAtracciones) {
				if (atraccion.getNombre().equals(nombreAtraccion)) {
					atracciones.add(atraccion);
				}
			}
		}
	}

	public float calcularDuracion() {
		float duracionTotal = 0;
		for (Atraccion atraccion : atracciones) {
			duracionTotal += atraccion.getTiempoEnRecorrer();
		}

		return duracionTotal;
	}

	public int calcularCosto() {
		int costoTotal = 0;
		for (Atraccion atraccion : atracciones) {
			costoTotal += atraccion.getCosto();
		}
		return costoTotal;
	}

	public TipoAtraccion getNombre() {
		return nombre;
	}
	
	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setNombre(TipoAtraccion nombre) {
		this.nombre = nombre;
	}
	
	public boolean hayCuposEnPaquete() {
		for(Atraccion atraccion : atracciones) {
			if(!atraccion.hayCupos()) {
				return false;
			}
		}
		return true;
	}
	
	

	public TipoPromocion getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return "Paquete [nombre=" + nombre + ", nombresAtracciones=" + atracciones + ", tipo=" + tipo + "]";
	}

}
