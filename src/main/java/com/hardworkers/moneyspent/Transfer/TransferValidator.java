package com.hardworkers.moneyspent.Transfer;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TransferValidator {

    private static final String PREFIX = "Transfer has validation errors: ";

    public String validate(Transfer transfer) {
        List<String> errors = new ArrayList<>();

        if (Objects.isNull(transfer)) {
            return PREFIX + "Transfer cannot be null!";
        }

        if (Objects.isNull(transfer.getCount())) {
            errors.add("Count cannot be null");
        } else if (transfer.getCount().compareTo(BigDecimal.ZERO) <= 0) {
            errors.add("Count must be greater than 0");
        }

        if (Objects.isNull(transfer.getTitle())) {
            errors.add("Title cannot be null");
        } else if (transfer.getTitle().length() > 50) {
            errors.add("Length of title must be lower than 50");
        }

        if (Objects.nonNull(transfer.getDescription()) && transfer.getDescription().length() > 250) {
            errors.add("Length of description must be lower than 250");
        }

        if (Objects.isNull(transfer.getDateTime())) {
            transfer.setDateTime(LocalDateTime.now());
        }
        if (errors.size() > 0) {
            return errors.stream().collect(Collectors.joining(", ", PREFIX, "!"));
        }
        return "";
    }
}
