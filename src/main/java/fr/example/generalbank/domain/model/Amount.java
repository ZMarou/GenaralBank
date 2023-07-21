package fr.example.generalbank.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Amount(BigDecimal value) {

    public Amount {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("the value can't be negative");
        }
    }

    public BigDecimal add(Amount amount) {
        return this.value.add(amount.value);
    }

    public BigDecimal subtract(Amount amount) {
        return this.value.subtract(amount.value);
    }

    public int compareTo(Amount amount) {
        return value.compareTo(amount.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount amount)) return false;
        return value.equals(amount.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
