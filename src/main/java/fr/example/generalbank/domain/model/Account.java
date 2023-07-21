package fr.example.generalbank.domain.model;

import fr.example.generalbank.domain.exception.BusinessException;

import java.util.Date;

public class Account {

    private final OperationHistoryAccount operationHistoryAccount;


    public Account(Amount initAmount, Date date) {
        operationHistoryAccount = new OperationHistoryAccount();
        if (initAmount != null) {
            deposit(initAmount, date);
        }
    }

    public void deposit(Amount amount, Date date) {
        Balance balance = getLastBalance().add(amount);
        this.operationHistoryAccount.add(OperationEnum.DEPOSIT, date, amount, balance);
    }

    public void withdrawal(Amount amount, Date date) throws BusinessException {
        if (getLastBalance().isInsufficientBalance(amount)) {
            throw new BusinessException("Insufficient Balance");
        }
        Balance balance = getLastBalance().subtract(amount);
        operationHistoryAccount.add(OperationEnum.WITHDRAWAL, date, amount, balance);
    }

    public OperationHistoryAccount getOperationHistoryAccount() {
        return operationHistoryAccount;
    }

    public Balance getLastBalance() {
        Balance balance = new Balance();
        if (operationHistoryAccount.isEmpty()) {
            return balance;
        } else {
            operationHistoryAccount.getOperationHistoryList().forEach(operationHistory -> {
                        if (OperationEnum.DEPOSIT.equals(operationHistory.operation())) {
                            balance.add(operationHistory.amount());
                        } else {
                            balance.subtract(operationHistory.amount());
                        }
                    }
            );
        }
        return balance;
    }
}
