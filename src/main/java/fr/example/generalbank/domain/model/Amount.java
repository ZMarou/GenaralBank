package fr.example.generalbank.domain.model;

import java.math.BigDecimal;

public record Amount(BigDecimal value) {
    public Amount {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("the value can't be negative");
        }
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
