package fr.example.generalbank.domain.usecase;

import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.port.AccountPrinterPort;

public class AccountHistoryUsecase {

    private AccountPrinterPort accountPrinterPort;

    public void showHistory(Account account) {
        accountPrinterPort.print(account);
    }
}
