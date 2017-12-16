package gmoldes.controllers;

import gmoldes.components.ViewLoader;
import gmoldes.components.contract.ContractHeader;
import gmoldes.components.contract.ContractParts;
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


    public MainController() {
        this.parent = ViewLoader.load(this, MAIN_FXML);
    }
}
