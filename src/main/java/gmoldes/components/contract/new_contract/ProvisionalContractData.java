package gmoldes.components.contract.new_contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ProvisionalContractData extends AnchorPane {

    private static final String CURRENT_CONTRACT_FXML = "/fxml/provisional_contract_data.fxml";

    private Parent parent;

    @FXML
    private ProvisionalContractData provisionalContractData;

    public ProvisionalContractData() {
        this.parent = ViewLoader.load(this, CURRENT_CONTRACT_FXML);
    }
}
