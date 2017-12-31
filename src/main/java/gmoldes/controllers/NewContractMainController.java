package gmoldes.controllers;

import gmoldes.components.ViewLoader;
import gmoldes.components.contract.new_contract.*;
import gmoldes.components.contract.new_contract.events.SearchEmployersEvent;
import gmoldes.domain.dto.ClientDTO;
import gmoldes.domain.dto.ProvisionalContractDataDTO;
import gmoldes.manager.ClientManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.logging.Logger;

public class NewContractMainController extends VBox {


    private static final Logger logger = Logger.getLogger(NewContractMainController.class.getSimpleName());
    private static final String MAIN_FXML = "/fxml/new_contract/contract_main.fxml";

    private ClientManager clientManager = new ClientManager();

    private Parent parent;

    @FXML
    private TabPane tabPane;
    @FXML
    private ContractHeader contractHeader;
    @FXML
    private ContractParts contractParts;
    @FXML
    private ContractData contractData;
    @FXML
    private ContractSchedule contractSchedule;
    @FXML
    private ContractPublicNotes contractPublicNotes;
    @FXML
    private ContractPrivateNotes contractPrivateNotes;
    @FXML
    private ProvisionalContractData provisionalContractData;
    @FXML
    private ContractActionComponents contractActionComponents;


    public NewContractMainController() {
        logger.info("Initilizing Main fxml");
        this.parent = ViewLoader.load(this, MAIN_FXML);
    }

    @FXML
    public void initialize() {
        contractActionComponents.setOnOkButton(this::onOkButton);
        tabPane.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                refreshProvisionalContractData();
            }
        });

        contractParts.setOnSearchEmployers(this::onSearchEmployers);

    }

    private void refreshProvisionalContractData(){
        ProvisionalContractDataDTO contractDataDTO = retrieveProvisionalContractDataDTO();
        provisionalContractData.refreshData(contractDataDTO);
    }

    private void onOkButton(MouseEvent event){
        System.out.println(event.getSource() + " clicked!\n");
    }

    private ProvisionalContractDataDTO retrieveProvisionalContractDataDTO(){
        ProvisionalContractDataDTO partsDTO = contractParts.getAllData();
        ProvisionalContractDataDTO dataDTO = contractData.getAllData();
        dataDTO.setEmployerFullName(partsDTO.getEmployerFullName());
        dataDTO.setEmployeeFullName(partsDTO.getEmployeeFullName());
        dataDTO.setQuoteAccountCode(partsDTO.getQuoteAccountCode());

        return dataDTO;
    }

    private void onSearchEmployers(SearchEmployersEvent searchPersonsEvent){
        String pattern = searchPersonsEvent.getPattern();
        if(pattern == null){
            pattern = "";
        }
        if(pattern.isEmpty()){
            contractParts.clearEmployersData();
            return;
        }
        List<ClientDTO> employers = findClientsByNamePattern(pattern);
        contractParts.refreshEmployers(employers);
    }

    private List<ClientDTO> findClientsByNamePattern(String pattern){
        return clientManager.findAllActiveClientByNamePatternInAlphabeticalOrder(pattern);
    }
}
