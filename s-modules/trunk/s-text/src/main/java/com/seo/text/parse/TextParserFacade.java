package com.seo.text.parse;

import com.seo.text.parse.model.ParsePageResponse;

public interface TextParserFacade {
    ParsePageResponse parsePage(String url);
}
