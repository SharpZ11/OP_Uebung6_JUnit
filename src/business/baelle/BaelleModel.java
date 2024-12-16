package business.baelle;

import business.*;
import java.io.*;
import java.util.Vector;
import ownUtil.*;

 public class BaelleModel implements Observable{
	 
	private Vector<Observer> observers = new Vector<Observer>();
  		
	private SportartikelListe<Ball> baelle = new SportartikelListe<>();
		
	private static BaelleModel baelleModel;
	
	private BaelleModel() {		
	}
	
	public static BaelleModel getInstance() {
		if(baelleModel == null) {
			baelleModel = new BaelleModel();
		}
		return baelleModel;
	}
	
	public SportartikelListe<Ball> getBaelle(){
		return this.baelle;
	}
	
	public Ball gibBall(String einkaufsdatum) {
		Ball ball = null;
		int i = 0;
		while (ball == null && i < this.baelle.getAnzahlSportartikel()) {
			if(Integer.parseInt(einkaufsdatum) == this.baelle.getSportartikel(i).getEinkaufsdatum()){
				ball = this.baelle.getSportartikel(i);
			}
			i++;
		}
		return ball;
	}
	
	@Override
	public void addObserver(Observer obs){
		this.observers.addElement(obs);
	}
	
	@Override
	public void removeObserver(Observer obs){
		this.observers.removeElement(obs);
	}
	
	@Override
	public void notifyObservers(){
		for(int i = 0; i < this.observers.size(); i++){
			this.observers.elementAt(i).update();
		}
	}

	// Die Fabrik-Methode wurde zum Lesen aus der CsvDatei nicht angewendet
	public void leseBaelleAusDatei()
	    throws Exception{
	    BufferedReader ein = new BufferedReader(new FileReader("Baelle.csv"));
	    int anzahlBaelle = Integer.parseInt(ein.readLine());
	   	String[] zeile;
	   	this.baelle.removeAllSportartikel();
	   	// Das Ersetzen der for-Schleife durch eine foreach-Schleife ist nicht
	   	// sinnvoll, da die SportartikelListe<Ball> erst erstellt werden muss. 
	   	for(int i = 0; i < anzahlBaelle; i++) {
	   		zeile = ein.readLine().split(";");
	   		this.baelle.addSportartikel(new Ball(
	   			Integer.parseInt(zeile[0]), 
	   			zeile[1], zeile[2], zeile[3], zeile[4], 
	   			Double.parseDouble(zeile[5])));
	   	}
	    ein.close();
    	notifyObservers();
 	}

}