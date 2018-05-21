package dungeon.master.ui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dungeon.master.components.Environment;
import dungeon.master.components.Player;
import dungeon.master.enumerations.Command;
import dungeon.master.enumerations.Dir;
import dungeon.master.ui.implementations.EnvironmentMouvements;
import dungeon.master.ui.implementations.PlayerMouvements;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Border;
import javafx.scene.input.KeyCode;





public class MainWindow {

	private int height = 675+39;
	private int width = 675+16;
	private Stage stage ;

	private ToolBar tb;
	private BufferedImage [] sprites ;
	private int rows = 16;
	private int cols = 16;
	private int spriteHeight = 32;
	private int spriteWidth = 32;
	
	private Image murImage, porteOuverteImage, porteFermeeImage, solImage;
	private Image selectedImage;
	private Image sortieImage;
	private Image pageAccueil;
	private Image arrierePlan;
	private Image choosePlayer;
	private Image playerOneFace, playerOneFaceD, playerOneFaceG, playerOneDroite, playerOneDroiteD,
					playerOneDroiteG, playerOneDerriere, playerOneDerriereG, playerOneDerriereD, playerOneGauche,
					playerOneGaucheD, playerOneGaucheG;
	private Image playerTwoFace, playerTwoFaceD, playerTwoFaceG, playerTwoDroite, playerTwoDroiteD,
	playerTwoDroiteG, playerTwoDerriere, playerTwoDerriereG, playerTwoDerriereD, playerTwoGauche,
	playerTwoGaucheD, playerTwoGaucheG;
	
	private Image createMap;
	private Image selectMap;
	private Image randomMap;
	private Image goBack;
	private Image goBack2;
	private Image clearMap;
	private Image start;
	
	private ImageView sortieUnique;
	
	private String id;
	
	private Background background;


