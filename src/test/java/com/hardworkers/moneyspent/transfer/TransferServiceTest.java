package com.hardworkers.moneyspent.transfer;

import com.hardworkers.moneyspent.Transfer.Transfer;
import com.hardworkers.moneyspent.Transfer.TransferRepository;
import com.hardworkers.moneyspent.Transfer.TransferService;
import com.hardworkers.moneyspent.Transfer.TransferValidator;
import com.hardworkers.moneyspent.exceptions.EntityNotFoundException;
import com.hardworkers.moneyspent.exceptions.EntityValidationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class to cover {@link TransferService}.
 */
public class TransferServiceTest {

    private static final Long TRANSFER_ID = 1L;
    private static final String TRANSFER_TITLE = "Title";
    private static final String TRANSFER_DESCRIPTION = "Description";
    private static final LocalDateTime TRANSFER_DATETIME = LocalDateTime.now();
    private static final BigDecimal TRANSFER_COUNT = BigDecimal.TEN;

    @InjectMocks
    private TransferService transferService;

    @Mock
    private TransferRepository transferRepositoryMock;

    @Mock
    private TransferValidator transferValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test case to cover {@link TransferService#getAll()}.
     * Expected to get not empty iterable list.
     */
    @Test
    public void testGetAll() {
        // GIVEN
        when(transferRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(new Transfer()));

        // WHEN
        final Iterable<Transfer> output = transferService.getAll();

        // THEN
        assertTrue(output.iterator().hasNext(),
                "Output lists should not be empty.");

        verify(transferRepositoryMock).findAll();
        verifyNoMoreInteractions(transferRepositoryMock);
    }

    /**
     * Test case to cover {@link TransferService#getById(Long)}.
     * Expected to get not-null {@link Transfer} object.
     */
    @Test
    public void testGetById() {
        // GIVEN
        Transfer transfer = Transfer.builder()
                .id(TRANSFER_ID)
                .title(TRANSFER_TITLE)
                .description(TRANSFER_DESCRIPTION)
                .dateTime(TRANSFER_DATETIME)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();
        when(transferRepositoryMock.findById(TRANSFER_ID)).thenReturn(Optional.of(transfer));

        // WHEN
        Transfer transferFromService = transferService.getById(TRANSFER_ID);

        // THEN
        assertEquals(transfer, transferFromService);

        verify(transferRepositoryMock).findById(TRANSFER_ID);
        verifyNoMoreInteractions(transferRepositoryMock);
    }

    /**
     * Test case to cover {@link TransferService#getById(Long)} with invalid id.
     * Expected to get {@link EntityNotFoundException}.
     */
    @Test
    public void testGetByIdWithException() {
        // GIVEN
        when(transferRepositoryMock.findById(TRANSFER_ID)).thenReturn(Optional.empty());

        // WHEN
        final Executable executable = () -> transferService.getById(TRANSFER_ID);

        // THEN
        assertThrows(EntityNotFoundException.class, executable, "EntityNotFoundException should be thrown.");

        verify(transferRepositoryMock).findById(TRANSFER_ID);
        verifyNoMoreInteractions(transferRepositoryMock);
    }

    /**
     * Test case to cover {@link TransferService#create(Transfer)}.
     * Expected to get not-null {@link Transfer} object.
     */
    @Test
    public void testCreate() {
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
        when(transferRepositoryMock.save(transfer)).thenReturn(transfer);
        when(transferValidator.validate(transfer)).thenReturn("");

        // WHEN
        Transfer transferFromService = transferService.create(transfer);

        // THEN
        assertNotNull(transferFromService, "Output unit should not be null.");
        assertEquals(TRANSFER_ID, transferFromService.getId(), "Incorrect id.");
        assertEquals(TRANSFER_TITLE, transferFromService.getTitle(), "Incorrect output title.");
        assertEquals(TRANSFER_DESCRIPTION, transferFromService.getDescription(), "Incorrect output description.");
        assertEquals(TRANSFER_DATETIME, transferFromService.getDateTime(), "Incorrect output datetime.");
        assertEquals(TRANSFER_COUNT, transferFromService.getCount(), "Incorrect output count.");

        verify(transferRepositoryMock).save(transfer);
        verifyNoMoreInteractions(transferRepositoryMock);
    }

    /**
     * Test case to cover {@link TransferService#update(Transfer)}.
     * Expected to get not-null {@link Transfer} object.
     */
    @Test
    public void testUpdate() {
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
        when(transferRepositoryMock.save(transfer)).thenReturn(transfer);

        // WHEN
        Transfer transferFromService = transferService.update(transfer);

        // THEN
        assertNotNull(transferFromService, "Output unit should not be null.");
        assertEquals(TRANSFER_ID, transferFromService.getId(), "Incorrect id.");
        assertEquals(TRANSFER_TITLE, transferFromService.getTitle(), "Incorrect output title.");
        assertEquals(TRANSFER_DESCRIPTION, transferFromService.getDescription(), "Incorrect output description.");
        assertEquals(TRANSFER_DATETIME, transferFromService.getDateTime(), "Incorrect output datetime.");
        assertEquals(TRANSFER_COUNT, transferFromService.getCount(), "Incorrect output count.");

        verify(transferRepositoryMock).save(transfer);
        verifyNoMoreInteractions(transferRepositoryMock);
    }

    /**
     * Test case to cover {@link TransferService#create(Transfer)} with invalid id.
     * Expected to get {@link EntityValidationFailedException}.
     */
    @Test
    public void testUpdateWithoutId() {
        // GIVEN
        final Transfer transfer = Transfer.builder()
                .title(TRANSFER_TITLE)
                .description(TRANSFER_DESCRIPTION)
                .dateTime(TRANSFER_DATETIME)
                .count(TRANSFER_COUNT)
                .transferType(Transfer.TransferType.INCOME)
                .tags(Collections.emptySet())
                .build();

        // WHEN
        final Executable executable = () -> transferService.update(transfer);

        // THEN
        assertThrows(EntityValidationFailedException.class, executable, "EntityValidationFailed should be thrown.");
        verifyNoInteractions(transferRepositoryMock);
    }

    /**
     * Test case to cover {@link TransferService#delete(Long)}.
     * Expected {@link TransferRepository#delete(Object)} method call.
     */
    @Test
    public void testDelete() {
        transferService.delete(TRANSFER_ID);

        // THEN
        verify(transferRepositoryMock).deleteById(TRANSFER_ID);
        verifyNoMoreInteractions(transferRepositoryMock);
    }

}
