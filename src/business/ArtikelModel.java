package business;

import java.io.*;
import java.util.ArrayList;

import readers.*;
import writers.*;

public class ArtikelModel {
	
	private ArrayList<Artikel> artikel = new ArrayList<>();
	
	public void leseArtikelAusCsvDatei()
        throws IOException{
		ReaderCreator readerCreator = new ConcreteCsvReaderCreator();
		ReaderProduct reader = readerCreator.factoryMethod();
		this.artikel = reader.leseArtikel();
	}
	
	public void schreibeArtikelInTxtDatei()
	    throws IOException{
		WriterCreator writerCreator = new ConcreteTxtWriterCreator();
		WriterProduct writer = writerCreator.factoryMethod();
		writer.schreibeArtikel(this.artikel);
	}
	
	public void schreibeArtikelInKonsole()
		throws IOException{
		WriterCreator writerCreator = new ConcreteKonsoleWriterCreator();
		WriterProduct writer = writerCreator.factoryMethod();
		writer.schreibeArtikel(this.artikel);
	}

}
