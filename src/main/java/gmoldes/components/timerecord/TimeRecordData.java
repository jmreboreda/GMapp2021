package gmoldes.components.timerecord;

import com.lowagie.text.DocumentException;
import gmoldes.components.ViewLoader;
import gmoldes.domain.dto.ClientDTO;
import gmoldes.domain.dto.TimeRecordCandidateDataDTO;
import gmoldes.forms.TimeRecord;
import gmoldes.manager.ClientManager;
import gmoldes.services.Printer;
import gmoldes.utilities.Utilities;
import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeRecordData extends VBox {

    private static final String TIME_RECORD_FXML = "/fxml/time_record/timerecord_data.fxml";
    private static final Integer FIRST_MONTH_INDEX_IN_MONTHNAME = 0;
    private static final Integer LAST_MONTH_INDEX_IN_MONTHNAME = 11;
    private final BooleanProperty activeButton = new SimpleBooleanProperty(false);

    private Parent parent;

    @FXML
    private ChoiceBox<Utilities.Months> monthName;
    @FXML
    private TextField yearNumber;
    @FXML
    private ChoiceBox<ClientDTO> clientForTimeRecord;
    @FXML
    private TableColumn<TimeRecordCandidateDataDTO, String> employeeFullName;
    @FXML
    private TableColumn<TimeRecordCandidateDataDTO, String> workDayType;
    @FXML
    private TableColumn<TimeRecordCandidateDataDTO, String> hoursByWeek;
    @FXML
    private TableColumn<TimeRecordCandidateDataDTO, String> contractType;
    @FXML
    private TableColumn<TimeRecordCandidateDataDTO, String> dateFrom;
    @FXML
    private TableColumn<TimeRecordCandidateDataDTO, String> dateTo;
    @FXML
    private TableView<TimeRecordCandidateDataDTO> dataByTimeRecord;
    @FXML
    private Button createPDFButton;
    @FXML
    private Button printButton;
    @FXML
    private Button exitButton;

    public TimeRecordData() {
        this.parent = ViewLoader.load(this, TIME_RECORD_FXML);
    }

    @FXML
    public void initialize() {

        clientForTimeRecord.setOnAction(this::onChangeEmployer);
        createPDFButton.disableProperty().bind(BooleanExpression.booleanExpression(this.dataByTimeRecord.getSelectionModel().selectedItemProperty().isNull()));
        createPDFButton.setOnMouseClicked(this::onCreateTimeRecordPDF);
        printButton.disableProperty().bind(BooleanExpression.booleanExpression(this.dataByTimeRecord.getSelectionModel().selectedItemProperty().isNull()));
        printButton.setOnMouseClicked(this::onPrintTimeRecord);
        exitButton.setOnMouseClicked(this::onExit);

        monthName.setItems(FXCollections.observableArrayList(
                Utilities.Months.ENERO,
                Utilities.Months.FEBRERO,
                Utilities.Months.MARZO,
                Utilities.Months.ABRIL,
                Utilities.Months.MAYO,
                Utilities.Months.JUNIO,
                Utilities.Months.JULIO,
                Utilities.Months.AGOSTO,
                Utilities.Months.SEPTIEMBRE,
                Utilities.Months.OCTUBRE,
                Utilities.Months.NOVIEMBRE,
                Utilities.Months.DICIEMBRE
                )
        );

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(localDate.getMonthValue() > LAST_MONTH_INDEX_IN_MONTHNAME){
            monthName.getSelectionModel().select(FIRST_MONTH_INDEX_IN_MONTHNAME);
        }else {
            monthName.getSelectionModel().select(localDate.getMonthValue());
        }
        if(localDate.getMonthValue() > LAST_MONTH_INDEX_IN_MONTHNAME){
            yearNumber.setText(String.valueOf(localDate.getYear() + 1));
        }else {
            yearNumber.setText(String.valueOf(localDate.getYear()));
        }

        employeeFullName.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("employeeFullName"));
        workDayType.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("workDayType"));
        hoursByWeek.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("hoursByWeek"));
        contractType.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("contractType"));
        dateFrom.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("dateFrom"));
        dateTo.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("dateTo"));
        hoursByWeek.setStyle("-fx-alignment: CENTER;");
        dateFrom.setStyle("-fx-alignment: CENTER;");
        dateTo.setStyle("-fx-alignment: CENTER;");

        loadClientForTimeRecord();

//        TimeRecordCandidateDataDTO candidate = new TimeRecordCandidateDataDTO(
//                "Reboreda Barcia, José Manuel",
//                "36.019.653-C",
//                "A tiempo completo",
//                "40,00 horas/semana",
//                "Ordinario",
//                "01-08-2014",
//                "Indefinido");
//
//        refreshData(candidate);

    }

    private void onCreateTimeRecordPDF(MouseEvent event){
        String pathToTimeRecordPDF = createPDF();
        System.out.println("Registro horario creado en ".concat(pathToTimeRecordPDF));
    }

    private void onPrintTimeRecord(MouseEvent event){
        String pathToTimeRecordPDF = createPDF();

        Map<String, String> attributes = new HashMap<>();
        attributes.put("papersize","A4");
        attributes.put("sides", "DUPLEX");
        attributes.put("chromacity","MONOCHROME");
        attributes.put("orientation","LANDSCAPE");

        try {
            Printer.printPDF(pathToTimeRecordPDF, attributes);
            Utilities.deleteFileFromPath(pathToTimeRecordPDF);
        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }

    private void onExit(MouseEvent event){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    private String createPDF(){
        TimeRecordCandidateDataDTO data = dataByTimeRecord.getSelectionModel().getSelectedItem();
        TimeRecord timeRecord = TimeRecord.create()
                .withNameOfMonth(this.monthName.getSelectionModel().getSelectedItem().getMonthName())
                .withYearNumber(this.yearNumber.getText())
                .withEnterpriseName("Colmado de Marujamaría C. B., O")
                .withQuoteAccountCode("36012598712")
                .withEmployeeName(data.getEmployeeFullName())
                .withEmployeeNIF(data.getEmployeeNif())
                .withNumberHoursPerWeek(data.getHoursByWeek())
                .build();

        String pathToTimeRecordPDF = "";
        try {
            pathToTimeRecordPDF = TimeRecord.createPDF(timeRecord);
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }

        return pathToTimeRecordPDF;
    }

    private void onChangeEmployer(ActionEvent event){
        dataByTimeRecord.getItems().clear();

        //refreshData();

    }

    private void loadClientForTimeRecord(){
        ClientManager manager = new ClientManager();
        List<ClientDTO> activeClientList = manager.findAllActiveClientInAlphabeticalOrder();
        ObservableList<ClientDTO> clientDTOS = FXCollections.observableArrayList(activeClientList);
        clientForTimeRecord.setItems(clientDTOS);
    }

    private void refreshData(TimeRecordCandidateDataDTO candidate){
        ObservableList<TimeRecordCandidateDataDTO> candidateObList = FXCollections.observableArrayList(candidate);
        dataByTimeRecord.setItems(candidateObList);
    }
}
