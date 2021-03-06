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

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class ProviderManagerImpl implements ProviderManager{
    @Inject
    private BlogAccountProvider blogAccountProvider;
    @Inject
    private EmailProvider emailProvider;
    @Inject
    private FtpAccountProvider ftpAccountProvider;
    @Inject
    private FtpAccountChecker ftpAccountChecker;
    @Inject
    private EmailAccountChecker emailAccountChecker;
    @Inject
    private CaptchaService captchaService;
    @Inject
    private ProxyProvider proxyProvider;
    @Inject
    private UserAgentProvider userAgentProvider;
    @Inject
    private WordProvider wordProvider;

    @Override
    public WordProvider getWordProvider() {
        return wordProvider;
    }

    public void setWordProvider(WordProvider wordProvider) {
        this.wordProvider = wordProvider;
    }

    public UserAgentProvider getUserAgentProvider() {
        return userAgentProvider;
    }

    public void setUserAgentProvider(UserAgentProvider userAgentProvider) {
        this.userAgentProvider = userAgentProvider;
    }

    public ProxyProvider getProxyProvider() {
        return proxyProvider;
    }

    public void setProxyProvider(ProxyProvider proxyProvider) {
        this.proxyProvider = proxyProvider;
    }

    public CaptchaService getCaptchaService() {
        return captchaService;
    }

    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public BlogAccountProvider getBlogAccountProvider() {
        return blogAccountProvider;
    }

    public void setBlogAccountProvider(BlogAccountProvider blogAccountProvider) {
        this.blogAccountProvider = blogAccountProvider;
    }

    public EmailProvider getEmailProvider() {
        return emailProvider;
    }

    public void setEmailProvider(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }

    public FtpAccountProvider getFtpAccountProvider() {
        return ftpAccountProvider;
    }

    public void setFtpAccountProvider(FtpAccountProvider ftpAccountProvider) {
        this.ftpAccountProvider = ftpAccountProvider;
    }

    public FtpAccountChecker getFtpAccountChecker() {
        return ftpAccountChecker;
    }

    public void setFtpAccountChecker(FtpAccountChecker ftpAccountChecker) {
        this.ftpAccountChecker = ftpAccountChecker;
    }

    public EmailAccountChecker getEmailAccountChecker() {
        return emailAccountChecker;
    }

    public void setEmailAccountChecker(EmailAccountChecker emailAccountChecker) {
        this.emailAccountChecker = emailAccountChecker;
    }
}
