package archivo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Atraccion;
import model.Paquete;
import model.PromocionAXB;
import model.PromocionAbsoluta;
import model.PromocionPorcentaje;
import tipo.TipoAtraccion;

class ArchivoPaqueteTest {
	private static final List<Atraccion> EXPECTED_ATRACCIONES = Arrays.asList(
			new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA),
			new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
			new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION),
			new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA),
			new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE),
			new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
			new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE),
			new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA));

	private static final List<Paquete> EXPECTED_PAQUETES = Arrays.asList(
			new Paquete(TipoAtraccion.AVENTURA,
					Arrays.asList(new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA),
							new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA)),
					new PromocionPorcentaje(20)),

			new Paquete(TipoAtraccion.DEGUSTACION,
					Arrays.asList(new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
							new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION)),
					new PromocionAbsoluta(36)),
			new Paquete(TipoAtraccion.PAISAJE,
					Arrays.asList(new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
							new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE)),
					new PromocionAXB(new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE))));

	@Test
	void testPaqueteListSize() throws Exception {
		final List<Paquete> resultList = ArchivoPaquete.leerArchivo("paqueteTest", EXPECTED_ATRACCIONES);

		int actualSize = resultList.size();
		int expectedSize = EXPECTED_PAQUETES.size();

		assertEquals(expectedSize, actualSize);
	}

	@Test
	void testReadFile() throws Exception {
		final List<Paquete> resultList = ArchivoPaquete.leerArchivo("paqueteTest", EXPECTED_ATRACCIONES);

		for (int i = 0; i < EXPECTED_PAQUETES.size(); i++) {
			Paquete expected = EXPECTED_PAQUETES.get(i);
			Paquete actual = resultList.get(i);

			assertEquals(expected.getNombre(), actual.getNombre());

			for (int j = 0; j < expected.getAtracciones().size(); j++) {
				Atraccion atraccionExpected = expected.getAtracciones().get(j);
				Atraccion atraccionActual = actual.getAtracciones().get(j);

				assertEquals(atraccionExpected.getNombre(), atraccionActual.getNombre());
				assertEquals(atraccionExpected.getCosto(), atraccionActual.getCosto());
				assertEquals(atraccionExpected.getCupo(), atraccionActual.getCupo());
				assertEquals(atraccionExpected.getTiempoEnRecorrer(), atraccionActual.getTiempoEnRecorrer());
				assertEquals(atraccionExpected.getTipo(), atraccionActual.getTipo());
			}
		}

	}

}
