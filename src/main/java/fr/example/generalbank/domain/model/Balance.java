package fr.example.generalbank.domain.model;

import java.math.BigDecimal;

public class Balance {

    private Amount amount;

    public Balance() {
        amount = new Amount(BigDecimal.ZERO);
    }

    public Balance add(Amount amount) {
        this.amount = new Amount(this.amount.add(amount));
        return this;
    }

    public Balance subtract(Amount amount) {
        this.amount = new Amount(this.amount.subtract(amount));
        return this;
    }

    public boolean isInsufficientBalance(Amount amount) {
        return this.amount.compareTo(amount) < 0;
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public Amount getAmount() {
        return amount;
    }
}