	private List<ImageView> playerMoves;
	private GridPane grille; //grille de StackPane

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
		this.stage = stage;
		stage.setTitle("Dungeon Master");
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);

		vbox.maxHeight(height);
		vbox.maxWidth(width);
		
		//vbox.getChildren().add(close);

		stage.setHeight(height);
		stage.setWidth(width);
		stage.setMaxHeight(height);
		stage.setMaxWidth(width);
		ImageView iv = new ImageView(pageAccueil);
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

	private void chargerImage() {
		murImage = SwingFXUtils.toFXImage(sprites[0*cols+8], null);
		porteOuverteImage = SwingFXUtils.toFXImage(sprites[0*cols+4], null);
		porteFermeeImage = SwingFXUtils.toFXImage(sprites[0*cols+7], null);
		solImage = SwingFXUtils.toFXImage(sprites[2*cols+14], null);
		sortieImage = SwingFXUtils.toFXImage(sprites[7*cols+10], null);
		arrierePlan = new Image("file:images/arriere_plan.png");
		pageAccueil = new Image("file:images/page_accueil.png");
		background = new Background(new BackgroundImage(arrierePlan, null, null, null, null));
		choosePlayer = new Image("file:images/choosePlayer.png");
		
		playerOneFace = SwingFXUtils.toFXImage(sprites[8*cols+3], null);
		playerOneFaceD = SwingFXUtils.toFXImage(sprites[8*cols+4], null);
		playerOneFaceG = SwingFXUtils.toFXImage(sprites[8*cols+5], null);
		playerOneDroite = SwingFXUtils.toFXImage(sprites[9*cols+3], null);
		playerOneDroiteD = SwingFXUtils.toFXImage(sprites[9*cols+4], null);
		playerOneDroiteG = SwingFXUtils.toFXImage(sprites[9*cols+5], null);
		playerOneDerriere = SwingFXUtils.toFXImage(sprites[11*cols+3], null);
		playerOneDerriereD = SwingFXUtils.toFXImage(sprites[11*cols+4], null);
		playerOneDerriereG = SwingFXUtils.toFXImage(sprites[11*cols+5], null);
		playerOneGauche = SwingFXUtils.toFXImage(sprites[10*cols+3], null);
		playerOneGaucheD = SwingFXUtils.toFXImage(sprites[10*cols+4], null);
		playerOneGaucheG = SwingFXUtils.toFXImage(sprites[10*cols+5], null);
		
		
		playerTwoFace = SwingFXUtils.toFXImage(sprites[8*cols], null);
		playerTwoFaceD = SwingFXUtils.toFXImage(sprites[8*cols+1], null);
		playerTwoFaceG = SwingFXUtils.toFXImage(sprites[8*cols+2], null);
		playerTwoDroite = SwingFXUtils.toFXImage(sprites[9*cols], null);
		playerTwoDroiteD = SwingFXUtils.toFXImage(sprites[9*cols+1], null);
		playerTwoDroiteG = SwingFXUtils.toFXImage(sprites[9*cols+2], null);
		playerTwoDerriere = SwingFXUtils.toFXImage(sprites[11*cols], null);
		playerTwoDerriereD = SwingFXUtils.toFXImage(sprites[11*cols+1], null);
		playerTwoDerriereG = SwingFXUtils.toFXImage(sprites[11*cols+2], null);
		playerTwoGauche = SwingFXUtils.toFXImage(sprites[10*cols], null);
		playerTwoGaucheD = SwingFXUtils.toFXImage(sprites[10*cols+1], null);
		playerTwoGaucheG = SwingFXUtils.toFXImage(sprites[10*cols+2], null);
		
		createMap = new Image("file:images/createMap.png");
		selectMap = new Image("file:images/selectMap.png");
		randomMap = new Image("file:images/randomMap.png");
		goBack = new Image("file:images/goBack.png");
		
		clearMap =  new Image("file:images/clearMap.png");
		goBack2 = new Image("file:images/goBack2.png");
		start = new Image("file:images/start.png");
		
	}

	private void choosePlayer() {

		HBox hbox = new HBox();
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		
		Button b1 = new Button();
		Button b2 = new Button();
		
		playerMoves = new ArrayList<>();
		
		//b1.setBackground(new Background(new BackgroundImage(playerOne, null, null, null,null)));
		
		ImageView iv1 = new ImageView(playerOneFace);
		ImageView iv2 = new ImageView(playerTwoFace);
		ImageView choose = new ImageView(choosePlayer);
		//b.setClip(iv1);
		b1.setGraphic(iv1);
		b2.setGraphic(iv2);

		vbox.setBackground(background);
		
		hbox.getChildren().add(b1);
		hbox.getChildren().add(b2);
		HBox.setMargin(b1, new Insets(50, 200, 0, 0));
		HBox.setMargin(b2, new Insets(50, 250, 0, 0));
		b1.setPrefHeight(32);
		b1.setPrefWidth(32);
		b2.setPrefHeight(32);
		b2.setPrefWidth(32);
		vbox.getChildren().addAll(choose, hbox);
		VBox.setMargin(hbox, new Insets(0, 0, 0, 190));
		VBox.setMargin(choose, new Insets(80,0,-70,130));
		b1.setOnMouseReleased(e->{
			playerMoves.add(iv1);
			playerMoves.add(new ImageView(playerOneFaceG));
			playerMoves.add(new ImageView(playerOneFaceD));
			playerMoves.add(new ImageView(playerOneDroite));
			playerMoves.add(new ImageView(playerOneDroiteD));
			playerMoves.add(new ImageView(playerOneDroiteG));
			playerMoves.add(new ImageView(playerOneDerriere));
			playerMoves.add(new ImageView(playerOneDerriereD));
			playerMoves.add(new ImageView(playerOneDerriereG));
			playerMoves.add(new ImageView(playerOneGauche));
			playerMoves.add(new ImageView(playerOneGaucheG));
			playerMoves.add(new ImageView(playerOneGaucheD));
			chooseMap();
		});

		b2.setOnMouseReleased(e->{
			playerMoves.add(iv2);
			playerMoves.add(new ImageView(playerTwoFaceG));
			playerMoves.add(new ImageView(playerTwoFaceD));
			playerMoves.add(new ImageView(playerTwoDroite));
			playerMoves.add(new ImageView(playerTwoDroiteD));
			playerMoves.add(new ImageView(playerTwoDroiteG));
			playerMoves.add(new ImageView(playerTwoDerriere));
			playerMoves.add(new ImageView(playerTwoDerriereD));
			playerMoves.add(new ImageView(playerTwoDerriereG));
			playerMoves.add(new ImageView(playerTwoGauche));
			playerMoves.add(new ImageView(playerTwoGaucheG));
			playerMoves.add(new ImageView(playerTwoGaucheD));
			chooseMap();
		});


		stage.setScene(scene);

	}

	private void chooseMap() {

		Button mapSelect = new Button();

		Button mapEdit = new Button();

		Button mapAlea = new Button();
		
		Button back = new Button();
		
		mapSelect.setGraphic(new ImageView(selectMap));
		mapEdit.setGraphic(new ImageView(createMap));
		mapAlea.setGraphic(new ImageView(randomMap));
		back.setGraphic(new ImageView(goBack));

		HBox hbox = new HBox();
		
		hbox.setBackground(background);

		hbox.getChildren().addAll(mapSelect, mapEdit, mapAlea, back);
		
		for(Node n : hbox.getChildren()){
			((Button)n).setMaxSize(50, 50);
		}

		hbox.setPadding(new Insets(300, 0, 0, 0));
		
		for(Node n : hbox.getChildren())
			HBox.setMargin(n, new Insets(0,0,0, 40));
		
		Scene scene = new Scene(hbox);


		mapEdit.setOnMouseReleased(e->{
			editerMap();
		});
		
		back.setOnMouseReleased(e->{
			choosePlayer();
		});

		stage.setScene(scene);

		//stage.close();

	}

	private void editerMap() {

		HBox hbox = new HBox();
		VBox outils = new VBox();
		VBox mapConst = new VBox();
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox);
		Button valider = new Button();
		Button effacer = new Button();
		Button goback = new Button();
		HBox boutons = new HBox();
		
		valider.setGraphic(new ImageView(start));
		effacer.setGraphic(new ImageView(clearMap));
		goback.setGraphic(new ImageView(goBack2));

		hbox.getChildren().addAll(outils, mapConst);

		vbox.getChildren().addAll(hbox, boutons);

		boutons.getChildren().addAll(effacer, valider, goback);

		ImageView murIV = new ImageView(murImage);
		ImageView porteOuverteIV = new ImageView(porteOuverteImage);
		ImageView porteFermeeIV = new ImageView(porteFermeeImage);
		ImageView solIV = new ImageView(solImage);
		ImageView sortieIV = new ImageView(sortieImage);
		
		Button mur = new Button();
		Button porteOuverte = new Button();
		Button porteFermee = new Button();
		Button sol = new Button();
		Button sortie = new Button();
		
		mur.setGraphic(murIV);
		porteOuverte.setGraphic(porteOuverteIV);
		porteFermee.setGraphic(porteFermeeIV);
		sol.setGraphic(solIV);
		sortie.setGraphic(sortieIV);

		outils.getChildren().addAll(mur, porteOuverte, porteFermee, sol, sortie);

		for(Node n : outils.getChildren()) {
			VBox.setMargin(n, new Insets(10));
		}

		GridPane g = new GridPane();

		g.setLayoutX(480);
		g.setLayoutY(480);
		
		outils.setMaxHeight(400);
		
		outils.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, null, null)));
		outils.setPrefHeight(300);
		
		outils.setPadding(new Insets(60, 30, 0, 0));
		mapConst.setPadding(new Insets(70,10,0,0));

		boutons.setPadding(new Insets(10, 0, 0,30));
		
		outils.setBorder(new Border(new BorderStroke(Color.MAROON, BorderStrokeStyle.SOLID,null, BorderStroke.MEDIUM)));

		for(Node n : boutons.getChildren()) {
			HBox.setMargin(n, new Insets(0,100,0,20));
		}

		for(Node n : hbox.getChildren()) {
			HBox.setMargin(n, new Insets(20,0,10,40));
		}
		
		mapConst.setPadding(new Insets(0,0,20, 0));
		
		HBox.setMargin(g, new Insets(-90,0,0,0));
		
		Background backg = new Background(new BackgroundFill(Color.BLANCHEDALMOND, null,null));
		
		hbox.setBackground(backg);
		vbox.setBackground(backg);

		for(int i=0 ; i<15; i++) {
			Node [] nodes = new Node[15];

			for(int j=0; j<15; j++) {
				ImageView iv = new ImageView(solImage);
				nodes[j] = new StackPane(iv);
				iv.setId("EMP");
			}
			g.addColumn(i, nodes);

		}

		mur.setOnMouseClicked(e->{
			selectedImage = murImage;
			id = "WLL";
		});

		sol.setOnMouseClicked(e->{
			selectedImage = solImage;
			id = "EMP";
		});

		porteOuverte.setOnMouseClicked(e->{
			selectedImage = porteOuverteImage;
			id = "OD";
		});

		porteFermee.setOnMouseClicked(e->{
			selectedImage = porteFermeeImage;
			id = "CD";
		});

		sortie.setOnMouseClicked(e->{
			selectedImage = sortieImage;
			id = "OUT";
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
				
				iv.setId(id);
				
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
				ImageView iv;
				if(ko.getChildren().size()==1){
					iv = new ImageView(solImage);
					ko.getChildren().add(iv);
				}
				else {
					iv = (ImageView)(ko.getChildren().get(1));
					iv.setImage(solImage);
				}
				iv.setId(id);
			}
		});
		
		goback.setOnMouseReleased(e->{
			chooseMap();
		});
		
		valider.setOnMouseReleased(e->{
			gameStart();
		});

		mapConst.getChildren().add(g);

		grille = g;
		stage.setScene(scene);

	}

	private void gameStart() {
		
		HBox hbox = new HBox();
		Scene scene = new Scene(hbox);
		VBox outils = new VBox();
		VBox map = new VBox(grille);
		
		hbox.getChildren().addAll(outils, map);
		
		PlayerMouvements pm = new PlayerMouvements(new Player(), playerMoves);
		EnvironmentMouvements em = new EnvironmentMouvements(new Environment(), grille);
		
		em.init(15, 15);
		pm.init(em, 0, 0, Dir.E);
		
		scene.setOnKeyPressed(e->{
			if(e.getCode()== KeyCode.DOWN){
				
				switch(pm.getFace()){
					case E:
						pm.setLastCom(Command.BB);
						break;
					case W :
						pm.setLastCom(Command.BB);
						break;
					case N :
						pm.setLastCom(Command.RR);
						break;
					case S :
						pm.setLastCom(Command.LL);
						break;
				}
				
				
				pm.step();
			}
			if(e.getCode()== KeyCode.UP){
				switch(pm.getFace()){
				case E:
					pm.setLastCom(Command.FF);
					break;
				case W :
					pm.setLastCom(Command.FF);
					break;
				case N :
					pm.setLastCom(Command.LL);
					break;
				case S :
					pm.setLastCom(Command.RR);
					break;
				}
				pm.step();
			}
			
			if(e.getCode()== KeyCode.RIGHT){
				switch(pm.getFace()){
				case E:
					pm.setLastCom(Command.LL);
					break;
				case W :
					pm.setLastCom(Command.RR);
					break;
				case N :
					pm.setLastCom(Command.FF);
					break;
				case S :
					pm.setLastCom(Command.BB);
					break;
				}

				pm.step();
			}
			
			if(e.getCode()== KeyCode.LEFT){
				switch(pm.getFace()){
				case E:
					pm.setLastCom(Command.RR);
					break;
				case W :
					pm.setLastCom(Command.LL);
					break;
				case N :
					pm.setLastCom(Command.BB);
					break;
				case S :
					pm.setLastCom(Command.FF);
					break;
				}
				pm.step();
			}
			
			if(e.getCode()== KeyCode.S){
				pm.setLastCom(Command.TL);
				pm.step();
			}
			
			if(e.getCode()== KeyCode.D){
				pm.setLastCom(Command.TR);
				pm.step();
			}
			
		});
		
		stage.setScene(scene);
		
	}

}
