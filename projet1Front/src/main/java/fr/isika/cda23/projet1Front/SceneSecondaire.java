package fr.isika.cda23.projet1Front;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import fr.isika.projet1Front.modele.Stagiaire;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SceneSecondaire {

private Scene scene;
static TableView<Stagiaire> table;
ChargeurArbreBinaire chargeur;
List<Stagiaire> listeStagiaires;
	
	public SceneSecondaire() throws IOException {
		
		BorderPane root = new BorderPane();
		Label gestionnaireDeStagiaire = new Label("Gestionnaire des stagiaires");
		gestionnaireDeStagiaire.setPadding(new Insets(0, 20, 0, 10));
		gestionnaireDeStagiaire.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		gestionnaireDeStagiaire.setTextFill(Color.BURLYWOOD);
		
		ChargeurArbreBinaire chargeur = new ChargeurArbreBinaire();
		List<Stagiaire> listeStagiaires = new ArrayList<>();
		listeStagiaires.addAll(chargeur.getArbreBinaire().afficherArbre());
		
		table = new TableView<Stagiaire>();
		table.setEditable(true);
		
		// Création des 5 colonnes
		TableColumn<Stagiaire, String> nomCol = new TableColumn<Stagiaire, String>("Nom");
		TableColumn<Stagiaire, String> prenomCol = new TableColumn<Stagiaire, String>("Prenom");
		TableColumn<Stagiaire, String> departementCol = new TableColumn<Stagiaire, String>("Departement");
		TableColumn<Stagiaire, String> universiteCol = new TableColumn<Stagiaire, String>("Universite");
		TableColumn<Stagiaire, String> anneeCol = new TableColumn<Stagiaire, String>("Annee");

		nomCol.setMinWidth(150);
		prenomCol.setMinWidth(150);
		departementCol.setMinWidth(150);
		universiteCol.setMinWidth(150);
		anneeCol.setMinWidth(150);

		// Remplissage de chaque colonne de la TableView table avec son attribut
		// correspondant
		nomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));
		departementCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("departement"));
		universiteCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("universite"));
		anneeCol.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("annee"));

		table.getColumns().addAll(nomCol, prenomCol, departementCol, universiteCol, anneeCol);

		// On rempli la table avec la liste observable pour pouvoir l'afficher
		table.setItems(FXCollections.observableList(listeStagiaires));

		VBox vboxGauche = new VBox();
		vboxGauche.setPadding(new Insets(50, 150, 50, 60));
		VBox logo = new VBox();
		// importer l'image du Logo
		Image image1 = new Image(new FileInputStream("W:\\ISIKA\\LOGO2.png"));
		ImageView imageView = new ImageView(image1);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		// imageView.setTranslateZ(200);
		logo.getChildren().add(imageView);
		logo.setPadding(new Insets(0, 0, 40, 0));
		Label lblFormulaireStagiaire = new Label("Formulaire du stagiaire");
		lblFormulaireStagiaire.setPadding(new Insets(0, 20, 0, 10));
		lblFormulaireStagiaire.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		lblFormulaireStagiaire.setTextFill(Color.BURLYWOOD);
		Label espace = new Label("");
		espace.setFont(Font.font(30));
		Label lblNom = new Label("Nom :");
		lblNom.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		lblNom.setPadding(new Insets(10, 0, 5, 0));
		TextField textFieldNom = new TextField();
		textFieldNom.setPromptText("Nom du Stagiaire");
		textFieldNom.setFont(Font.font("Brush Script MT", 15));
		Label lblPrenom = new Label("Prénom :");
		lblPrenom.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		lblPrenom.setPadding(new Insets(10, 0, 5, 0));
		TextField textFieldPrenom = new TextField();
		textFieldPrenom.setPromptText("Prénom du Stagiaire");
		textFieldPrenom.setFont(Font.font("Brush Script MT", 15));
		Label lblPromotion = new Label("Promotion :");
		lblPromotion.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		lblPromotion.setPadding(new Insets(10, 0, 5, 0));
		TextField textFieldPromotion = new TextField();
		textFieldPromotion.setPromptText("Nom de la promotion");
		textFieldPromotion.setFont(Font.font("Brush Script MT", 15));
		Label lblDepartement = new Label("Département :");
		lblDepartement.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		lblDepartement.setPadding(new Insets(10, 0, 5, 0));
		lblDepartement.setTranslateY(25);
		
		ChoiceBox<String> departementBox = new ChoiceBox<>();
		departementBox.setTranslateX(100);
		departementBox.setTranslateZ(35);
		departementBox.getItems().addAll("(01) Ain", "(02) Aisne", "(03) Allier", "(04) Alpes-de\n-Haute-Provence",
				"(05) Hautes-alpes", "(06) Alpes-maritimes", "(07) Ardèche", "(08) Ardennes", "(09) Ariège",
				"(10) Aube", "(11)Aude", "(12) Aveyron", "(13)Bouches-du-Rhône", "(14) Calvados", "(15) Cantal",
				"(16) Charente", "(17) Charente-maritime", "(18) Cher", "(19) Corrèze", "(2A) Corse-du-sud",
				"(2B) Haute-Corse", "(21) Côte-d'Or", "(22) Côtes-d'Armor", "(23) Creuse", "(24) Dordogne",
				"(25) Doubs", "(26) Drôme", "(27) Eure", "(28) Eure-et-loir", "(29) Finistère", "(30) Gard",
				"(31) Haute-garonne", "(32) Gers", "(33) Gironde", "(34) Hérault", "(35) Ille-et-vilaine", "(36) Indre",
				"(38) Isère", "(39) Jura", "(40) Landes", "(41) Loir-et-cher", "(42) Loire", "(43) Haute-loire",
				"(44) Loire-atlantique", "(45) Loiret", "(46) Lot", "(47) Lot-et-garonne", "(48) Lozère",
				"(49) Maine-et-loire", "(50) Manche", "(51) Marne", "(52) Haute-marne", "(53) Mayenne",
				"(54) Meurthe-et-moselle", "(55) Meuse", "(56) Morbihan", "(57) Moselle", "(58) Nièvre", "(59) Nord",
				"(60) Oise", "(61) Orne", "(62) Pas-de-calais", "(63) Puy-de-dôme", "(64) Pyrénées-atlantiques",
				"(65) Hautes-Pyrénées", "(66) Pyrénées-orientales", "(67) Bas-rhin", "(68) Haut-rhin", "(69) Rhône",
				"(70) Haute-saône", "(71) Saône-et-loire", "(72) Sarthe", "(73) Savoie", "(74) Haute-savoie",
				"(75) Paris", "(76) Seine-maritime", "(77) Seine-et-marne", "(78) Yvelines", "(79) Deux-sèvres",
				"(80) Somme", "(81) Tarn", "(82) Tarn-et-Garonne", "(83) Var", "(84) Vaucluse", "(85) Vendée",
				"(86) Vienne", "(87) Haute-vienne", "(88) Vosges", "(89) Yonne", "(90) Territoire de belfort",
				"(91) Essonne", "(92) Hauts-de-seine", "(93) Seine-Saint-Denis", "(94) Val-de-marne", "(95) Val-d'Oise",
				"(971) Guadeloupe", "(972) Martinique", "(973) Guyane", "(974) La réunion", "(976) Mayotte");
		departementBox.getSelectionModel().select(0);
		Label lblDepartemental = new Label();
		departementBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				lblDepartemental.setText(newValue);
			}
		});

		Label lblAnneeScolaire = new Label("Année scolaire :");
		lblAnneeScolaire.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		lblAnneeScolaire.setTranslateY(30);

		// Label label = new Label("Select Level:");
		final Spinner<Integer> spinnerAnneeScolaire = new Spinner<Integer>();
		final int initialValue = 2023;
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1960, 2050,
				initialValue);
		spinnerAnneeScolaire.setValueFactory(valueFactory);
		spinnerAnneeScolaire.setTranslateX(114);
		spinnerAnneeScolaire.setTranslateY(10);
		spinnerAnneeScolaire.setId("spinnerAnneeScolaire");
		
		Button boutonAjouter = new Button("Ajouter Stagiaire");
		boutonAjouter.setFont(Font.font("Brush Script MT", 15));
		boutonAjouter.setTranslateY(70);
		boutonAjouter.setTranslateX(48);
		Button boutonDeconnexion = new Button("Deconnexion");
		boutonDeconnexion.setTranslateX(50);
		boutonDeconnexion.setTranslateZ(200);
		boutonDeconnexion.setFont(Font.font("Brush Script MT", 14));
		boutonDeconnexion.setOnAction(e -> {
			SceneDAccueil loginScene = null;
			try {
				loginScene = new SceneDAccueil();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Stage stage = (Stage) boutonDeconnexion.getScene().getWindow();
			stage.setScene(loginScene.getScene());
		});
		
		Button btnImprimer = new Button("Imprimer");
		btnImprimer.setFont(Font.font("Brush Script MT", 14));
		btnImprimer.setTranslateY(90);
		btnImprimer.setTranslateX(75);
		
		Button btnDocumentation = new Button("Documentation");
		btnDocumentation.setFont(Font.font("Brush Script MT", 14));
		btnDocumentation.setTranslateZ(10);
		btnDocumentation.setTranslateX(1310);
		
		vboxGauche.getChildren().addAll(lblFormulaireStagiaire, espace, lblNom, textFieldNom, lblPrenom,
				textFieldPrenom, lblPromotion, textFieldPromotion, lblDepartement, departementBox, lblAnneeScolaire,
				spinnerAnneeScolaire, boutonAjouter,btnImprimer);
		root.setTop(gestionnaireDeStagiaire);
		root.setCenter(table);
		root.setTop(logo);
		
		root.setRight(boutonDeconnexion);
		root.setBottom(btnDocumentation);
		root.setLeft(vboxGauche);
		root.setPadding(new Insets(30, 80, 130, 0));
		
		scene = new Scene(root, 1450, 750);
		scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	}
	public Scene getScene() {
		return scene;
	}
}
