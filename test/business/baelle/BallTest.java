package business.baelle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BallTest {

	private Ball ball;

	@BeforeEach
	void setUp() throws Exception {				
		// Sportart ist null
		this.ball = new Ball(20140601, "Leder", null, "in Gebrauch", "Spiel", 59.99);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.ball = null;
	}

	@Test
	void testUeberpruefeSportart() throws Exception {

		// Variante mit boolean condition, String message
		assertTrue(ball.ueberpruefeSportart() == false,
				"Das Ergebnis muss false ergeben");
		
		// boolean condition, Supplier<String> messageSuppliert
		// im Lambda-Ausdruck
		// Hier wird die Sportart gesetzt
		this.ball.setSportart("Fußball");
		assertTrue(ball.ueberpruefeSportart() == true,
				() -> "Das Ergebins muss true ergeben");
	}

}
