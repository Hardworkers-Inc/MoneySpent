package com.hardworkers.moneyspent.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder
public class Transfer {

    @Id
    Long id;

    String title;

    String description;

    TransferType transferType;

    @Builder.Default
    LocalDateTime dateTime = LocalDateTime.now();

    BigInteger count;

    @MappedCollection
    Set<Tag> tags;

    enum TransferType {
        INCOME,
        SPENT
    }
}
