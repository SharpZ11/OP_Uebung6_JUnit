package gui.sportartikel;

import business.baelle.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.*;
import ownUtil.MeldungsfensterAnzeiger;
import ownUtil.*;

public class SportartikelView implements Observer{
	
	private SportartikelControl sportartikelControl;
	private BaelleModel baelleModel;	
	private Stage stage;
	
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane = new  Pane();
    private Label lblAnzeigeTrikots         = new Label("Anzeige Trikots");
    private Label lblAnzeigeBaelle          = new Label("Anzeige Bälle");
    private TextArea txtAnzeigeTrikots      = new TextArea();
    private TextArea txtAnzeigeBaelle       = new TextArea();
  
    //-------Ende Attribute der grafischen Oberflaeche-------
     
    public SportartikelView(SportartikelControl sportartikelControl, 
    	Stage stage, BaelleModel baelleModel){
    	Scene scene = new Scene(this.pane, 640, 340);
    	stage.setScene(scene);
    	stage.setTitle("Anzeige von Sportartikeln");
    	stage.show();
    	this.sportartikelControl = sportartikelControl;
    	this.baelleModel = baelleModel;
    	this.baelleModel.addObserver(this);
    	this.stage = stage;
		this.initKomponenten();
		this.initListener();
   	}
  
    private void initKomponenten(){
    	// Label
 	    Font font = new Font("Arial", 20);
 	    lblAnzeigeTrikots.setLayoutX(20);
        lblAnzeigeTrikots.setLayoutY(40);
        lblAnzeigeTrikots.setFont(font);
        lblAnzeigeTrikots.setStyle("-fx-font-weight: bold;"); 
        lblAnzeigeBaelle.setLayoutX(310);
        lblAnzeigeBaelle.setLayoutY(40);
        lblAnzeigeBaelle.setFont(font);
        lblAnzeigeBaelle.setStyle("-fx-font-weight: bold;"); 
        pane.getChildren().addAll(lblAnzeigeTrikots, lblAnzeigeBaelle);    
      	// Textbereiche	
    	txtAnzeigeTrikots.setEditable(false);
       	txtAnzeigeTrikots.setLayoutX(20);
       	txtAnzeigeTrikots.setLayoutY(90);
       	txtAnzeigeTrikots.setPrefWidth(220);
       	txtAnzeigeTrikots.setPrefHeight(185);
       	txtAnzeigeBaelle.setEditable(false);
       	txtAnzeigeBaelle.setLayoutX(310);
       	txtAnzeigeBaelle.setLayoutY(90);
       	txtAnzeigeBaelle.setPrefWidth(300);
       	txtAnzeigeBaelle.setPrefHeight(185);
        pane.getChildren().add(txtAnzeigeBaelle);        	
    }
    
    private void initListener() {
    	/*
    	stage.setOnCloseRequest(
     		new EventHandler<WindowEvent>() {
        		@Override
        		public void handle(WindowEvent e) {
     				SportartikelView.this.baelleModel
     					.removeObserver(SportartikelView.this);
        		}
        	});
    	*/
    	stage.setOnCloseRequest(
    	    e -> SportartikelView.this.baelleModel
			.removeObserver(SportartikelView.this));        
    }
     
    @Override
    public void update() {
   	   	String text = "";
    	// Ersetzen der for-Schleife durch eine foreach-Schleife ist nur moeglich,
    	// wenn SportartikelListe eine Instanz von Iterable ist.
      	for(int i = 0; i < baelleModel.getBaelle().getAnzahlSportartikel(); i++) {
      		text = text + baelleModel.getBaelle().getSportartikel(i).gibZurueck('|') + "\n";
     	}
    	txtAnzeigeBaelle.setText(text);
    	zeigeInformationsfensterAn("Beachten Sie die Anzeige!");
    }	
   
    private void zeigeInformationsfensterAn(String meldung){
 		new MeldungsfensterAnzeiger(AlertType.INFORMATION,
 			"Information", meldung).zeigeMeldungsfensterAn();
    }	
    

}
