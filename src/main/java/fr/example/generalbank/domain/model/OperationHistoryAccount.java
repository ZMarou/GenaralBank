package fr.example.generalbank.domain.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationHistoryAccount {

    private final List<OperationHistory> operationHistoryList;

    public OperationHistoryAccount() {
        operationHistoryList = new ArrayList<>();
    }

    public void add(OperationEnum operationEnum, Date date, Amount amount, Balance balance) {
        OperationHistory operationHistory = new OperationHistory(operationEnum, date, amount, balance);
        operationHistoryList.add(operationHistory);
    }

    public boolean isEmpty() {
        return operationHistoryList.isEmpty();
    }

    public List<OperationHistory> getOperationHistoryList() {
        return operationHistoryList;
    }
}
