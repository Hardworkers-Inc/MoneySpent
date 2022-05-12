package com.hardworkers.moneyspent.Tag;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tags")
@AllArgsConstructor
public class TagController {

    private TagService tagService;

    @GetMapping
    public Iterable<Tag> getAll() {
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public Tag getById(@PathVariable Long id) {
        return tagService.getById(id);
    }

    @PostMapping
    public Tag create(@RequestBody Tag transfer) {
        return tagService.create(transfer);
    }

    @PutMapping
    public Tag update(@RequestBody Tag transfer) {
        return tagService.update(transfer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tagService.delete(id);
    }
}
