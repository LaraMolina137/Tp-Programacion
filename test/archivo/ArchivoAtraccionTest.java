package archivo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Atraccion;
import tipo.TipoAtraccion;

class ArchivoAtraccionTest {
	
	@Test
	void testAtraccionListSize() throws Exception {		
		
		int expectedSize = 8;
		final List<Atraccion> resultList = ArchivoAtraccion.leerArchivo("atraccionTest");
			
		int actualSize = resultList.size();

		assertEquals(expectedSize, actualSize);
	}

	@Test
	void testReadFile() throws Exception {		
		
		List<Atraccion> expectedList = Arrays.asList( 
				new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA), 
				new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
				new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION),
				new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA),
		        new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE),
		        new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
		        new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE),
		        new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA));
        
		final List<Atraccion> resultList = ArchivoAtraccion.leerArchivo("atraccion");
			
		for (int i = 0; i < expectedList.size(); i++) {
            Atraccion expected = expectedList.get(i);
            Atraccion actual = resultList.get(i);

            assertEquals(expected.getNombre(), actual.getNombre());
            assertEquals(expected.getCosto(), actual.getCosto());
            assertEquals(expected.getTiempoEnRecorrer(), actual.getTiempoEnRecorrer(), 0.001f);
            assertEquals(expected.getCupo(), actual.getCupo());
            assertEquals(expected.getTipo(), actual.getTipo());
        }
	
	}

}
