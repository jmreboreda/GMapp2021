package gmoldes;


import com.lowagie.text.DocumentException;
import gmoldes.controllers.NewContractMainController;
import gmoldes.domain.dto.ClientDTO;
import gmoldes.forms.TimeRecord;
import gmoldes.manager.ClientManager;
import gmoldes.persistence.dao.ClientDAO;
import gmoldes.persistence.vo.ClientVO;
import gmoldes.persistence.vo.ServiceGMVO;
import gmoldes.services.Printer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

//        Set<ServiceGMVO> serviceGMVOSet = new HashSet<>();
//
//        ClientVO clientVO = new ClientVO();
//        clientVO.setIdcliente(101);
//        clientVO.setNom_rzsoc("Cliente de prueba2");
//
//        ServiceGMVO serviceGMVO= new ServiceGMVO();
//        serviceGMVO.setDateFrom(new Date());
//        serviceGMVO.setService("Asesoría");
//        serviceGMVO.setClientVO(clientVO);
//        serviceGMVOSet.add(serviceGMVO);
//
//        ServiceGMVO serviceGMVO1= new ServiceGMVO();
//        serviceGMVO1.setDateFrom(new Date());
//        serviceGMVO1.setService("Alquileres");
//        serviceGMVO1.setClientVO(clientVO);
//        serviceGMVOSet.add(serviceGMVO1);
//        clientVO.setServicesGM(serviceGMVOSet);
//
//        ClientDAO clientDAO = ClientDAO.ClientDAOFactory.getInstance();
//        Integer idNewClient = clientDAO.createClient(clientVO);


    }

    private static void createPDForm() throws IOException, DocumentException, PrinterException {

        TimeRecord timeRecord = TimeRecord.create()
                .withNameOfMonth("enero")
                .withYearNumber("2018")
                .withEnterpriseName("Colmado de Marujamaría C. B., O")
                .withQuoteAccountCode("36012598712")
                .withEmployeeName("Núñez Ferradás, Saleta")
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
        //Utilities.deleteFileFromPath(pathToTimeRecordPDF);

        System.exit(0);
    }
}
