package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;

public class ContractParts extends HBox {

    private static final String CONTRACT_PARTS_FXML = "/fxml/employer_employee.fxml";

    private Parent parent;

    @FXML
    private ContractParts contractParts;

    public ContractParts() {
        this.parent = ViewLoader.load(this, CONTRACT_PARTS_FXML);
    }
}
