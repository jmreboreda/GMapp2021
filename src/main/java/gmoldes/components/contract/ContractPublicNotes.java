package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ContractPublicNotes extends AnchorPane {

    private static final String PUBLIC_NOTES_FXML = "/fxml/contract_public_notes.fxml";

    private Parent parent;

    @FXML
    private ContractPublicNotes publicNotes;

    public ContractPublicNotes() {
        this.parent = ViewLoader.load(this, PUBLIC_NOTES_FXML);
    }
}
