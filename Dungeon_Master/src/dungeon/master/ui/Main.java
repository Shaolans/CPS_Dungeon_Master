package dungeon.master.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {


	@Override
	public void start(Stage stage) throws Exception {
		MainWindow bw = new MainWindow(stage);
	}

    public static void main(String[] args) {
    	launch(args);
    }

}
