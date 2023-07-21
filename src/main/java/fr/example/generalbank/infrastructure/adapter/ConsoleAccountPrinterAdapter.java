package fr.example.generalbank.infrastructure.adapter;

import fr.example.generalbank.domain.model.Account;
import fr.example.generalbank.domain.port.AccountPrinterPort;

import java.text.SimpleDateFormat;

public class ConsoleAccountPrinterAdapter implements AccountPrinterPort {

    private static final String HEADER = "| OPERATION | DATE | AMOUNT | BALANCE |\n";
    private static final String PATTERN = "yyyy-MM-dd HH:mm";

    @Override
    public String print(final Account account) {
        SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
        String line = "| %s | %s | %s | %s |\n";
        String history = account.getOperationHistoryAccount().getOperationHistoryList()
                .stream()
                .map(opHistory -> String.format(line,
                        opHistory.operation().toString(),
                        formatter.format(opHistory.date()),
                        opHistory.amount(),
                        opHistory.balance()))
                .reduce(HEADER, String::concat);
        System.out.print(history);
        return history;
    }
}
