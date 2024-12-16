package readers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import business.Artikel;

public class ConcreteCsvReaderProduct extends ReaderProduct {

	@Override
	public ArrayList<Artikel> leseArtikel() throws IOException {
		ArrayList<Artikel> artikel = new ArrayList<>();
		BufferedReader ein = new BufferedReader(new FileReader("Artikel.csv"));
		String[] zeile = null;
		String zeileStr = ein.readLine();
		while(zeileStr != null) {
			zeile = zeileStr.split(";");
			artikel.add(new Artikel(		
				Integer.parseInt(zeile[0]), zeile[1],
				Double.parseDouble(zeile[2])));
			zeileStr = ein.readLine();
		}    
	    ein.close();
		return artikel;
	}
}
