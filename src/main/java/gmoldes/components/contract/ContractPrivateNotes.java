package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ContractPrivateNotes extends AnchorPane {

    private static final String PRIVATE_NOTES_FXML = "/fxml/contract_private_notes.fxml";

    private Parent parent;

    @FXML
    private ContractPrivateNotes privateNotes;

    public ContractPrivateNotes() {
        this.parent = ViewLoader.load(this, PRIVATE_NOTES_FXML);
    }
}
