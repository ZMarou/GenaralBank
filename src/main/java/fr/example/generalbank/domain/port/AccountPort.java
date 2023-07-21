package fr.example.generalbank.domain.port;

import fr.example.generalbank.domain.exception.BusinessException;
import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.model.Amount;

import java.util.Date;

public interface AccountPort {

    Account deposit(Account account, Amount amount, Date date) ;
    Account withdrawal(Account account, Amount amount, Date date) throws BusinessException;
}
