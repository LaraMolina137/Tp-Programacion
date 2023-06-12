package archivo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Usuario;
import tipo.TipoAtraccion;
import tipo.TipoPerfil;

class ArchivoUsuarioTest {
	
	@Test
	void testUsuarioListSize() throws Exception {		
		
		int expectedSize = 3;
		final List<Usuario> resultList = ArchivoUsuario.leerArchivo("usuarioTest");
			
		int actualSize = resultList.size();

		assertEquals(expectedSize, actualSize);
	}

	@Test
	void testReadFile() throws Exception {		
		
		List<Usuario> expectedList = Arrays.asList(
				new Usuario("Frodo", TipoAtraccion.AVENTURA, 150, 70),
				new Usuario("Galardiel", TipoAtraccion.PAISAJE, 100, 5),
				new Usuario("Sam", TipoAtraccion.DEGUSTACION, 36, 8)
		);
        
		final List<Usuario> resultList = ArchivoUsuario.leerArchivo("usuarioTest");
			
		for (int i = 0; i < expectedList.size(); i++) {
            Usuario expected = expectedList.get(i);
            Usuario actual = resultList.get(i);

            assertEquals(expected.getNombre(), actual.getNombre());
            assertEquals(expected.getPreferencia(), actual.getPreferencia());
            assertEquals(expected.getPresupuesto(), actual.getPresupuesto());
            assertEquals(expected.getTiempoDisponible(), actual.getTiempoDisponible());
        }
	
	}

}
