package com.seo.core.service;

import com.seo.core.AbstractDalTest;
import com.seo.core.model.AutoConfig;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

public class AutoConfigManagerTest extends AbstractDalTest {

    @Inject
    private AutoConfigManager autoConfigManager;

    @Test
    public void testGetAll() {
        List<AutoConfig> results = autoConfigManager.getAll();
    }
}
