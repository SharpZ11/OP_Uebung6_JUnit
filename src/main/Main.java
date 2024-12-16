package main;

import java.io.IOException;

import business.*;
import business.baelle.Ball;
import gui.baelle.BaelleControl;
import gui.sportartikel.SportartikelControl;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void testSportartikelListe() {
		try {
//			SportartikelListe<String> listeStrings = new SportartikelListe<>();
//			SportartikelListe<Integer> listeIntegers = new SportartikelListe<>();
			Ball ball1 = new Ball(20230701, "Leder", "Basketball", "inGebrauch", "Spiel", 59.90);
			Ball ball2 = new Ball(20230801, "Leder", "Fussball", "inGebrauch", "Spiel", 49.90);
			SportartikelListe<Sportartikel> listeSportartikel = new SportartikelListe<>();
			listeSportartikel.addSportartikel(ball1);
			listeSportartikel.addSportartikel(ball2);
			SportartikelListe<Ball> listeBaelle = new SportartikelListe<>();
			listeBaelle.addSportartikel(ball1);
			listeBaelle.addSportartikel(ball2);
			for(int i = 0; i < listeSportartikel.getAnzahlSportartikel(); i++) {
				System.out.println(listeSportartikel.getSportartikel(i).gibZurueck(';'));
			}
			for(int i = 0; i < listeBaelle.getAnzahlSportartikel(); i++) {
				System.out.println(listeBaelle.getSportartikel(i).gibZurueck(';'));
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		new BaelleControl(primaryStage);
		Stage fensterSportartikel = new Stage();
 		new SportartikelControl(fensterSportartikel);

	}
	
	public static void main(String[] args){
		launch(args);
		testSportartikelListe();
		
		ArtikelModel artikelModel = new ArtikelModel();
		try {
			artikelModel.leseArtikelAusCsvDatei();
			artikelModel.schreibeArtikelInTxtDatei();
			artikelModel.schreibeArtikelInKonsole();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
