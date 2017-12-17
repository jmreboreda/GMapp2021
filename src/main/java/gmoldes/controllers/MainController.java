package gmoldes.controllers;

import gmoldes.components.ViewLoader;
import gmoldes.components.contract.*;
import gmoldes.components.contract.events.ContractDataEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends VBox {

    private static final String MAIN_FXML = "/fxml/contract_main.fxml";

    private Parent parent;

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
    private ContractActionComponents contractActionComponents;


    public MainController() {
        this.parent = ViewLoader.load(this, MAIN_FXML);
    }

    @FXML
    public void initialize() {
        contractActionComponents.setOnOkButton(this::onOkButton);
    }

    private void onOkButton(MouseEvent event){
        ContractData data = contractData.getData();
        System.out.println(event.getSource() + " clicked!\n"
                + data.getDateNotification().getValue() +"\n"
                + data.getDateFrom().getValue() + "\n"
                + data.getDateTo().getValue());
    }
}
