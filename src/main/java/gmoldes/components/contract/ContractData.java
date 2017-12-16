package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ContractData extends AnchorPane {

    private static final String CONTRACT_DATA_FXML = "/fxml/contract_data.fxml";

    private Parent parent;

    @FXML
    private ContractData contractData;

    public ContractData() {
        this.parent = ViewLoader.load(this, CONTRACT_DATA_FXML);
    }
}
