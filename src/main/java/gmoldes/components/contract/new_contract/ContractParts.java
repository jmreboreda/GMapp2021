package gmoldes.components.contract.new_contract;

import gmoldes.components.ViewLoader;
import gmoldes.components.contract.new_contract.events.SearchEmployersEvent;
import gmoldes.domain.dto.ClientDTO;
import gmoldes.domain.dto.ProvisionalContractDataDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

import java.util.List;


public class ContractParts extends HBox {

    private static final String CONTRACT_PARTS_FXML = "/fxml/new_contract/contract_parts.fxml";
    private EventHandler<SearchEmployersEvent> onEmployerNamePatternChangedEventHandler;

    private Parent parent;

    @FXML
    private ContractParts contractParts;

    @FXML
    private TextField employerName;
    @FXML
    private ListView<ClientDTO> employersNames;
    @FXML
    private TextField employeeName;
    @FXML
    private ListView employeesNames;
    @FXML
    private ChoiceBox cotizationCode;
    @FXML
    private javafx.scene.control.Button newEmployee;


    public ContractParts() {

        this.parent = ViewLoader.load(this, CONTRACT_PARTS_FXML);
    }

    @FXML
    public void initialize() {
        employerName.setOnKeyReleased(this::onEmployerNamePatternChanged);
    }

    private void onEmployerNamePatternChanged(KeyEvent keyEvent) {
        String pattern = employerName.getText();
        final SearchEmployersEvent searchPersonsEvent = new SearchEmployersEvent(pattern);
        onEmployerNamePatternChangedEventHandler.handle(searchPersonsEvent);
    }

    public ProvisionalContractDataDTO getAllData(){

        String employerName = "";
        if(this.employersNames.getSelectionModel().getSelectedItem() != null){
            employerName = this.employersNames.getSelectionModel().getSelectedItem().toString();
        }
        String employeeName = "";
        if(this.employeesNames.getSelectionModel().getSelectedItem() != null){
            employeeName = this.employeesNames.getSelectionModel().getSelectedItem().toString();
        }
        String CCC = "";
        if(this.cotizationCode.getSelectionModel().getSelectedItem() != null){
            CCC = this.cotizationCode.getSelectionModel().getSelectedItem().toString();
        }

        return ProvisionalContractDataDTO.create()
                .withEmployerFullName(employerName)
                .withQuoteAccountCode(CCC)
                .withEmployeeFullName(employeeName)
                .build();
    }

    public void clearEmployersData(){
        employerName.clear();
        if(!employersNames.getItems().isEmpty()) {
            employersNames.getItems().clear();
        }
    }

    public void refreshEmployers(List<ClientDTO> employers){
        if(!employersNames.getItems().isEmpty()) {
            employersNames.getItems().clear();
        }
        ObservableList<ClientDTO> listPersonsWhoMatchPattern = FXCollections.observableList(employers);
        employersNames.getItems().addAll(listPersonsWhoMatchPattern);

//        employersNames.getSelectionModel().selectedItemProperty()
//                .addListener((observable, oldValue, newPersonValue) -> onSelectPerson(newPersonValue));
    }

    public void setOnSearchEmployers(EventHandler<SearchEmployersEvent> handler) {
        this.onEmployerNamePatternChangedEventHandler = handler;
    }
}
