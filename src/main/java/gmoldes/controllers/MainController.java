package gmoldes.controllers;

import gmoldes.components.ViewLoader;
import gmoldes.components.contract.*;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

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


    public MainController() {
        this.parent = ViewLoader.load(this, MAIN_FXML);
    }
}
