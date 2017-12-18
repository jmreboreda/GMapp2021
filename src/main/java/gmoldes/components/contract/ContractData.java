package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import gmoldes.utilities.Utilities;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;


public class ContractData extends AnchorPane {

    private static final Logger logger = Logger.getLogger(ContractData.class.getSimpleName());
    private static final String CONTRACT_DATA_FXML = "/fxml/contract_data.fxml";

    private Parent parent;

    @FXML
    private DatePicker dateNotification;
    @FXML
    private TextField hourNotification;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;

    public ContractData() {
        this.parent = ViewLoader.load(this, CONTRACT_DATA_FXML);
    }

    @FXML
    private void initialize(){
        logger.info("Initializing contract data fxml ...");
        setupDatePickers();
        setupHourListener();
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

    public ContractData getData(){
        ContractData contractData = new ContractData();
        contractData.setDateNotification(this.getDateNotification());
        contractData.setDateFrom(this.getDateFrom());
        contractData.setDateTo(this.getDateTo());

        return contractData;
    }

    private void setupDatePickers(){
        dateNotification.setConverter(Utilities.converter);
        dateNotification.showWeekNumbersProperty().set(false);
        dateNotification.setEditable(false);
        dateNotification.setValue(LocalDate.now());

        DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("HH:mm");
        hourNotification.setText(hourFormatter.format(LocalTime.now()));

        dateFrom.setConverter(Utilities.converter);
        dateFrom.showWeekNumbersProperty().set(false);
        dateFrom.setEditable(false);

        dateTo.setConverter(Utilities.converter);
        dateTo.showWeekNumbersProperty().set(false);
        dateTo.setEditable(false);
    }

    private void setupHourListener(){
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
}
