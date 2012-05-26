package com.seo.text.parse;

import com.seo.text.parse.model.ParsePageResponse;

public interface PageParser {
    ParsePageResponse parsePage(String page);
}
