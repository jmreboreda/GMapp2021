package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ContractHeader extends HBox {

    private static final String CONTRACT_HEADER_FXML = "/fxml/contract_header.fxml";

    private Parent parent;

    public ContractHeader() {
        this.parent = ViewLoader.load(this, CONTRACT_HEADER_FXML);
    }

}
