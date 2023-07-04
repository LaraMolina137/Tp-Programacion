package model;

import java.util.List;

public class PromocionPorcentaje implements Promocion {

	private static final float PORCENTAJE_TOTAL = 100.0f;
	private int valorPorcentaje;

	public PromocionPorcentaje(int valorPorcentaje) {
		this.valorPorcentaje = valorPorcentaje;
	}

	@Override
	public float aplicarDescuento(List<Atraccion> atracciones) {
		int costoTotal = 0;
		for (Atraccion atraccion : atracciones) {
			costoTotal += atraccion.getCosto();
		}

		return costoTotal - (costoTotal * (valorPorcentaje / PORCENTAJE_TOTAL));
	}

}