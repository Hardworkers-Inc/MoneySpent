package com.hardworkers.moneyspent.Tag;

import com.hardworkers.moneyspent.BaseCrudService;
import com.hardworkers.moneyspent.exceptions.EntityNotFoundException;
import com.hardworkers.moneyspent.exceptions.EntityValidationFailedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TagService implements BaseCrudService<Tag> {

    private final TagRepository tagRepository;

    @Override
    public Iterable<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag getById(Long id) {
        return tagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Tag", id)
        );
    }

    @Override
    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        if (Objects.isNull(tag.getId())) {
            throw new EntityValidationFailedException("Id can't be null");
        }
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }
}
