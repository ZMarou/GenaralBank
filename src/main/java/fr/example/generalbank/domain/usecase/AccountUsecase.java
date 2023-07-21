package fr.example.generalbank.domain.usecase;

import fr.example.generalbank.domain.exception.BusinessException;
import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.model.Amount;
import fr.example.generalbank.domain.port.AccountPort;

import java.util.Date;

public class AccountUsecase {

    private AccountPort accountPort;

    public Account deposit(Account account, Amount amount, Date date) {
        accountPort.deposit(account, amount, date);
        return account;
    }

    public Account withdrawal(Account account, Amount amount, Date date) throws BusinessException {
        accountPort.withdrawal(account, amount, date);
        return account;
    }
}
