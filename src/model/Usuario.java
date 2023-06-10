package model;

import java.util.ArrayList;
import java.util.List;

import tipo.TipoAtraccion;

public class Usuario {

	private String nombre;
	private TipoAtraccion preferencia;
	private int presupuesto;
	private float tiempoDisponible;
	private List<Atraccion> itinerario;
		
	public Usuario(final String nombre, final TipoAtraccion preferencia, int presupuesto, float tiempoDisponible) {
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.itinerario = new ArrayList<>();
	}
	
	public boolean tieneTiempoYPresupuesto() {
		return presupuesto > 0 && tiempoDisponible > 0;
	}
	
	public List<Paquete> obternerPaquetesPreferidos(final List<Paquete> paquetes) {
		final List<Paquete> paquetesPreferidos = new ArrayList<>();
		for (Paquete paquete : paquetes) {
			if (paquete.getNombre().equals(preferencia) && paquete.calcularCosto() <= presupuesto
					&& paquete.calcularDuracion() <= tiempoDisponible) {
				paquetesPreferidos.add(paquete);
			}
		}
		return paquetesPreferidos;
	}
	
	public List<Atraccion> obtenerAtraccionesPreferidas(final List<Atraccion> atracciones){
	 final List<Atraccion> atraccionesPreferidas = new ArrayList<>();
	 for (Atraccion atraccion : atracciones) {
		 if(atraccion.getTipo().equals(preferencia) && atraccion.getCosto() <= presupuesto
				 && atraccion.getTiempoEnRecorrer() <= tiempoDisponible) {
			 atraccionesPreferidas.add(atraccion);
		 }
	 }
	 
	 return atraccionesPreferidas;
	}
	
	public boolean algunaAtraccionEstaEnItinerario(final Paquete paquete) {
		for(Atraccion atraccion : paquete.getAtracciones()) {
			if(atraccionEstaEnItinerario(atraccion))
				return true;
		}
		
		return false;
	}
	
	public boolean atraccionEstaEnItinerario(final Atraccion atraccion) {
		return itinerario.contains(atraccion); 
	}
	
	public boolean alcanzaTiempoYCosto(final float tiempo, final int costo) {
		return tiempoDisponible - tiempo >= 0 && presupuesto - costo >= 0;
	}
	
	public void agregarAtraccionAlItinerario(final Atraccion atraccion) {
		itinerario.add(atraccion);
		presupuesto -= atraccion.getCosto();		
		tiempoDisponible -= atraccion.getTiempoEnRecorrer();
		atraccion.restarCupo();
	}
	
	public void agregarPaqueteAlItinerario(final Paquete paquete) {
		itinerario.addAll(paquete.getAtracciones());
		presupuesto -= paquete.calcularCosto();
		tiempoDisponible -= paquete.calcularDuracion();
		for (Atraccion atraccion : paquete.getAtracciones()) {
			atraccion.restarCupo();
		}
		
	}

	public String getNombre() {
		return nombre;
	}

	public List<Atraccion> getItinerario() {
		return itinerario;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public float getTiempoDisponible() {
		return tiempoDisponible;
	}

	public TipoAtraccion getPreferencia() {
		return preferencia;
	}
	
	
	
}
