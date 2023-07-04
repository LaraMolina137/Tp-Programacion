package model;

import tipo.TipoAtraccion;
import java.util.Comparator;

public class Atraccion {

	private static final int UNIDAD_CUPO = 1;
	private static final int SIN_CUPO = 0;
	private static final int COMPARACION_IGUAL = 0;
	private static final int INVERSO = -1;
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
			if (resultado == COMPARACION_IGUAL) {
				return Double.compare(atraccion1.getTiempoEnRecorrer(), atraccion2.getTiempoEnRecorrer()) * INVERSO;
			}
			return resultado * INVERSO;
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

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public boolean hayCupos() {
		return cupo > SIN_CUPO;
	}

	public void restarCupo() {
		cupo -= UNIDAD_CUPO;
	}

}
