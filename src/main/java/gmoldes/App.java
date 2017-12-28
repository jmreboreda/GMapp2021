package gmoldes;


import com.lowagie.text.DocumentException;
import gmoldes.controllers.NewContractMainController;
import gmoldes.forms.TimeRecord;
import gmoldes.services.Printer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class App extends Application{

    public static void main( String[] args ){

//        try {
//            createPDForm();
//        } catch (IOException | DocumentException | PrinterException e) {
//            e.printStackTrace();
//        }

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        NewContractMainController controller = new NewContractMainController();
        primaryStage.setResizable(false);
        Scene scene = new Scene(controller,800,825);
        scene.getStylesheets().add(App.class.getResource("/css_stylesheet/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void createPDForm() throws IOException, DocumentException, PrinterException {

        TimeRecord timeRecord = TimeRecord.create()
                .withNameOfMonth("enero")
                .withYearNumber("2018")
                .withEnterpriseName("Calidad y Gestión Medioambiental TRILLIUM S. L.")
                .withQuoteAccountCode("36012598712")
                .withEmployeeName("Millán Bermúdez, María de la Consolación")
                .withEmployeeNIF("35.897.475-H")
                .withNumberHoursPerWeek("24,00")
                .build();

        String pathToTimeRecordPDF = TimeRecord.createPDF(timeRecord);
        Map<String, String> attributes = new HashMap<>();
        attributes.put("papersize","A4");
        attributes.put("sides", "DUPLEX");
        attributes.put("chromacity","MONOCHROME");
        attributes.put("orientation","LANDSCAPE");


        Printer.printPDF(pathToTimeRecordPDF, attributes);
        //Utilities.deleteFileFromPath(pathToPDF);

        System.exit(0);
    }
}
