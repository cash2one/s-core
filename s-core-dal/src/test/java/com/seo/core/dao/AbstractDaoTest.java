package com.seo.core.dao;

import com.seo.core.AbstractDalTest;
import com.seo.core.model.BaseModel;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractDaoTest extends AbstractDalTest {
    protected BaseRepository<BaseModel, Long> repository;

    public abstract BaseModel createModel();

    @Test
    public void testSave() {
        BaseModel model = createModel();

        model = repository.save(model);

        BaseModel loaded = repository.findOne(model.getId());

        assertEquals(model.getId(), loaded.getId());
    }

    @Test
    public void testFind() {
        BaseModel model = createModel();

        model = repository.save(model);

        repository.save(createModel());
        repository.save(createModel());

        BaseModel loaded = repository.findOne(model.getId());

        assertEquals(loaded.getId(), model.getId());
    }

    @Test
    public void testDelete() {
        BaseModel model = createModel();

        model = repository.save(model);

        Long id = model.getId();
        assertNotNull(model.getId());

        repository.delete(model);

        assertNull(repository.findOne(id));
    }
}
