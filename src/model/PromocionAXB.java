package model;

import java.util.List;

public class PromocionAXB implements Promocion{

	private Atraccion atraccionGratis;
	
	public PromocionAXB(Atraccion atraccion) {
		atraccionGratis = atraccion;
	}

	@Override
	public float aplicarDescuento(List<Atraccion> atracciones) {
		int costoTotal = 0;
		for (Atraccion atraccion : atracciones) {
			costoTotal += atraccion.getCosto();
		}
		
		atracciones.add(atraccionGratis);
		return costoTotal;
	}

}
