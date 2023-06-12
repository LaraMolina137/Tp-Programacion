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
		boolean siEstaAtraccionGratis = false;
		
		for (Atraccion atraccion : atracciones) {
			if(!atraccion.equals(atraccionGratis))
				costoTotal += atraccion.getCosto();
			else 
				siEstaAtraccionGratis = true;	
		}	
		
		if(!siEstaAtraccionGratis)
			atracciones.add(atraccionGratis);
		
		return costoTotal;
	}

}
