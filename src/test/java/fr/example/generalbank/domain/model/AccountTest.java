package fr.example.generalbank.domain.model;

import fr.example.generalbank.domain.exception.BusinessException;
import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.model.Amount;
import fr.example.generalbank.domain.port.AccountPrinterPort;
import fr.example.generalbank.infrastructure.adapter.ConsoleAccountPrinterAdapter;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class AccountTest {

    private static final Date now = new Date();

    @Test
    public void should_init_account_balance_with_10() {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(amount, now);

        assertEquals(amount, account.getLastBalance().getAmount());
    }

    @Test
    public void should_deposit_account_balance_with_10() {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(null, now);
        account.deposit(amount, now);

        assertEquals(amount, account.getLastBalance().getAmount());
    }

    @Test
    public void should_withdrawal_account_balance_with_10() throws BusinessException {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(amount, now);
        account.withdrawal(amount, now);

        Amount expectedAmount = new Amount(BigDecimal.ZERO);
        assertEquals(expectedAmount, account.getLastBalance().getAmount());
    }

    @Test
    public void should_throw_exception_when_withdrawal_account_balance_with_10() {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(null, now);

        BusinessException exception = assertThrows(BusinessException.class, () -> account.withdrawal(amount, now));
        assertEquals("Insufficient Balance", exception.getMessage());
    }
}
