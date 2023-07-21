package fr.example.generalbank.domain.model;

import java.util.Date;

public record OperationHistory(OperationEnum operation,
                               Date date,
                               Amount amount,
                               Balance balance) {
}
