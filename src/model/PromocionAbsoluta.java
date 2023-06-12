package model;

import java.util.List;

public class PromocionAbsoluta implements Promocion{

	private int valorAbsoluto;
	
	public PromocionAbsoluta(int valorAbsoluto) {
		this.valorAbsoluto = valorAbsoluto;
	}

	@Override
	public float aplicarDescuento(List<Atraccion> atracciones) {
		return valorAbsoluto;
	}
	
	
}
