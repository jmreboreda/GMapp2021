package gmoldes.components.contract.new_contract;

import gmoldes.components.ViewLoader;
import gmoldes.domain.dto.ProvisionalContractDataDTO;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ProvisionalContractData extends AnchorPane {

    private static final String CURRENT_CONTRACT_FXML = "/fxml/new_contract/provisional_contract_data.fxml";

    private Parent parent;

    @FXML
    private Label employerName;
    @FXML
    private Label employeeName;
    @FXML
    private Label cotizationCode;
    @FXML
    private Label contractType;
    @FXML
    private Label dateFrom;
    @FXML
    private Label dateTo;
    @FXML
    private Label numDaysContract;
    @FXML
    private Label workDayType;
    @FXML
    private Label numberHoursPerWeek;
    @FXML
    private Label laboralCategory;


    @FXML
    private ProvisionalContractData provisionalContractData;


    public ProvisionalContractData() {
        this.parent = ViewLoader.load(this, CURRENT_CONTRACT_FXML);
    }

    public void refreshData(ProvisionalContractDataDTO contractDataDTO){
        this.employerName.setText(contractDataDTO.getEmployerFullName());
        this.employeeName.setText(contractDataDTO.getEmployeeFullName());
        this.cotizationCode.setText(contractDataDTO.getCcc());
        this.contractType.setText(contractDataDTO.getContractType());
        this.dateFrom.setText(contractDataDTO.getDateFrom());
        this.dateTo.setText(contractDataDTO.getDateTo());
        this.numDaysContract.setText(contractDataDTO.getDurationDays());
        this.workDayType.setText(contractDataDTO.getWorkDayType());
        this.numberHoursPerWeek.setText(contractDataDTO.getNumberHoursPerWeek());
        this.laboralCategory.setText(contractDataDTO.getLaboralCategory());


    }
}
