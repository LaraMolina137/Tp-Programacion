package archivo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Atraccion;
import model.Paquete;
import tipo.TipoAtraccion;
import tipo.TipoPromocion;

class ArchivoPaqueteTest {
	
	@Test
	void testPaqueteListSize() throws Exception {		
		List<Atraccion> atracciones = List.of( 
				new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA), 
				new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
				new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION),
				new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA),
		        new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE),
		        new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
		        new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE),
		        new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA));
		
		int expectedSize = 3;
		final List<Paquete> resultList = ArchivoPaquete.leerArchivo("paquete", atracciones);
			
		int actualSize = resultList.size();

		assertEquals(expectedSize, actualSize);
	}

	@Test
	void testReadFile() throws Exception {	
		List<Atraccion> atracciones = List.of( 
				new Atraccion("Moria", 10, 2, 6, TipoAtraccion.AVENTURA), 
				new Atraccion("Minas Tirith", 5, (float) 2.5, 25, TipoAtraccion.PAISAJE),
				new Atraccion("La Comarca", 3, (float) 6.5, 150, TipoAtraccion.DEGUSTACION),
				new Atraccion("Mordor", 25, 3, 4, TipoAtraccion.AVENTURA),
		        new Atraccion("Abismo de Helm", 5, 2, 15, TipoAtraccion.PAISAJE),
		        new Atraccion("Lothlorien", 35, 1, 30, TipoAtraccion.DEGUSTACION),
		        new Atraccion("Erebor", 12, 3, 32, TipoAtraccion.PAISAJE),
		        new Atraccion("Bosque Negro", 3, 4, 12, TipoAtraccion.AVENTURA));
		List<Paquete> expectedList = List.of(
			new Paquete(TipoAtraccion.AVENTURA, new ArrayList<String>(List.of("Bosque Negro","Mordor")), TipoPromocion.PORCENTAJE, atracciones ),
			new Paquete(TipoAtraccion.DEGUSTACION, new ArrayList<String>(List.of("Lothlorien","La Comarca")), TipoPromocion.ABSOLUTO, atracciones ),
			new Paquete(TipoAtraccion.PAISAJE, new ArrayList<String>(List.of("Minas Tirith","Abismo de Helm")), TipoPromocion.AXB, atracciones )
		);
        
		final List<Paquete> resultList = ArchivoPaquete.leerArchivo("paquete",atracciones);
			
		for (int i = 0; i < expectedList.size(); i++) {
            Paquete expected = expectedList.get(i);
            Paquete actual = resultList.get(i);

            assertEquals(expected.getNombre(), actual.getNombre());
            assertEquals(expected.getTipo(), actual.getTipo());
            
            for (int j = 0; j < expected.getAtracciones().size(); j++) {
				Atraccion atraccionExpected = expected.getAtracciones().get(j);
				Atraccion atraccionActual = actual.getAtracciones().get(j);
				
				assertEquals(atraccionExpected.getNombre(), atraccionActual.getNombre());
			}
        }
	
	}

}
