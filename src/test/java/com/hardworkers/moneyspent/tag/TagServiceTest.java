package com.hardworkers.moneyspent.tag;

import com.hardworkers.moneyspent.Tag.TagRepository;
import com.hardworkers.moneyspent.Tag.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Test class to cover {@link TagService}.
 */
public class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


}
