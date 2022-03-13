package com.hardworkers.moneyspent.Transfer;

import com.hardworkers.moneyspent.BaseCrudService;
import com.hardworkers.moneyspent.exceptions.EntityNotFoundException;
import com.hardworkers.moneyspent.exceptions.EntityValidationFailedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TransferService implements BaseCrudService<Transfer> {

    private final TransferRepository transferRepository;
    private final TransferValidator transferValidator;

    @Override
    public Iterable<Transfer> getAll() {
        return transferRepository.findAll();
    }

    @Override
    public Transfer getById(Long id) {
        return transferRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Transfer", id)
        );
    }

    @Override
    public Transfer create(Transfer transfer) {
        String error = transferValidator.validate(transfer);
        if (error.length() > 0) {
            throw new EntityValidationFailedException(error);
        }
        return transferRepository.save(transfer);
    }

    @Override
    public Transfer update(Transfer transfer) {
        if (transfer.getId() == null) {
            throw new EntityValidationFailedException("Id can't be null");
        }
        return transferRepository.save(transfer);
    }

    @Override
    public void delete(Long id) {
        transferRepository.deleteById(id);
    }
}
