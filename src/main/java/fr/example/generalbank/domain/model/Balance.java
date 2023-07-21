package fr.example.generalbank.domain.model;

import java.math.BigDecimal;

public record Balance(BigDecimal value) {

    Balance add(BigDecimal amount) {
        return new Balance(this.value.add(amount));
    }

    Balance subtract(BigDecimal amount) {
        return new Balance(this.value.subtract(amount));
    }

    boolean isInsufficientBalance(BigDecimal amount) {
        return this.value.compareTo(amount) < 0;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
