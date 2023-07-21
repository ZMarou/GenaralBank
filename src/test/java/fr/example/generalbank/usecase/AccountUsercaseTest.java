package fr.example.generalbank.usecase;

import fr.example.generalbank.domain.exception.BusinessException;
import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.model.Amount;
import fr.example.generalbank.domain.port.AccountPrinterPort;
import fr.example.generalbank.infrastructure.adapter.AccountAdapter;
import fr.example.generalbank.infrastructure.adapter.ConsoleAccountPrinterAdapter;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AccountUsercaseTest {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final Date now = new Date();

    private static final AccountAdapter accountAdapter = new AccountAdapter();

    @Test
    public void should_deposit_an_account() {
        Date now = new Date();
        Account account = new Account(null, now);
        Amount amount = new Amount(BigDecimal.TEN);

        Account actualAccount = accountAdapter.deposit(account, amount, now);
        assertEquals(amount, actualAccount.getLastBalance().getAmount());
    }

    @Test
    public void should_withdrawal_an_account() {
        Date now = new Date();
        Amount initAmount = new Amount(new BigDecimal(12));
        Account account = new Account(initAmount, now);
        Amount amount = new Amount(BigDecimal.TEN);

        Account actualAccount = accountAdapter.withdrawal(account, amount, now);
        Amount expectedAmount = new Amount(new BigDecimal(2));
        assertEquals(expectedAmount, actualAccount.getLastBalance().getAmount());
    }

}
