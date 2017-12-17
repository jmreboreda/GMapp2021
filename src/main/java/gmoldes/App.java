package gmoldes;


import gmoldes.controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application
{
    public static void main( String[] args )
    {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainController controller = new MainController();
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(controller,720,650));
        primaryStage.show();
    }
}
