package fr.example.generalbank.domain.port;

import fr.example.generalbank.domain.model.Account;

public interface AccountPrinterPort {

    String print(Account account);
}
