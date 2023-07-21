package fr.example.generalbank.domain.port;

import fr.example.generalbank.domain.model.OperationHistory;

import java.util.List;

public interface AccountPrinterPort {

    String print(List<OperationHistory> operationHistoryList);
}
