package dungeon.master.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow {

	private static int height = 700;
	private static int width = 700;
	private static Stage stage ;

	private ToolBar tb;
	private static BufferedImage [] sprites ;
	private static int rows = 16;
	private static int cols = 16;
	private static int spriteHeight = 32;
	private static int spriteWidth = 32;
	private static Image murImage, porteOuverteImage, porteFermeeImage, solImage;
	private static Image selectedImage;
	private static Image sortieImage;
	private static ImageView sortieUnique;

	private static ImageView player;
	private static GridPane grille; //grille de StackPane

	public MainWindow(Stage stage) {
		 sprites = new BufferedImage[rows * cols];

		try {

			File f = new File("./images/tiny-Complete-Spritesheet-32x32-fruits.png");
			BufferedImage bigImg = ImageIO.read(f);

			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sprites[(i * cols) + j] = bigImg.getSubimage(j * spriteWidth, i * spriteHeight, spriteWidth, spriteHeight);
			    }
			}

		} catch (IOException e1) {
			e1.printStackTrace();
			stage.close();
		};

		chargerImage();

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

	private static void chargerImage() {
		murImage = SwingFXUtils.toFXImage(sprites[0*cols+8], null);
		porteOuverteImage = SwingFXUtils.toFXImage(sprites[0*cols+4], null);
		porteFermeeImage = SwingFXUtils.toFXImage(sprites[0*cols+7], null);
		solImage = SwingFXUtils.toFXImage(sprites[2*cols+14], null);
		sortieImage = SwingFXUtils.toFXImage(sprites[7*cols+10], null);

	}

	protected static void choosePlayer() {

		HBox hbox = new HBox();
		Scene scene = new Scene(hbox);

		Image player1 = SwingFXUtils.toFXImage(sprites[8*cols+3], null);
		Image player2 = SwingFXUtils.toFXImage(sprites[8*cols], null);

		ImageView iv1 = new ImageView(player1);
		ImageView iv2 = new ImageView(player2);

		hbox.getChildren().add(iv1);
		hbox.getChildren().add(iv2);


		iv1.setOnMouseReleased(e->{
			player=iv1;
			chooseMap();
		});

		iv2.setOnMouseReleased(e->{
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

		HBox hbox = new HBox();
		VBox outils = new VBox();
		VBox mapConst = new VBox();
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		Button valider = new Button("Valider la map");
		Button effacer = new Button("Effacer");
		HBox boutons = new HBox();

		hbox.getChildren().addAll(outils, mapConst);

		vbox.getChildren().addAll(hbox, boutons);

		boutons.getChildren().addAll(effacer, valider);

		ImageView mur = new ImageView(murImage);
		ImageView porteOuverte = new ImageView(porteOuverteImage);
		ImageView porteFermee = new ImageView(porteFermeeImage);
		ImageView sol = new ImageView(solImage);
		ImageView sortie = new ImageView(sortieImage);

		outils.getChildren().addAll(mur, porteOuverte, porteFermee, sol, sortie);

		for(Node n : outils.getChildren()) {
			VBox.setMargin(n, new Insets(10));
		}

		GridPane g = new GridPane();

		g.setLayoutX(480);
		g.setLayoutY(480);

		outils.setPadding(new Insets(50, 0, 0, 0));
		mapConst.setPadding(new Insets(70,10,0,0));

		boutons.setPadding(new Insets(10, 0, 0, 250));

		for(Node n : boutons.getChildren()) {
			HBox.setMargin(n, new Insets(0,100,0,0));
		}

		for(Node n : hbox.getChildren()) {
			HBox.setMargin(n, new Insets(0,100,0,0));
		}

		for(int i=0 ; i<15; i++) {
			Node [] nodes = new Node[15];

			for(int j=0; j<15; j++) {
				nodes[j] = new StackPane(new ImageView(solImage));
			}
			g.addColumn(i, nodes);

		}

		mur.setOnMouseClicked(e->{
			selectedImage = murImage;
		});

		sol.setOnMouseClicked(e->{
			selectedImage = solImage;
		});

		porteOuverte.setOnMouseClicked(e->{
			selectedImage = porteOuverteImage;
		});

		porteFermee.setOnMouseClicked(e->{
			selectedImage = porteFermeeImage;
		});

		sortie.setOnMouseClicked(e->{
			selectedImage = sortieImage;
		});

		for(Node d : g.getChildren()) {
			d.setOnMouseClicked(e->{
				StackPane ko = (StackPane)d;
				ImageView iv ;
				if(ko.getChildren().size()==1) {
					iv = new ImageView(selectedImage);
					ko.getChildren().add(iv);
				}
				else {
					iv = (ImageView)(ko.getChildren().get(1));
					iv.setImage(selectedImage);
				}

				if(selectedImage == sortieImage) {
					if(sortieUnique != null) {
						sortieUnique.setImage(solImage);
					}
					sortieUnique = iv;
				}
			});
		}

		effacer.setOnMouseClicked(e->{
			for(Node d : g.getChildren()) {
				StackPane ko = (StackPane)d;
				if(ko.getChildren().size()==1)
					ko.getChildren().add(new ImageView(solImage));
				else {
					ImageView interne = (ImageView)(ko.getChildren().get(1));
					interne.setImage(solImage);

				}
			}
		});

		mapConst.getChildren().add(g);

		grille = g;

		stage.setScene(scene);

	}

}
