package gmoldes;


import com.lowagie.text.DocumentException;
import gmoldes.controllers.NewContractMainController;
import gmoldes.forms.TimeRecord;
import gmoldes.persistence.dao.ClientDAO;
import gmoldes.persistence.vo.ClientVO;
import gmoldes.services.Printer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

        ClientDAO clientDAO = ClientDAO.ClientDAOFactory.getInstance();
        List<ClientVO> clientVOList = clientDAO.findAllClientFromNamePatternInAlphabeticalOrder("a");
        for(ClientVO clientVO : clientVOList) {
            System.out.println(clientVO.getNom_rzsoc() + ", cliente de "  + clientVO.getTipoclte() + ", activo: " + clientVO.getCltactivo());
        }
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
