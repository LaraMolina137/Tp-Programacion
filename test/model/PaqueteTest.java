package model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tipo.TipoAtraccion;

public class PaqueteTest {

	private static final Paquete PAQUETE = new Paquete(
			TipoAtraccion.AVENTURA,
			List.of(
					new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA), 
					new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA)),
			new PromocionPorcentaje(20)
			);
	
	@Test
	public void calcularDuracionTest() {
		float duracionExpected = 5;
		float duracionActual = PAQUETE.calcularDuracion();
		
		//porque assertEquals está deprecado para float
		assertTrue(duracionExpected == duracionActual);
	}
	
	@Test
	public void calcularCostoTotalTest() {
		int costoExpected = 35;
		int costoActual = PAQUETE.calcularCosto();
		
		assertEquals(costoExpected,costoActual);
	}
	
	@Test
	public void calcularCostoTotalConDescuentoTest() {
		int costoExpected = (int) (35 * 0.8);
		int costoActual = PAQUETE.calcularCostoConDescuento();
		
		assertEquals(costoExpected,costoActual);
	}

}
