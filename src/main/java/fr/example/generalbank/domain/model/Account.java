package fr.example.generalbank.domain.model;

import fr.example.generalbank.domain.exception.BusinessException;
import fr.example.generalbank.domain.port.AccountPrinterPort;

import java.math.BigDecimal;
import java.util.Date;

public class Account {

    private final OperationHistoryAccount operationHistoryAccount;
    private Balance balance;

    public Account(BigDecimal initAmount, Date date) {
        operationHistoryAccount = new OperationHistoryAccount();
        balance = new Balance(BigDecimal.ZERO);
        if (initAmount != null) {
            deposit(new Amount(initAmount), date);
        }
    }

    public void deposit(Amount amount, Date date) {
        balance = balance.add(amount.value());
        this.operationHistoryAccount.add(OperationEnum.DEPOSIT, date, amount, balance);
    }

    public void withdrawal(Amount amount, Date date) throws BusinessException {
        if (balance.isInsufficientBalance(amount.value())) {
            throw new BusinessException("Insufficient Balance");
        }
        balance = balance.subtract(amount.value());
        operationHistoryAccount.add(OperationEnum.WITHDRAWAL, date, amount, balance);
    }

    public String print(AccountPrinterPort printer){
        return operationHistoryAccount.print(printer);
    }

    public Balance getBalance() {
        return balance;
    }
}
