package com.hardworkers.moneyspent.transfer;

import com.hardworkers.moneyspent.Transfer.Transfer;
import com.hardworkers.moneyspent.Transfer.TransferValidator;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class to cover {@link TransferValidator}.
 */
public class TransferValidatorTest {

    private static final Long TRANSFER_ID = 1L;
    private static final String TRANSFER_TITLE = "Title";
    private static final String TRANSFER_DESCRIPTION = "Description";
    private static final LocalDateTime TRANSFER_DATETIME = LocalDateTime.now();
    private static final BigDecimal TRANSFER_COUNT = BigDecimal.TEN;

    private static final String PREFIX = "Transfer has validation errors: ";

    @InjectMocks
    private TransferValidator transferValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void validateValidTransfer() {
        // GIVEN
        final Transfer transfer = Transfer.builder()
                .id(TRANSFER_ID)
                .title(TRANSFER_TITLE)
                .description(TRANSFER_DESCRIPTION)
                .dateTime(TRANSFER_DATETIME)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();

        // WHEN
        String message = transferValidator.validate(transfer);

        // THEN
        assertEquals("", message);
    }

    @Test
    public void validateNullTransfer() {
        // WHEN
        String message = transferValidator.validate(null);

        // THEN
        assertEquals(PREFIX + "Transfer cannot be null!", message);
    }

    @Test
    public void validateTitleNull() {
        // GIVEN
        final Transfer transfer = Transfer.builder()
                .id(TRANSFER_ID)
                .description(TRANSFER_DESCRIPTION)
                .dateTime(TRANSFER_DATETIME)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();

        // WHEN
        String message = transferValidator.validate(transfer);

        // THEN
        assertEquals(PREFIX + "Title cannot be null!", message);
    }

    @Test
    public void validateTitleLength() {
        // GIVEN
        final Transfer transfer = Transfer.builder()
                .id(TRANSFER_ID)
                .title(RandomStringUtils.random(51))
                .description(TRANSFER_DESCRIPTION)
                .dateTime(TRANSFER_DATETIME)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();

        // WHEN
        String message = transferValidator.validate(transfer);

        // THEN
        assertEquals(PREFIX + "Length of title must be lower than 50!", message);
    }

    @Test
    public void validateDescriptionLength() {
        // GIVEN
        final Transfer transfer = Transfer.builder()
                .id(TRANSFER_ID)
                .title(TRANSFER_TITLE)
                .description(RandomStringUtils.random(251))
                .dateTime(TRANSFER_DATETIME)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();

        // WHEN
        String message = transferValidator.validate(transfer);

        // THEN
        assertEquals(PREFIX + "Length of description must be lower than 250!", message);
    }

    @Test
    public void validateDateTime() {
        // GIVEN
        final Transfer transfer = Transfer.builder()
                .id(TRANSFER_ID)
                .title(TRANSFER_TITLE)
                .description(TRANSFER_DESCRIPTION)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();

        // WHEN
        String message = transferValidator.validate(transfer);

        // THEN
        assertEquals("", message);
        assertNotNull(transfer.getDateTime());
    }
}
