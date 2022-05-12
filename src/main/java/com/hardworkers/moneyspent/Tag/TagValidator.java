package com.hardworkers.moneyspent.Tag;

import com.hardworkers.moneyspent.exceptions.EntityDuplicatesException;
import com.hardworkers.moneyspent.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public record TagValidator(TagRepository tagRepository) {

    public void validateExist(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException("Transfer", id);
        }
    }

    public void validateNotDuplicates(Tag tag) {
        Optional<Tag> tagOptional = tagRepository.findByName(tag.getName());
        if (tagOptional.isPresent()) {
            Tag tagFromDb = tagOptional.get();
            if (!Objects.equals(tagFromDb.getId(), tag.getId())) {
                throw new EntityDuplicatesException("Tag", tag.getName());
            }
        }
    }
}
