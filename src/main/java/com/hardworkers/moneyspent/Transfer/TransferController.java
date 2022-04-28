package com.hardworkers.moneyspent.Transfer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
@AllArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @GetMapping
    public Iterable<Transfer> getAll() {
        return transferService.getAll();
    }

    @GetMapping("/{id}")
    public Transfer getById(@PathVariable Long id) {
        return transferService.getById(id);
    }

    @PostMapping
    public Transfer create(@RequestBody Transfer transfer) {
        return transferService.create(transfer);
    }

    @PutMapping
    public Transfer update(@RequestBody Transfer transfer) {
        return transferService.update(transfer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        transferService.delete(id);
    }
}
