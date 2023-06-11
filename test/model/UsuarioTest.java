package model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import tipo.TipoAtraccion;

public class UsuarioTest {

	private static final Usuario USUARIO = new Usuario("Juan Carlos", TipoAtraccion.AVENTURA, 10, 8);

	private static final List<Atraccion> EXPECTED_ATRACCIONES = List.of( 
			new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA), 
			new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
			new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION),
			new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA),
	        new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE),
	        new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
	        new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE),
	        new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA));
	
	private static final List<Paquete> EXPECTED_PAQUETES = List.of(
			new Paquete(
					TipoAtraccion.AVENTURA, 
					List.of(
							new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA),
							new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA)
							),
					new PromocionPorcentaje(20)
					),
			
			new Paquete(
					TipoAtraccion.DEGUSTACION, 
					List.of(
							new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
							new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION)
							),
					new PromocionAbsoluta(36)
					),
			new Paquete(
					TipoAtraccion.PAISAJE, 
					List.of(
							new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
							new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE)
							),
					new PromocionAXB(new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE))
					)
			);
	
	@Test
	public void tieneTiempoYPresupuestoTest() {
		boolean tieneTiempoYPresupuesto = USUARIO.tieneTiempoYPresupuesto();
		
		assertTrue(tieneTiempoYPresupuesto);
	}
	
	@Test
	public void obternerAtraccionesPreferidosTest() {
		List<Atraccion> atraccionesPref = USUARIO.obtenerAtraccionesPreferidas(EXPECTED_ATRACCIONES);
		
		List<Atraccion> prefEsperados = List.of(new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA),
	    new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA));
		
		for (int i = 0; i < prefEsperados.size(); i++) {		
			assertEquals(prefEsperados.get(i).getNombre(), atraccionesPref.get(i).getNombre());
			assertEquals(prefEsperados.get(i).getCosto(), atraccionesPref.get(i).getCosto());
			assertEquals(prefEsperados.get(i).getCupo(), atraccionesPref.get(i).getCupo());
			assertEquals(prefEsperados.get(i).getTiempoEnRecorrer(), atraccionesPref.get(i).getTiempoEnRecorrer());
			assertEquals(prefEsperados.get(i).getTipo(), atraccionesPref.get(i).getTipo());
		}
	}
	
	@Test
	public void obternerPaquetesPreferidosTest() {
		List<Paquete> paquetesPref = USUARIO.obternerPaquetesPreferidos(EXPECTED_PAQUETES);
		
		assertEquals(List.of(), paquetesPref);
	}
	
	@Test
	public void algunaAtraccionEstaEnItinerarioTest() {
		Usuario usuario = new Usuario(
				USUARIO.getNombre(), USUARIO.getPreferencia(), USUARIO.getPresupuesto(), USUARIO.getTiempoDisponible());
		
		boolean atraccionEnItinerario = usuario.algunaAtraccionEstaEnItinerario(new Paquete(
					TipoAtraccion.AVENTURA, 
					List.of(
							new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA),
							new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA)
							),
					new PromocionPorcentaje(20)
					));
		
		assertFalse(atraccionEnItinerario);
	}
	
	@Test
	public void alcanzaTiempoYCostoTest() {
		boolean expected = USUARIO.alcanzaTiempoYCosto(1, 1);
		
		assertTrue(expected);
	}
	
	@Test
	public void noAlcanzaTiempoYCostoTest() {
		boolean expected = USUARIO.alcanzaTiempoYCosto(100, 100);
		
		assertFalse(expected);
	}
	
	@Test
	public void agregarAtraccionAlItinerarioTest() {
		Usuario usuario = new Usuario(
				USUARIO.getNombre(), USUARIO.getPreferencia(), USUARIO.getPresupuesto(), USUARIO.getTiempoDisponible());
		
		usuario.agregarAtraccionAlItinerario(new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA));
		
		int tamItinerario = usuario.getItinerario().size();
		
		assertEquals(1, tamItinerario);
		
		Atraccion atraccionItinerario = usuario.getItinerario().get(0);
				
		assertEquals("Moria", atraccionItinerario.getNombre());
        assertEquals(10, atraccionItinerario.getCosto());
        assertEquals(2, atraccionItinerario.getTiempoEnRecorrer(), 0.001f);
        assertEquals(5, atraccionItinerario.getCupo());
        assertEquals(TipoAtraccion.AVENTURA, atraccionItinerario.getTipo());
	}
	
	@Test
	public void agregarPaqueteAltinerarioTest() {
		Usuario usuario = new Usuario(
				USUARIO.getNombre(), USUARIO.getPreferencia(), USUARIO.getPresupuesto(), USUARIO.getTiempoDisponible());
		
		Paquete paquete = new Paquete(
				TipoAtraccion.AVENTURA, 
				List.of(
						new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA),
						new Atraccion("NoMordor", 1, 3, 4, TipoAtraccion.AVENTURA)
						),
				new PromocionPorcentaje(20)
				);
		
		usuario.agregarPaqueteAlItinerario(paquete);
		
		int tamItinerario = usuario.getItinerario().size();
		
		assertEquals(2, tamItinerario);
		
		List<Atraccion> atraccionItinerario = usuario.getItinerario();
		
		for (int i = 0; i < paquete.getAtracciones().size(); i++) {
            Atraccion expected = paquete.getAtracciones().get(i);
            Atraccion actual = atraccionItinerario.get(i);

            assertEquals(expected.getNombre(), actual.getNombre());
            assertEquals(expected.getCosto(), actual.getCosto());
            assertEquals(expected.getTiempoEnRecorrer(), actual.getTiempoEnRecorrer(), 0.001f);
            assertEquals(expected.getCupo(), actual.getCupo());
            assertEquals(expected.getTipo(), actual.getTipo());
        }

		
	}
	

}
