package readers;

import java.io.IOException;
import java.util.ArrayList;

import business.Artikel;

public abstract class ReaderProduct {
	
	public abstract ArrayList<Artikel> leseArtikel()
		throws IOException; 

}
