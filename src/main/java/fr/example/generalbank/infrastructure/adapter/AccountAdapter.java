package fr.example.generalbank.infrastructure.adapter;

import fr.example.generalbank.domain.exception.BusinessException;
import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.model.Amount;
import fr.example.generalbank.domain.port.AccountPort;

import java.util.Date;

public class AccountAdapter implements AccountPort {

    @Override
    public Account deposit(Account account, Amount amount, Date date) {
        account.deposit(amount, date);
        return account;
    }

    @Override
    public Account withdrawal(Account account, Amount amount, Date date) {
        try {
            account.withdrawal(amount, date);
            return account;
        } catch (BusinessException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
