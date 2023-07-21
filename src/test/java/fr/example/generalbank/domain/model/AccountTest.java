package fr.example.generalbank.domain.model;

import fr.example.generalbank.domain.exception.BusinessException;
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
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final AccountPrinterPort accountPrinterAdapter = new ConsoleAccountPrinterAdapter();

    @Test
    public void should_init_account_balance_with_10() {
        Account account = new Account(new BigDecimal(10), now);

        Balance expectedBalance = new Balance(new BigDecimal(10));
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_throw_exception_with_negative_amount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Amount(new BigDecimal(-10)));
        assertEquals("the value can't be negative", exception.getMessage());
    }

    @Test
    public void should_deposit_account_balance_with_10() {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(null, now);
        account.deposit(amount, now);

        Balance expectedBalance = new Balance(new BigDecimal(10));
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_withdrawal_account_balance_with_10() throws BusinessException {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(new BigDecimal(10), now);
        account.withdrawal(amount, now);

        Balance expectedBalance = new Balance(new BigDecimal(0));
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_throw_exception_when_withdrawal_account_balance_with_10() {
        Amount amount = new Amount(new BigDecimal(10));
        Account account = new Account(null, now);

        BusinessException exception = assertThrows(BusinessException.class, () -> account.withdrawal(amount, now));
        assertEquals("Insufficient Balance", exception.getMessage());
    }

    @Test
    public void should_display_0_operation_history() {
        Date now = new Date();
        Account account = new Account(null, now);

        String expectedHistory = "| OPERATION | DATE | AMOUNT | BALANCE |\n";
        assertEquals(expectedHistory, account.print(accountPrinterAdapter));
    }

    @Test
    public void should_display_2_operations_history() throws BusinessException {
        Account account = new Account(null, now);
        Amount amount = new Amount(new BigDecimal("50.7"));
        Amount amount2 = new Amount(new BigDecimal(10));
        account.deposit(amount, now);
        account.withdrawal(amount2, now);

        String expectedHistory = "| OPERATION | DATE | AMOUNT | BALANCE |\n" +
                "| DEPOSIT | " + FORMATTER.format(now) + " | 50.7 | 50.7 |\n" +
                "| WITHDRAWAL | " + FORMATTER.format(now) + " | 10 | 40.7 |\n";
        assertEquals(expectedHistory, account.print(accountPrinterAdapter));
    }
}
