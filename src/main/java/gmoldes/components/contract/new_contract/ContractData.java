package gmoldes.components.contract.new_contract;

import gmoldes.components.ViewLoader;
import gmoldes.domain.dto.ProvisionalContractDataDTO;
import gmoldes.utilities.Utilities;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.beans.value.ChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;


public class ContractData extends AnchorPane {

    private static final Logger logger = Logger.getLogger(ContractData.class.getSimpleName());
    private static final String CONTRACT_DATA_FXML = "/fxml/new_contract/contract_data.fxml";
    private static final String FULL_WORKDAY = "A tiempo completo";
    private static final String PARTIAL_WORKDAY = "A tiempo parcial";


    private static final String UNDEFINED_DURATION = "Indefinido";

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    private Parent parent;

    @FXML
    private DatePicker dateNotification;
    @FXML
    private TextField hourNotification;
    @FXML
    private ChoiceBox contractType;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private ToggleGroup grContractDuration;
    @FXML
    private ToggleGroup grWorkDay;
    @FXML
    private TextField hoursWorkWeek;
    @FXML
    private RadioButton radioButtonUndefinedContractDuration;
    @FXML
    private RadioButton radioButtonTemporalContractDuration;
    @FXML
    private TextField durationDaysContract;
    @FXML
    private RadioButton radioButtonFullWorkDay;
    @FXML
    private RadioButton radioButtonPartialWorkDay;
    @FXML
    private TextField laboralCategory;

    public ContractData() {
        this.parent = ViewLoader.load(this, CONTRACT_DATA_FXML);
    }

    @FXML
    private void initialize(){
        logger.info("Initializing contract data fxml ...");
        init();
    }

    private void init(){
        notificationDateControlSetup();
        contractDurationControlSetup();
        workDayDataControlSetup();
    }

    public DatePicker getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(DatePicker dateNotification) {
        this.dateNotification = dateNotification;
    }

    public TextField getHourNotification() {
        return hourNotification;
    }

    public void setHourNotification(TextField hourNotification) {
        this.hourNotification = hourNotification;
    }

    public ChoiceBox getContractType() {
        return contractType;
    }

    public void setContractType(ChoiceBox contractType) {
        this.contractType = contractType;
    }

    public DatePicker getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(DatePicker dateFrom) {
        this.dateFrom = dateFrom;
    }

    public DatePicker getDateTo() {
        return dateTo;
    }

    public void setDateTo(DatePicker dateTo) {
        this.dateTo = dateTo;
    }

    public TextField getHoursWorkWeek() {
        return hoursWorkWeek;
    }

    public void setHoursWorkWeek(TextField hoursWorkWeek) {
        this.hoursWorkWeek = hoursWorkWeek;
    }

    public String getDurationDaysContract() {
        return durationDaysContract.getText();
    }

    public void setDurationDaysContract(String durationDaysContract) {
        this.durationDaysContract.setText(durationDaysContract + " días");
    }

    public TextField getLaboralCategory() {
        return laboralCategory;
    }

    public void setLaboralCategory(TextField laboralCategory) {
        this.laboralCategory = laboralCategory;
    }

    public ProvisionalContractDataDTO getAllData(){

        String contractType = "";
        if(this.getContractType().getSelectionModel().getSelectedItem() != null){
            contractType = this.getContractType().getSelectionModel().getSelectedItem().toString();
        }

        String dateFrom = "";
        if(this.getDateFrom().getValue() != null){
            dateFrom = this.getDateFrom().getValue().format(formatter);
        }

        String dateTo = "";
        if(this.getDateTo().getValue() != null){
            dateTo = this.getDateTo().getValue().format(formatter);
        }

        String durationContract = "";
        if(this.radioButtonUndefinedContractDuration.isSelected()) {
            if (this.getDateFrom().getValue() != null) {
                durationContract = UNDEFINED_DURATION;
            }
        }

        if(this.radioButtonTemporalContractDuration.isSelected()) {
            if (this.getDateFrom().getValue() != null && this.getDateTo().getValue() != null) {
                Long durationContractCalc = (this.getDateTo().getValue().toEpochDay() - this.getDateFrom().getValue().toEpochDay() + 1);
                durationContract = durationContractCalc.toString();
            }
        }

        String workDayType = "";
        String numberHoursPerWeek = "";
        if(radioButtonFullWorkDay.isSelected()){
            workDayType = FULL_WORKDAY;
            numberHoursPerWeek = "";
        }
        if(radioButtonPartialWorkDay.isSelected()){
            workDayType = PARTIAL_WORKDAY;
            if(this.getHoursWorkWeek().getText() != null){
                numberHoursPerWeek = this.hoursWorkWeek.getText();
            }
        }

        String laboralCategory = "";
        if(this.getLaboralCategory().getText() != null){
            laboralCategory = this.getLaboralCategory().getText();
        }

        return ProvisionalContractDataDTO.create()
                .withContractType(contractType)
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withDurationDays(durationContract)
                .withWorkDayType(workDayType)
                .withNumberHoursPerWeek(numberHoursPerWeek)
                .withDaysWeekToWork("")
                .withLaboralCategory(laboralCategory)
                .build();
    }

    private void notificationDateControlSetup(){
        dateNotification.setConverter(Utilities.converter);
        dateNotification.showWeekNumbersProperty().set(false);
        dateNotification.setEditable(false);
        dateNotification.setValue(LocalDate.now());

        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
        hourNotification.setText(hourFormatter.format(LocalTime.now()));

        hourNotificationControlSetup();
    }

    private void contractDurationControlSetup(){
        radioButtonUndefinedContractDuration.setSelected(true);
        dateFrom.setConverter(Utilities.converter);
        dateFrom.showWeekNumbersProperty().set(false);
        dateFrom.setEditable(false);

        dateTo.setConverter(Utilities.converter);
        dateTo.showWeekNumbersProperty().set(false);
        dateTo.setEditable(false);

        grContractDuration.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if(grContractDuration.getSelectedToggle() == radioButtonUndefinedContractDuration){
                    dateTo.setValue(null);
                    dateTo.setDisable(true);
                    durationDaysContract.setText(null);
                    durationDaysContract.setDisable(true);
                }else{
                    dateTo.setDisable(false);
                    durationDaysContract.setDisable(false);
                }
            }
        });
    }

    private void workDayDataControlSetup(){
        grWorkDay.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (radioButtonFullWorkDay.isSelected()) {
                    hoursWorkWeek.setText(null);
                    hoursWorkWeek.setDisable(true);
                }else{
                    hoursWorkWeek.setDisable(false);
                }
            }
        });
    }

    private void hourNotificationControlSetup(){
        hourNotification.focusedProperty().addListener((arg0, oldPropertyValue, newPropertyValue) -> {
            if (!newPropertyValue)
            {
                Date hour = Utilities.verifyHourValue(hourNotification.getText());
                if(hour == null){
                    hourNotification.setText("");
                }else{
                    SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm");
                    hourNotification.setText(hourFormatter.format(hour));
                }
            }
        });
    }

    @Override
    public String toString(){
        return "Fecha notificación: " + this.getDateNotification().getValue() + "\n"
                + "Hora notificación: " + this.getHourNotification().getText() + "\n"
                + "Fecha desde: " + this.getDateFrom().getValue() + "\n"
                + "Fecha hasta: " + this.getDateTo().getValue() + "\n";
    }

}
