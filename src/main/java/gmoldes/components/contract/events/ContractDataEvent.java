package gmoldes.components.contract.events;

import gmoldes.components.contract.ContractData;
import javafx.event.Event;
import javafx.event.EventType;

public class ContractDataEvent extends Event {

	public static final EventType<ContractDataEvent> CONTRACT_DATA_EVENT = new EventType<>("CONTRACT_DATA_EVENT");
	private final ContractData contractData;

	public ContractDataEvent(ContractData contractData) {
		super(CONTRACT_DATA_EVENT);
		this.contractData = contractData;
	}

	public ContractData getContractData() {
		return contractData;
	}
}
