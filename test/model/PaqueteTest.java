package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import tipo.TipoAtraccion;

public class PaqueteTest {

	private static final Paquete PAQUETE = new Paquete(
			TipoAtraccion.AVENTURA,
			Arrays.asList(new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA),new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA)),
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
		float costoExpected = 35;
		float costoActual = PAQUETE.calcularCosto();
		
		assertTrue(costoExpected == costoActual);
	}
	
	@Test
	public void calcularCostoTotalConDescuentoTest() {
		float costoExpected = (float) (35 * 0.8);
		float costoActual = PAQUETE.calcularCostoConDescuento();
		
		assertTrue(costoExpected == costoActual);
	}

}
