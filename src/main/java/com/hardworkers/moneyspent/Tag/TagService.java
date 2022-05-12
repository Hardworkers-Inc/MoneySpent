package com.hardworkers.moneyspent.Tag;

import com.hardworkers.moneyspent.BaseCrudService;
import com.hardworkers.moneyspent.exceptions.EntityDuplicatesException;
import com.hardworkers.moneyspent.exceptions.EntityNotFoundException;
import com.hardworkers.moneyspent.exceptions.EntityValidationFailedException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class TagService implements BaseCrudService<Tag> {

    private final TagRepository tagRepository;
    private final TagValidator tagValidator;

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

    public Tag getByName(String name) {
        return tagRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Tag", name)
        );
    }

    @Override
    public Tag create(Tag tag) {
        if (tagRepository.findByName(tag.getName()).isPresent()) {
            throw new EntityDuplicatesException("Tag", tag.getName());
        }
        return tagRepository.save(tag);
    }

    @Override
    public Tag update(Tag tag) {
        if (Objects.isNull(tag.getId())) {
            throw new EntityValidationFailedException("Id can't be null");
        }
        tagValidator.validateExist(tag.getId());
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagValidator.validateExist(id);
        tagRepository.deleteById(id);
    }

}
