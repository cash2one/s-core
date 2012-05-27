package com.seo.webclient.mods;

import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.params.HttpParams;

public class CustomCookieSpecFactory implements CookieSpecFactory {
    @Override
    public CookieSpec newInstance(HttpParams params) {
        return new CustomCompatSpec();
    }
}
