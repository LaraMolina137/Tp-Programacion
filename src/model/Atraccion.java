package model;

import tipo.TipoAtraccion;
import java.util.Comparator;

public class Atraccion {

	private String nombre;
	private float costo;
	private float tiempoEnRecorrer;
	private int cupo;
	private TipoAtraccion tipo;

	public Atraccion(String nombre, int costo, float tiempoEnRecorrer, int cupo, TipoAtraccion tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempoEnRecorrer = tiempoEnRecorrer;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	public static final Comparator<Atraccion> ORDEN_COSTO_TIEMPO = new Comparator<Atraccion>() {
		@Override
		public int compare(Atraccion atraccion1, Atraccion atraccion2) {
			int resultado = Float.compare(atraccion1.getCosto(), atraccion2.getCosto());
			if (resultado == 0) {
				return Double.compare(atraccion1.getTiempoEnRecorrer(), atraccion2.getTiempoEnRecorrer()) * -1;
			}
			return resultado * -1;
		}
	};

	public String getNombre() {
		return nombre;
	}

	public float getCosto() {
		return costo;
	}

	public float getTiempoEnRecorrer() {
		return tiempoEnRecorrer;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public boolean hayCupos() {
		return cupo > 0;
	}

	public void restarCupo() {
		cupo -= 1;
	}

}
