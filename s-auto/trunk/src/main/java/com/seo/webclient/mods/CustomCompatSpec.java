package com.seo.webclient.mods;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.cookie.*;
import org.apache.http.impl.cookie.BrowserCompatSpec;

import java.util.List;

public class CustomCompatSpec extends BrowserCompatSpec {
    public CustomCompatSpec(String[] datepatterns) {
        super(datepatterns);
        registerAttribHandler(ClientCookie.EXPIRES_ATTR,
                new CookieAttributeHandler() {
                    @Override
                    public void parse(SetCookie cookie, String value) throws MalformedCookieException {

                    }

                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {

                    }

                    @Override
                    public boolean match(Cookie cookie, CookieOrigin origin) {
                        return false;
                    }
                }
        );
    }

    public CustomCompatSpec() {
        super();
        registerAttribHandler(ClientCookie.EXPIRES_ATTR,
                new CookieAttributeHandler() {
                    @Override
                    public void parse(SetCookie cookie, String value) throws MalformedCookieException {

                    }

                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {

                    }

                    @Override
                    public boolean match(Cookie cookie, CookieOrigin origin) {
                        return false;
                    }
                }
        );
    }

    @Override
    public List<Cookie> parse(Header header, CookieOrigin origin) throws MalformedCookieException {
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null");
        }
        if (origin == null) {
            throw new IllegalArgumentException("Cookie origin may not be null");
        }
        String headername = header.getName();
        String headervalue = header.getValue();
        if (!headername.equalsIgnoreCase(SM.SET_COOKIE)) {
            throw new MalformedCookieException("Unrecognized cookie header '"
                    + header.toString() + "'");
        }
        boolean isNetscapeCookie = false;

        HeaderElement[] elems = null;
        elems = header.getElements();
        return parse(elems, origin);
    }
}
