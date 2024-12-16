package business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArtikelTest {

	private Artikel artikel;

	@BeforeEach
	void setUp() throws Exception {
		this.artikel = new Artikel(110, "Parkett Eiche Geräuchert", 100);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.artikel = null;
	}

	@Test
	void test() {
		double erg;
		// Fall: Anzahl Artikel gekauft gleich 0
		try {
			artikel.berechnePreisJeArtikel(0);
			fail("Exception: Anzahl der gekauften Artikel ist 0");
		} 
		catch (Exception exc) {
		}
		
		// Fall: Anzahl Artikel gekauft kleiner 0
		try {
			artikel.berechnePreisJeArtikel(-1);
			fail("Exception: Anzahl der gekauften Artikel kleiner 0");
		} 
		catch (Exception exc) {	
		}
		// Fall: anzahlKaufArtikel ist 500 oder 1000
		// genau 500
		erg = this.artikel.berechnePreisJeArtikel(500);
//		System.out.println("500 Stueck: "+erg);
		assertEquals(75.0, erg, 0.001);
		// zwischen 500 und 1000 testen
		erg = this.artikel.berechnePreisJeArtikel(600);
//		System.out.println("600 Stueck: "+erg);
		assertEquals(72.0, erg, 0.001);
		// genau 1000
		erg = this.artikel.berechnePreisJeArtikel(1000);
//		System.out.println("1000 Stueck: "+erg);
		assertEquals(60.0, erg, 0.001);
		// mehr als 1000
		erg = this.artikel.berechnePreisJeArtikel(1001);
//		System.out.println("1001 Stueck: "+erg);
		assertEquals(60.0, erg, 0.001);

	}

}
