package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tipo.TipoAtraccion;

public class PromocionTest {
	
	private static final int COSTO = 30;
	
	private static final List<Atraccion> ATRACCIONES = List.of(new Atraccion("Moria", COSTO, 2, 0, TipoAtraccion.AVENTURA));

	
	@Test
	public void promocionAbsolutaTest() {
		PromocionAbsoluta promocion = new PromocionAbsoluta(10);
		
		int costoConDescuento = promocion.aplicarDescuento(ATRACCIONES);
		
		assertEquals(10, costoConDescuento);
	}

	
	@Test
	public void promocionPorcetajeTest() {
		PromocionPorcentaje promocion = new PromocionPorcentaje(20);
		
		int costoConDescuento = promocion.aplicarDescuento(ATRACCIONES);
		
		int costoExpected = 24;
	
		assertEquals(costoExpected, costoConDescuento);
	}
	

	@Test
	public void promocionAXBCostoTest() {
		PromocionAXB promocion = new PromocionAXB(new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA));
		List<Atraccion> atracciones = new ArrayList<Atraccion>(ATRACCIONES);
		
		int costoConDescuento = promocion.aplicarDescuento(atracciones);
		
		assertEquals(COSTO, costoConDescuento);
	}
	
	@Test
	public void promocionAXBAgregaGratisTest() {
		PromocionAXB promocion = new PromocionAXB(new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA));
		
		List<Atraccion> atracciones = new ArrayList<Atraccion>(ATRACCIONES);
		
		promocion.aplicarDescuento(atracciones);
		
		assertEquals(2, atracciones.size());
		
		Atraccion atraccion = atracciones.get(1);
		
		assertEquals("Mordor", atraccion.getNombre());
		
	}
	
}
