package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


public class ContractActionComponents extends AnchorPane {

    private static final String CURRENT_CONTRACT_FXML = "/fxml/contract_action_components.fxml";

    private Parent parent;

    private EventHandler<MouseEvent> OkButtonEventHandler;

    @FXML
    private Button OkButton;
    @FXML
    private Button exitButton;

    public ContractActionComponents() {

        this.parent = ViewLoader.load(this, CURRENT_CONTRACT_FXML);
        OkButton.setOnMouseClicked(this::onOkButton);
        exitButton.setOnMouseClicked(this::onExitButton);
    }

    private void onOkButton(MouseEvent event){
        this.OkButtonEventHandler.handle(event);
    }

    private void onExitButton(MouseEvent event){
        Platform.exit();
        System.exit(0);
    }

    public void setOnOkButton(EventHandler<MouseEvent> OkButtonEventHandler){
        this.OkButtonEventHandler = OkButtonEventHandler;

    }
}
