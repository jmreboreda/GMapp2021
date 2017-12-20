package gmoldes;


import com.lowagie.text.DocumentException;
import gmoldes.controllers.MainController;
import gmoldes.forms.TimeRecord;
import gmoldes.services.Printer;
import gmoldes.services.TimeRecordPDF;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.IOException;

public class App extends Application{

    public static void main( String[] args ){

        try {
            createPDForm();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        MainController controller = new MainController();
        primaryStage.setResizable(false);
        Scene scene = new Scene(controller,800,825);
        //scene.getStylesheets().add(App.class.getResource("lcd.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void createPDForm() throws IOException, DocumentException {

        String pathToPDF = null;
        PDDocument pdf = null;

        TimeRecord timeRecord = new TimeRecord(
                "septiembre",
                "2021",
                "Millán Bermúdez, María de la Consolación",
                "36012598712",
                "Rodríguez Barbitúrica, María Fernanda",
                "35.897.475-H",
                "24,00"
        );
        pathToPDF = TimeRecordPDF.createPDF(timeRecord);

        try {
            Printer.print(pathToPDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
