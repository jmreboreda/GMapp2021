package gmoldes.components.contract;

import gmoldes.components.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ContractSchedule extends AnchorPane {

    private static final String SCHEDULE_FXML = "/fxml/contratct_schedule.fxml";

    private Parent parent;

    @FXML
    private ContractSchedule contractSchedule;

    public ContractSchedule() {
        this.parent = ViewLoader.load(this, SCHEDULE_FXML);
    }
}
