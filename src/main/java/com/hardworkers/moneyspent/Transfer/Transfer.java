package com.hardworkers.moneyspent.Transfer;

import com.hardworkers.moneyspent.Tag.Tag;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    @NotNull
    private BigInteger count;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Tag> tags;

    public enum TransferType {
        INCOME,
        SPENT
    }
}
