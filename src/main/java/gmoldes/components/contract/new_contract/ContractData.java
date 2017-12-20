package gmoldes.components.contract.new_contract;

import gmoldes.components.ViewLoader;
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
    private static final String CONTRACT_DATA_FXML = "/fxml/contract_data.fxml";
    private static final String FULL_WORKDAY = "Completa";

    private Parent parent;

    @FXML
    private DatePicker dateNotification;
    @FXML
    private TextField hourNotification;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private ToggleGroup grContractDuration;
    @FXML
    private ToggleGroup grWorkDay;
    @FXML
    private TextField hoursWorkDay;
    @FXML
    private RadioButton radioButtonUndefinedContractDuration;
    @FXML
    private RadioButton radioButtonTemporalContractDuration;
    @FXML
    private TextField temporalContractDurationDays;

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

    public ContractData getAllData(){
        ContractData contractData = new ContractData();
        contractData.setHourNotification(this.getHourNotification());
        contractData.setDateNotification(this.getDateNotification());
        contractData.setDateFrom(this.getDateFrom());
        contractData.setDateTo(this.getDateTo());

        return contractData;
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
                    temporalContractDurationDays.setText(null);
                    temporalContractDurationDays.setDisable(true);
                }else{
                    dateTo.setDisable(false);
                    temporalContractDurationDays.setDisable(false);
                }
            }
        });
    }

    private void workDayDataControlSetup(){
        grWorkDay.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov,
                                Toggle old_toggle, Toggle new_toggle) {
                if (grWorkDay.getSelectedToggle().toString().contains(FULL_WORKDAY)) {
                    hoursWorkDay.setText(null);
                    hoursWorkDay.setDisable(true);
                }else{
                    hoursWorkDay.setDisable(false);
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
