package gmoldes;


import com.lowagie.text.DocumentException;
import gmoldes.forms.TimeRecord;
import gmoldes.utilities.CreateTimeRecordPDF;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application{

    public static void main( String[] args ){

        createPDForm();

        //launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        MainController controller = new MainController();
//        primaryStage.setResizable(false);
//        Scene scene = new Scene(controller,800,825);
//        //scene.getStylesheets().add(App.class.getResource("lcd.css").toExternalForm());
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    private static void createPDForm(){

        TimeRecord timeRecord = new TimeRecord(
                "septiembre",
                "2021",
                "Millán Bermúdez, María de la Consolación",
                "36012598712",
                "Rodríguez Barbitúrica, María Fernanda",
                "35.897.475-H",
                "24,00"
        );

        try {
            CreateTimeRecordPDF.createPDF(timeRecord);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
