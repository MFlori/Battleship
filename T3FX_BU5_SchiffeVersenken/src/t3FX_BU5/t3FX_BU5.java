package t3FX_BU5;

import java.net.URI;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class t3FX_BU5 extends Application {

	static int explCounter =0; //Zählt ob alle Schiff-Felder aufgedeckt wurden
	static int tryCounter = 0; // zählt die Versuche
	
	@Override
	public void start(Stage primaryStage) {
		
		int [][] playground = new Spielfeld().getCoordinates();	
	
		VBox vb = new VBox();
		vb.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		GridPane gp = new GridPane();
		vb.setPadding(new Insets(10));
		Label lb1 = new Label ("Willkommen bei \"Schiffe versenken\"");
		lb1.setStyle("-fx-font: 30 arial;-fx-text-fill: lightblue;");
		Label lb2 = new Label ("finde 3 Schiffe mit einer Größe von 3 Feldern\nMögliche Anordnung: -horizontal oder -vertikal");
		lb2.setStyle("-fx-font: 15 arial;-fx-text-fill: lightblue;");
		lb2.setMinHeight(50);
		Label lb3 = new Label ();
		lb3.setStyle("-fx-font: 30 arial;-fx-text-fill: red;");
		Label lb4 = new Label ();
		lb4.setStyle("-fx-font: 15 arial;-fx-text-fill: lightblue;");
		vb.getChildren().addAll(lb1, lb2, gp, lb3, lb4);
		
		for (int i=0;i<playground.length;i++) {
			for (int j=0;j<playground.length;j++) {
				Button btn = new Button();
				btn.setStyle("-fx-base: lightblue;-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color;-fx-background-insets: 0, 1, 2;");
				btn.setMinSize(70, 70);
				btn.setMaxSize(70, 70);
				btn.setPrefSize(70, 70);
				
				gp.add(btn, i, j);
				int x = i;
				int y = j;

				btn.setOnAction(e -> {
					if(explCounter<9) { //Wenn alle Schiffe gefunden wurden, keine Aktivierung von Buttons mehr möglich
						URI uri;
						if(playground[x][y] == 1) {
							uri = Paths.get(".\\resources\\ship.png").toUri();
							explCounter++;		
							tryCounter++;
							if(explCounter==9) {
								lb3.setText("Gewonnen!");
								lb4.setText("benötigte Versuche:\t "+tryCounter+"\nTreffgenauigkeit:\t\t"+(Math.round((9/(double)tryCounter)*100))+"%");
							}
						}
						else {
							uri = Paths.get(".\\resources\\sea.png").toUri();
							tryCounter++;
						}
						ImageView iv = new ImageView(uri.toString());
						btn.setGraphic(iv);	
						btn.setDisable(true);
						btn.setOpacity(1);
						btn.setFocusTraversable(true);
					}
				});	
			}
		}
		primaryStage.setScene(new Scene(vb, 520, 670));
		primaryStage.setTitle("Schiffe versenken v1.0");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
