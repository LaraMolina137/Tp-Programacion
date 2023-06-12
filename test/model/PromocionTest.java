package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tipo.TipoAtraccion;

public class PromocionTest {
	
	private static final int COSTO = 30;
	
	private static final List<Atraccion> ATRACCIONES = Arrays.asList((new Atraccion("Moria", COSTO, 2, 0, TipoAtraccion.AVENTURA)));

	
	@Test
	public void promocionAbsolutaTest() {
		PromocionAbsoluta promocion = new PromocionAbsoluta(10);
		
		float costoConDescuento = promocion.aplicarDescuento(ATRACCIONES);
		
		assertTrue(10 == costoConDescuento);
	}

	
	@Test
	public void promocionPorcetajeTest() {
		PromocionPorcentaje promocion = new PromocionPorcentaje(20);
		
		float costoConDescuento = promocion.aplicarDescuento(ATRACCIONES);
		
		float costoExpected = 24;
	
		assertTrue(costoExpected == costoConDescuento);
	}
	

	@Test
	public void promocionAXBCostoTest() {
		PromocionAXB promocion = new PromocionAXB(new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA));
		List<Atraccion> atracciones = new ArrayList<Atraccion>(ATRACCIONES);
		
		float costoConDescuento = promocion.aplicarDescuento(atracciones);
		
		assertTrue(COSTO == costoConDescuento);
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
