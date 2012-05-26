package com.seo.webclient.mods;

import org.apache.http.client.RedirectHandler;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.*;

public class CustomHttpClient extends DefaultHttpClient{
    @Override
    protected RedirectHandler createRedirectHandler() {
        return new CustomRedirectHandler();
    }

    @Override
    protected CookieSpecRegistry createCookieSpecRegistry() {
        CookieSpecRegistry registry = new CookieSpecRegistry();
        registry.register(
                CookiePolicy.BEST_MATCH,
                new BestMatchSpecFactory());
        registry.register(
                CookiePolicy.BROWSER_COMPATIBILITY,
                new BrowserCompatSpecFactory());
        registry.register(
                CookiePolicy.NETSCAPE,
                new NetscapeDraftSpecFactory());
        registry.register(
                CookiePolicy.RFC_2109,
                new RFC2109SpecFactory());
        registry.register(
                CookiePolicy.RFC_2965,
                new RFC2965SpecFactory());
        registry.register(
                "custom",
                new CustomCookieSpecFactory()

        );
        return registry;
    }
}
