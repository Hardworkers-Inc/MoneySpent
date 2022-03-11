package com.hardworkers.moneyspent.repository;

import com.hardworkers.moneyspent.models.Transfer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends CrudRepository<Transfer, Long> {
}
