package com.hardworkers.moneyspent.service;

import com.hardworkers.moneyspent.models.Transfer;
import com.hardworkers.moneyspent.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransferService implements BaseCrudService<Transfer> {

    private final TransferRepository transferRepository;

    @Override
    public List<Transfer> getAll() {
        return null;
    }

    @Override
    public Transfer get(Long id) {
        return null;
    }

    @Override
    public Transfer create(Transfer transfer) {
        return null;
    }

    @Override
    public Transfer update(Transfer transfer) {
        return null;
    }

    @Override
    public void delete(Transfer transfer) {

    }
}
