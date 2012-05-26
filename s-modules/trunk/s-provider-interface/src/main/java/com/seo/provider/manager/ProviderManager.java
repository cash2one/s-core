package com.seo.provider.manager;

import com.seo.captcha.CaptchaService;
import com.seo.checker.ftp.FtpAccountChecker;
import com.seo.checker.pop.EmailAccountChecker;
import com.seo.provider.BlogAccountProvider;
import com.seo.provider.EmailProvider;
import com.seo.provider.FtpAccountProvider;
import com.seo.proxy.ProxyProvider;
import com.seo.text.words.WordProvider;
import com.seo.useragent.UserAgentProvider;

public interface ProviderManager {
    BlogAccountProvider getBlogAccountProvider();
    EmailProvider getEmailProvider();
    FtpAccountProvider getFtpAccountProvider();
    FtpAccountChecker getFtpAccountChecker();
    EmailAccountChecker getEmailAccountChecker();
    ProxyProvider getProxyProvider();
    CaptchaService getCaptchaService();
    UserAgentProvider getUserAgentProvider();
    WordProvider getWordProvider();
}
