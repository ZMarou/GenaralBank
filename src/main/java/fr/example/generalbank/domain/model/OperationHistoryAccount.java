package fr.example.generalbank.domain.model;

import fr.example.generalbank.domain.port.AccountPrinterPort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationHistoryAccount {

    private final List<OperationHistory> operationHistoryList;

    public OperationHistoryAccount() {
        operationHistoryList = new ArrayList<>();
    }

    void add(OperationEnum operationEnum, Date date, Amount amount, Balance balance) {
        OperationHistory operationHistory = new OperationHistory(operationEnum, date, amount, balance);
        operationHistoryList.add(operationHistory);
    }

    String print(AccountPrinterPort printer){
        return printer.print(operationHistoryList);
    }



}
