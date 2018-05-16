package dungeon.master.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

public class MainWindow {

	private static int height = 700;
	private static int width = 700;
	private static Stage stage ;
	private static ImageView player;
	private ToolBar tb;

	public MainWindow(Stage stage) {
		Button close = new Button("Fermer");
		tb = new ToolBar(close);
		MainWindow.stage = stage;
		stage.setTitle("Dungeon Master");
		VBox vbox = new VBox();
		Image imageBienvenue = new Image("file:images/imageBienvenue.jpg");
		Scene scene = new Scene(vbox);

		vbox.maxHeight(height);
		vbox.maxWidth(width);

		vbox.getChildren().add(close);

		stage.setHeight(height);
		stage.setWidth(width);
		ImageView iv = new ImageView(imageBienvenue);
		vbox.getChildren().add(iv);


		scene.setOnKeyReleased(e->{
			choosePlayer();
		});

		scene.setOnMouseReleased(e->{
			choosePlayer();
		});

		close.setOnMouseReleased(e->{
			stage.close();
		});

		stage.setScene(scene);
		stage.show();

	}

	protected static void choosePlayer() {

		HBox hbox = new HBox();
		Scene scene = new Scene(hbox);

		String namePlayer1 = "file:images/centaur_brown_f.png" ;
		String namePlayer2 = "file:images/draconian_purple_f.png" ;
		String namePlayer3 = "file:images/naga_lightgreen_f.png" ;

		Image player1 = new Image(namePlayer1);
		Image player2 = new Image(namePlayer2);
		Image player3 = new Image(namePlayer3);

		ImageView iv1 = new ImageView(player1);
		ImageView iv2 = new ImageView(player2);
		ImageView iv3 = new ImageView(player3);

		hbox.getChildren().add(iv1);
		hbox.getChildren().add(iv2);
		hbox.getChildren().add(iv3);

		iv1.setOnMouseReleased(e->{
			player=iv1;
			chooseMap();
		});

		iv2.setOnMouseReleased(e->{
			player=iv1;
			chooseMap();
		});

		iv3.setOnMouseReleased(e->{
			player=iv1;
			chooseMap();
		});


		stage.setScene(scene);

	}

	private static void chooseMap() {

		Button mapSelect = new Button("Selectionner une map");

		Button mapEdit = new Button("Créer une map");

		Button mapAlea = new Button("Map aléatoire");

		HBox hbox = new HBox();

		hbox.getChildren().addAll(mapSelect, mapEdit, mapAlea);

		Scene scene = new Scene(hbox);


		mapEdit.setOnMouseReleased(e->{
			editerMap();
		});

		stage.setScene(scene);

		//stage.close();

	}

	private static void editerMap() {

		String murPath = "file:images/brick_brown2.png";
		String porteOuvertePath = "file:images/dngn_enter_zot_open.png";
		String porteFermeePath = "file:images/dngn_enter_zot_closed.png";
		String solPath = "file:images/grass0-dirt-mix2.png";

		HBox hbox = new HBox();
		VBox outils = new VBox();
		VBox mapConst = new VBox();
		Scene scene = new Scene(hbox);

		hbox.getChildren().addAll(outils, mapConst);

		ImageView mur = new ImageView(murPath);
		ImageView porteOuverte = new ImageView(porteOuvertePath);
		ImageView porteFermee = new ImageView(porteFermeePath);
		ImageView sol = new ImageView(solPath);

		outils.getChildren().addAll(mur, porteOuverte, porteFermee, sol);

		for(Node n : outils.getChildren()) {
			VBox.setMargin(n, new Insets(50));
		}

		GridPane g = new GridPane();

		g.setLayoutX(480);
		g.setLayoutY(480);

		outils.setPadding(new Insets(50, 0, 0, 0));
		mapConst.setPadding(new Insets(70,0,0,0));


		for(int i=0 ; i<15; i++) {
			Node [] nodes = new Node[15];

			for(int j=0; j<15; j++) {
				nodes[j] = new ImageView(solPath);
			}
			g.addColumn(i, nodes);

		}

		mapConst.getChildren().add(g);

		stage.setScene(scene);

	}


}
