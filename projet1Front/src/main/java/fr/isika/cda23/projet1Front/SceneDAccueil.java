package fr.isika.cda23.projet1Front;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SceneDAccueil {
	private Scene scene;

	public SceneDAccueil() throws IOException {

// Création du BorderPane:
		BorderPane root = new BorderPane();

// Création de la VBox où on va mettre la partie gauche (Logo et Texte):
		VBox maVbox = new VBox();
// Création d'une VBox où on va mettre le Logo:
		VBox logo = new VBox();
		// importer une l'image du Logo
		try {
			Image image = new Image(new FileInputStream("W:\\ISIKA\\LOGO2.png"));
			ImageView imageView = new ImageView(image);

			// Mise en forme du logo(image)
			imageView.setFitHeight(400);
			imageView.setFitWidth(400);
			logo.setPadding(new Insets(0, 50, 0, 150));

			Group grpImage = new Group(imageView);
			logo.getChildren().add(grpImage);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		// Création du Label de la 1ème ligne du texte et sa mise en forme
		Label lblLign1 = new Label("LMWY-TECHNOLOGIE pour les entreprises");
		lblLign1.setPadding(new Insets(0, 50, 0, 40));
		lblLign1.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		lblLign1.setTextFill(Color.BURLYWOOD);
		lblLign1.setId("lblLign1");

		// Création d'une ligne (forme géométrique) sa mise en forme: Amélioration
		// esthétique
		Line ligne = new Line(605, 0, 0, 0);
		ligne.setStroke(Color.ORANGE);

		// Création du Label de la 2ème ligne du texte et sa mise en forme
		Label lblLign2 = new Label("Un LMS léger, rapide, flexible et facile à utiliser");
		lblLign2.setPadding(new Insets(20, 20, 0, 90));
		lblLign2.setFont(Font.font("times new roman", 18));
		lblLign2.setTextFill(Color.BURLYWOOD);

		// Création du Label de la 3ème ligne du texte et sa mise en forme
		Label lblLign3 = new Label(
				"Formez vos employés, partenaires et clients avec le classement n°1 des utilisateurs LMS");
		lblLign3.setPadding(new Insets(10, 10, 10, 90));
		lblLign3.setFont(Font.font("times new roman", 18));
		lblLign3.setTextFill(Color.BURLYWOOD);

//Les ajouter a maVbox par ordre 			
		maVbox.getChildren().add(logo);
		maVbox.getChildren().add(lblLign1);
		maVbox.getChildren().add(ligne);
		maVbox.getChildren().add(lblLign2);
		maVbox.getChildren().add(lblLign3);

//Création de GridePane pour la partie Authentification        
		GridPane authentification = new GridPane();

		// Mise en forme du GridPane

		authentification.setPadding(new Insets(60, 80, 10, 70));
		authentification.setStyle("-fx-background-color : #333333	"); //#333333
		authentification.setAlignment(Pos.CENTER_RIGHT);
		authentification.setVgap(10);
		authentification.setHgap(0);
		

		// Création du Label Authentification et sa mise en forme
		Label lblAuth = new Label("Authentifiez-vous");
		lblAuth.setTextFill(Color.BURLYWOOD);
		lblAuth.setFont(Font.font("times new roman", 25));

		// Création du TextField Identifiant saisi par l'utilisateur et sa mise en forme
		TextField fldLogin = new TextField();
		fldLogin.setFont(Font.font("Brush Scirpt MT", 20));
		fldLogin.setPromptText("Identifiant");
		fldLogin.setPadding(new Insets(0, 0, 0, 10));

		
		// Création du TextField Mot de passe saisi par l'utilisateur et sa mise en forme

		PasswordField fldPassword = new PasswordField();
		fldPassword.setPromptText("Mot de passe");
		fldPassword.setFont(Font.font("Brush Scirpt MT", 20));
		fldPassword.setPadding(new Insets(0, 0, 0, 10));
		
		
		// Créer un Choise-box du poste occupé choisi  par l'utilisateur et sa mise en forme

		Label lblPosteOccupe = new Label("Poste Occupé :");
		lblPosteOccupe.setFont(Font.font("times new roman", 15));
		
		ChoiceBox<String> choicePosteOccupe = new ChoiceBox<>();
		choicePosteOccupe.getItems().addAll("Poste Occupé","Développement commercial", "Encadrant", "Formateur",
				"Gestion Administrative","Management","Responsable Administrative", "Responsable Logistique");
		choicePosteOccupe.getSelectionModel().select(0);
		Label lbl2 = new Label();
		choicePosteOccupe.getSelectionModel().selectedItemProperty().addListener(new
		ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				lbl2.setText(newValue);	
			}
		});
		
	
		// Création du bouton seConnecter et son VBox, sa mise en forme
	
		VBox hbSeConnecter = new VBox();
		Button seConnecter = new Button("Se connecter");
		seConnecter.setFont(Font.font("times new roman", 20));
		
		//Créer l'action qui va se passer derrière ce bouton
		seConnecter.setOnAction(e -> {
			String enteredIdentifiant = fldLogin.getText();
            String enteredPassword = fldPassword.getText();
            String enteredPoste = lbl2.getText();
         //Conditions de connexion   
            if (!enteredIdentifiant.equals("test") || !enteredPassword.equals("admin")) {
            	
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Oups ! Votre login ou votre mot de passe est incorrect.\nVeuillez réessayer svp ou cliquez sur Mot de passe oublié");
                alert.showAndWait();
            }else if(enteredPoste.equals("") || enteredPoste.equals("Poste Occupé") ){
            	
            	Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                alert.setTitle("Erreur");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez choisir un poste SVP");
                alert.showAndWait();
            	
            }else  {
            	
            	if (!enteredPoste.equals("Développement commercial") && !enteredPoste.equals("Encadrant")
            			 && !enteredPoste.equals("Formateur")) {
            			 ScenePrincipale scenePrincipale;
            			 Stage stage = (Stage) seConnecter.getScene().getWindow();
            			 try {
            			 scenePrincipale = new ScenePrincipale();
            			 stage.setScene(scenePrincipale.getScene());
            			 } catch (IOException e1) {
            			 e1.printStackTrace();
            			 }
            			 } else {
            			 SceneSecondaire sceneSecondaire;
            			 Stage stage = (Stage) seConnecter.getScene().getWindow();
            			 try {
            			 sceneSecondaire = new SceneSecondaire();
            			 stage.setScene(sceneSecondaire.getScene());
            			 } catch (IOException e1) {
            			 e1.printStackTrace();
            			 }
            			 }
            }
        });
	

		// Création du Label Mot de passe oublié, sa mise en forme et l' action qui se passe derrière

		Label passOublie = new Label("Mot de passe oublié ?");
		passOublie.setFont(Font.font("Brush Scirpt MT", FontWeight.BOLD, FontPosture.REGULAR, 15));
		passOublie.setUnderline(true);
		passOublie.setTextFill(Color.BURLYWOOD);
		passOublie.setOnMouseClicked((mouseEvent) -> {

			Label recupererPass1 = new Label(
					"Veuillez consulter votre boite mail.\nUn mail de récupération de Mot\nde passe vous a été envoyé.");
			recupererPass1.setFont(Font.font("Brush Scirpt MT", 15));
			recupererPass1.setId("recupererPass1");
			//Ajout de ce label au GridPane(authentification): Action qui se passe que a l'intérieur de le méthode
			authentification.add(recupererPass1, 0, 10);

		});

//Ajouter les fonctionnalitées au GridPane par ordre 
		authentification.add(lblAuth, 0, 1);
		authentification.add(fldLogin, 0, 2);
		authentification.add(fldPassword, 0, 5);
		authentification.add(choicePosteOccupe, 0, 8);
		authentification.add(seConnecter, 0, 14);
		authentification.add(passOublie, 0, 15);

//Ajouter le GridPane(authentification) et la VBox(Logo et texte) dans le BorderPane de la scene			
		root.setRight(authentification);
		root.setLeft(maVbox);

//Création de la scène et sa mise en forme

		scene = new Scene(root, 1300, 700);
		scene.setCursor(Cursor.HAND);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}

	public Scene getScene() {
		return scene;
	}

}
