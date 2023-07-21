package fr.example.generalbank.usecase;

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

public class AccountHistoryUsercaseTest {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final Date now = new Date();

    private static final AccountPrinterPort accountPrinterAdapter = new ConsoleAccountPrinterAdapter();

    @Test
    public void should_display_0_operation_history() {
        Date now = new Date();
        Account account = new Account(null, now);

        String expectedHistory = "| OPERATION | DATE | AMOUNT | BALANCE |\n";
        assertEquals(expectedHistory, accountPrinterAdapter.print(account));
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
        assertEquals(expectedHistory, accountPrinterAdapter.print(account));
    }

}
