package gmoldes.components.contract.new_contract;

import gmoldes.components.ViewLoader;
import gmoldes.domain.dto.ProvisionalContractDataDTO;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class ContractParts extends HBox {

    private static final String CONTRACT_PARTS_FXML = "/fxml/new_contract/contract_parts.fxml";

    private Parent parent;

    @FXML
    private ContractParts contractParts;

    @FXML
    private TextField employerName;
    @FXML
    private ListView employersNames;
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
}
