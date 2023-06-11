package model;

import static org.junit.Assert.*;

import org.junit.Test;

import tipo.TipoAtraccion;

public class AtraccionTest {

	@Test
	public void noHayCuposTest() {
		Atraccion atraccionSinCupo = new Atraccion("Moria", 10, 2, 0, TipoAtraccion.AVENTURA);
	    boolean hayCupo = atraccionSinCupo.hayCupos();
		assertFalse(hayCupo);
	}
	
	@Test
	public void hayCuposTest() {
		Atraccion atraccionConCupo = new Atraccion("Moria", 10, 2, 1, TipoAtraccion.AVENTURA);
		boolean hayCupo = atraccionConCupo.hayCupos();
		assertTrue(hayCupo);
	}
	
	@Test
	public void restarCupoTest() {
		Atraccion atraccion = new Atraccion("Moria", 10, 2, 1, TipoAtraccion.AVENTURA);
		atraccion.restarCupo();
		
		int cupoRespuesta = atraccion.getCupo();
		assertEquals(0, cupoRespuesta);
	}

}
