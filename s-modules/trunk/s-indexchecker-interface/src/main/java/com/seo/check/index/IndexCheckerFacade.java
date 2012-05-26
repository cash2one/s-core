package com.seo.check.index;

import com.seo.check.index.model.IndexCheckResponse;

public interface IndexCheckerFacade {
    IndexCheckResponse checkIndex(String url);
}
