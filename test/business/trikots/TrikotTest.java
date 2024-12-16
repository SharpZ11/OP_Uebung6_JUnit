package business.trikots;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class TrikotTest {
	
	public Trikot trikot;

	@AfterEach
	void tearDown() throws Exception {
		this.trikot = null;
	}

	@Test
	void test() {
		// trikotnummer, groesse, preis, einkaufdatum
		this.trikot = new Trikot(1, 42, 49.90, 20240701);
		// 1.Test
		// Fuer den Fall, Trikotnummer > 0
		// BooleanSupplier boolsupplier, String message variante
		assertTrue((BooleanSupplier) () -> trikot.getTrikotnummer() > 0,
				"Das Ergebniss muss True ergeben");
		
		// 2.Test
		// Fuer den Fall, Trikotgroesse > 0
		// BooleanSupplier boolsupplier, Supplier<String> message
		assertTrue((BooleanSupplier) () -> trikot.getGroesse() > 0,
				(Supplier<String>) () -> "Das Ergebniss muss True ergeben");
		
		// 3.Test
		// Fuer den Fall, Trikotnummer =< 0
		// <T> T assertThrows(Class<T> clazz, Executable executable)
		// Beispiel in Folien OOP4 Folie 28
		Throwable exc = assertThrows(IllegalArgumentException.class,
				(Executable)()->{new Trikot(-1, 40, 29.99, 20220919);}); //this.trikot.setGroesse(0) geht auch
		// String expected, String actual ,gehört zum assertThrows
		assertEquals("Trikotnummer darf nicht <= 0 sein",
				exc.getMessage());
		
		
		
	}

}
