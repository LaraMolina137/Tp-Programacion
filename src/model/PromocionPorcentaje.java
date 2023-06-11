package model;

import java.util.List;

public class PromocionPorcentaje implements Promocion{
	
	private int valorPorcentaje;

	public PromocionPorcentaje(int valorPorcentaje) {
		this.valorPorcentaje = valorPorcentaje;
	}
	
	@Override
	public int aplicarDescuento(List<Atraccion> atracciones) {
		int costoTotal = 0;
		for (Atraccion atraccion : atracciones) {
			costoTotal += atraccion.getCosto();
		}
		
		return (int) (costoTotal - (costoTotal * (valorPorcentaje/100.0f)));
	}

}