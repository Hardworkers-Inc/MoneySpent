package com.hardworkers.moneyspent.Transfer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends PagingAndSortingRepository<Transfer, Long> {
}
