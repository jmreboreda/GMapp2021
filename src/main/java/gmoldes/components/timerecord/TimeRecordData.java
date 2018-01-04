package gmoldes.components.timerecord;

import com.lowagie.text.DocumentException;
import gmoldes.components.ViewLoader;
import gmoldes.domain.dto.TimeRecordCandidateDataDTO;
import gmoldes.forms.TimeRecord;
import gmoldes.services.Printer;
import gmoldes.utilities.Utilities;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.awt.print.PrinterException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeRecordData extends VBox {

    private static final String TIME_RECORD_FXML = "/fxml/time_record/timerecord_data.fxml";

    private Parent parent;

    @FXML
    private ChoiceBox<Utilities.Months> nameOfMonth;
    @FXML
    private TextField yearNumber;
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
        createPDFButton.setOnMouseClicked(this::onCreateTimeRecordPDF);
        printButton.setOnMouseClicked(this::onPrintTimeRecord);
        exitButton.setOnMouseClicked(this::onExit);

        nameOfMonth.setItems(FXCollections.observableArrayList(
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
        nameOfMonth.getSelectionModel().select(localDate.getMonthValue());
        yearNumber.setText(String.valueOf(localDate.getYear()));

        employeeFullName.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("employeeFullName"));
        workDayType.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("workDayType"));
        hoursByWeek.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("hoursByWeek"));
        contractType.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("contractType"));
        dateFrom.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("dateFrom"));
        dateTo.setCellValueFactory(new PropertyValueFactory<TimeRecordCandidateDataDTO,String>("dateTo"));
        dateFrom.setStyle("-fx-alignment: CENTER;");
        dateTo.setStyle("-fx-alignment: CENTER;");

        TimeRecordCandidateDataDTO candidate = new TimeRecordCandidateDataDTO(
                "Millán Bermúdez, María Consolación",
                "A tiempo parcial",
                "20,00 horas/semana",
                "De interinidad [sustitución por riesgo para el embarazo]",
                "01-12-2004",
                "Indefinido");

        ObservableList<TimeRecordCandidateDataDTO> candidateObList = FXCollections.observableArrayList(candidate);
        dataByTimeRecord.setItems(candidateObList);

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

    private String createPDF(){
        TimeRecordCandidateDataDTO data = dataByTimeRecord.getSelectionModel().getSelectedItem();
        TimeRecord timeRecord = TimeRecord.create()
                .withNameOfMonth(this.nameOfMonth.getSelectionModel().getSelectedItem().getMonthName())
                .withYearNumber(this.yearNumber.getText())
                .withEnterpriseName("Colmado de Marujamaría C. B., O")
                .withQuoteAccountCode("36012598712")
                .withEmployeeName(data.getEmployeeFullName())
                .withEmployeeNIF("35.897.475-H")
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

    private void onExit(MouseEvent event){
        Platform.exit();
    }
}
