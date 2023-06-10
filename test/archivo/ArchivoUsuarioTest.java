package archivo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Usuario;
import tipo.TipoPerfil;

class ArchivoUsuarioTest {
	
	@Test
	void testUsuarioListSize() throws Exception {		
		
		int expectedSize = 3;
		final List<Usuario> resultList = ArchivoUsuario.leerArchivo("usuario");
			
		int actualSize = resultList.size();

		assertEquals(expectedSize, actualSize);
	}

	@Test
	void testReadFile() throws Exception {		
		
		List<Usuario> expectedList = List.of(
				new Usuario("Diego Martínez", TipoPerfil.FRODO.getPreferencia(), TipoPerfil.FRODO.getPresupuesto(), TipoPerfil.FRODO.getTiempo()),
				new Usuario("Ana Herrera", TipoPerfil.GALARDIEL.getPreferencia(), TipoPerfil.GALARDIEL.getPresupuesto(), TipoPerfil.GALARDIEL.getTiempo()),
				new Usuario("Laura Jiménez", TipoPerfil.GALARDIEL.getPreferencia(), TipoPerfil.GALARDIEL.getPresupuesto(), TipoPerfil.GALARDIEL.getTiempo())
		);
        
		final List<Usuario> resultList = ArchivoUsuario.leerArchivo("usuario");
			
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
