package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class CurrentContract extends AnchorPane {

    private static final String CURRENT_CONTRACT_FXML = "/fxml/current_contract.fxml";

    private Parent parent;

    @FXML
    private CurrentContract currentContract;

    public CurrentContract() {
        this.parent = ViewLoader.load(this, CURRENT_CONTRACT_FXML);
    }
}
