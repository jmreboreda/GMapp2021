package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import gmoldes.utilities.Utilities;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class ContractData extends AnchorPane {

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
        setupDatePickers();
    }

    private void setupDatePickers(){
        Utilities utilities = new Utilities();
        dateFrom.setConverter(utilities.converter);
        dateFrom.showWeekNumbersProperty().set(false);
        dateTo.setConverter(utilities.converter);
        dateTo.showWeekNumbersProperty().set(false);
        dateNotification.setConverter(utilities.converter);
        dateNotification.showWeekNumbersProperty().set(false);
        //dateNotification.setValue(LocalDate.now());
    }
}
