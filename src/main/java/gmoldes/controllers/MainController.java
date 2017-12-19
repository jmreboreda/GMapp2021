package gmoldes.controllers;

import gmoldes.components.ViewLoader;
import gmoldes.components.contract.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.logging.Logger;

public class MainController extends VBox {


    private static final Logger logger = Logger.getLogger(MainController.class.getSimpleName());
    private static final String MAIN_FXML = "/fxml/contract_main.fxml";

    private Parent parent;

    @FXML
    private TabPane tabPane;
    @FXML
    private ContractHeader contractHeader;
    @FXML
    private ContractParts contractParts;
    @FXML
    private ContractData contractData;
    @FXML
    private ContractSchedule contractSchedule;
    @FXML
    private ContractPublicNotes contractPublicNotes;
    @FXML
    private ContractPrivateNotes contractPrivateNotes;
    @FXML
    private ProvisionalContractData provisionalContractData;
    @FXML
    private ContractActionComponents contractActionComponents;


    public MainController() {
        logger.info("Initilizing Main fxml");
        this.parent = ViewLoader.load(this, MAIN_FXML);
    }

    @FXML
    public void initialize() {
        contractActionComponents.setOnOkButton(this::onOkButton);

        tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                refreshProvisionalContractData();
            }
        });
    }

    private void refreshProvisionalContractData(){
        ContractData contractData = retrieveProvisionalContractData();
        System.out.println("Cambiada pestaña ...");
    }

    private void onOkButton(MouseEvent event){
        ContractData data = retrieveProvisionalContractData();
        System.out.println(event.getSource() + " clicked!\n"
                + data.getDateNotification().getValue() +"\n"
                + data.getDateFrom().getValue() + "\n"
                + data.getDateTo().getValue());
    }

    private ContractData retrieveProvisionalContractData(){
        return contractData.getData();
    }
}
