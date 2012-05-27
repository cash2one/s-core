package com.seo.doorgen.component.naming;

import com.seo.doorgen.model.Keyword;

import java.util.List;

public interface PageNamingStrategy {
    String generatePageName(List<Keyword> keywords);
}
