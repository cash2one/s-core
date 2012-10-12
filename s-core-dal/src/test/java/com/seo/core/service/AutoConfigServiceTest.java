package com.seo.core.service;

import com.seo.core.AbstractDalTest;
import com.seo.core.model.AutoConfig;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

public class AutoConfigServiceTest extends AbstractDalTest {

    @Inject
    private AutoConfigService autoConfigService;

    @Test
    public void testGetAll() {
        List<AutoConfig> results = autoConfigService.getAll();
    }
}
