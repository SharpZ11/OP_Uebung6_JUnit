package gui.baelle;
   
import business.baelle.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ownUtil.*;

public class BaelleView implements Observer{
	  
	private BaelleModel baelleModel;
	private BaelleControl baelleControl;
	
    //---Anfang Attribute der grafischen Oberflaeche---
    private Pane pane     				       = new  Pane();
    private Label lblAenderung  	 	       = new Label("Änderung");
    private Label lblEinkaufsdatum 		       = new Label("Einkaufsdatum:");
    private Label lblMaterial        	       = new Label("Material:");
    private Label lblSportart  	 	           = new Label("Sportart:");
    private Label lblStatus   	               = new Label("Status:");
    private Label lblNutzung  	               = new Label("Nutzung:");
    private Label lblPreis  	               = new Label("Preis:");
    private ComboBox<String> cmbBxEinkaufdatum = new ComboBox<String>();
    private TextField txtMaterial	           = new TextField();
    private TextField txtSportart	           = new TextField();
    private TextField txtStatus	               = new TextField();
    private TextField txtNutzung	           = new TextField();
    private TextField txtPreis	               = new TextField();
    private Button btnAenderungAufnehmen	   = new Button("Änderung aufnehmen"); 
    private MenuBar mnbrMenuLeiste  	       = new MenuBar();
    private Menu mnDatei             	       = new Menu("Datei");
    private MenuItem mnItmCsvImport 	       = new MenuItem("csv-Import");
    //-------Ende Attribute der grafischen Oberflaeche-------
    
    public BaelleView(BaelleControl baelleControl, 
    	Stage primaryStage, BaelleModel baelleModel){
    	Scene scene = new Scene(this.pane, 300, 380);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Verwaltung von Bällen");
    	primaryStage.show();
    	this.baelleControl = baelleControl;
    	this.baelleModel = baelleModel;
    	this.baelleModel.addObserver(this);
		this.initKomponenten();
		this.initListener();
    }
    
    private void initKomponenten(){
    	// Labels
       	lblAenderung.setLayoutX(20);
    	lblAenderung.setLayoutY(40);
    	Font font = new Font("Arial", 24);
    	lblAenderung.setFont(font);
    	lblAenderung.setStyle("-fx-font-weight: bold;"); 
       	lblEinkaufsdatum.setLayoutX(20);
    	lblEinkaufsdatum.setLayoutY(90);
    	lblMaterial.setLayoutX(20);
    	lblMaterial.setLayoutY(130);
    	lblSportart.setLayoutX(20);
    	lblSportart.setLayoutY(170);
    	lblStatus.setLayoutX(20);
    	lblStatus.setLayoutY(210);
    	lblNutzung.setLayoutX(20);
    	lblNutzung.setLayoutY(250);    
     	lblPreis.setLayoutX(20);
    	lblPreis.setLayoutY(290);    	
       	pane.getChildren().addAll(lblAenderung,  
       		lblEinkaufsdatum, lblMaterial, lblSportart,
       		lblStatus, lblNutzung, lblPreis);
    
    	// Textfelder
       	cmbBxEinkaufdatum.setLayoutX(130);
       	cmbBxEinkaufdatum.setLayoutY(90);
      	cmbBxEinkaufdatum.setPrefWidth(150);
    	txtMaterial.setLayoutX(130);
    	txtMaterial.setLayoutY(130);
    	txtSportart.setLayoutX(130);
    	txtSportart.setLayoutY(170);
      	txtStatus.setLayoutX(130);
    	txtStatus.setLayoutY(210);
    	txtNutzung.setLayoutX(130);
    	txtNutzung.setLayoutY(250);
    	txtPreis.setLayoutX(130);
    	txtPreis.setLayoutY(290);
     	pane.getChildren().addAll( 
     			cmbBxEinkaufdatum, txtMaterial, txtSportart,
     		txtStatus, txtNutzung, txtPreis);
         	
        // Buttons
     	btnAenderungAufnehmen.setLayoutX(20);
     	btnAenderungAufnehmen.setLayoutY(340);
        pane.getChildren().add(btnAenderungAufnehmen); 
        
 		// Menu
   	    this.mnbrMenuLeiste.getMenus().add(mnDatei);
  	    this.mnDatei.getItems().add(this.mnItmCsvImport);
   	    pane.getChildren().add(mnbrMenuLeiste);
   }
   
   private void initListener() {
	    /*
	   EventHandler e d= new EventHandler<ActionEvent>();
	    cmbBxEinkaufdatum.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				zeigeBallAn();			
				
			}
	    });
	    */
	    cmbBxEinkaufdatum.setOnAction(
	    	e -> zeigeBallAn());
		/*
	    btnAenderungAufnehmen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	zeigeInformationsfensterAn("Noch nicht implementiert!");
            }
	    });
	    */
	    btnAenderungAufnehmen.setOnAction(
	    	e -> zeigeInformationsfensterAn("Noch nicht implementiert!"));
	    /*
	    mnItmCsvImport.setOnAction(new EventHandler<ActionEvent>() {
	    	@Override
	        public void handle(ActionEvent e) {
	    		baelleControl.leseBaelleAusDatei();
	        } 
        });
        */
	    mnItmCsvImport.setOnAction(
	    	e -> baelleControl.leseBaelleAusDatei());
   }
   
   private void zeigeBallAn() {
		String einkaufsdatum = this.cmbBxEinkaufdatum.getValue();
		if(einkaufsdatum == null) {
			this.zeigeInformationsfensterAn( 
				"Wählen Sie ein Einkaufsdatum aus.");
		}
		else {
			Ball ball = this.baelleModel.gibBall(einkaufsdatum);
			txtMaterial.setText(ball.getMaterial());
			txtSportart.setText(ball.getSportart());     
			txtStatus.setText(ball.getStatus());     
			txtNutzung.setText(ball.getNutzung());     
			txtPreis.setText(ball.getPreis() + "");     
	    }
   }
   
   @Override
   public void update(){
	    cmbBxEinkaufdatum.getItems().clear();
		// Ersetzen der for-Schleife durch eine foreach-Schleife ist nur moeglich,
	    // wenn SportartikelListe eine Instanz von Iterable ist.
	    for(int i = 0; i < baelleModel.getBaelle().getAnzahlSportartikel(); i++) {	
			cmbBxEinkaufdatum.getItems().add(
			    baelleModel.getBaelle().getSportartikel(i).getEinkaufsdatum() + "");
		}  
   }
    
   void zeigeInformationsfensterAn(String meldung){
		new MeldungsfensterAnzeiger(AlertType.INFORMATION,
			"Information", meldung).zeigeMeldungsfensterAn();
   }	

   void zeigeFehlermeldungsfensterAn(String fehlertyp, String meldung){
	   new MeldungsfensterAnzeiger(AlertType.ERROR,
			fehlertyp + " Fehler", meldung).zeigeMeldungsfensterAn();
   }

}
