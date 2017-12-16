package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class ContractActionComponents extends AnchorPane {

    private static final String CURRENT_CONTRACT_FXML = "/fxml/contract_action_components.fxml";

    private Parent parent;

    @FXML
    private ContractActionComponents actionComponents;
    @FXML
    private Button exitButton;

    public ContractActionComponents() {

        this.parent = ViewLoader.load(this, CURRENT_CONTRACT_FXML);
        exitButton.setOnMouseClicked(this::onExitButton);
    }

    private void onExitButton(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }
}
