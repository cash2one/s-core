package com.seo.text.link;

import java.util.List;

public interface GoogleNewsProvider {
    List<String> fetchNews(String query, int start);
}
