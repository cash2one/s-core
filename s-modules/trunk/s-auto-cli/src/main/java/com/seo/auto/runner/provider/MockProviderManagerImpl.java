package com.seo.auto.runner.provider;

import com.seo.captcha.CaptchaService;
import com.seo.checker.ftp.FtpAccountChecker;
import com.seo.checker.pop.EmailAccountChecker;
import com.seo.provider.BlogAccountProvider;
import com.seo.provider.EmailProvider;
import com.seo.provider.FtpAccountProvider;
import com.seo.provider.manager.ProviderManager;
import com.seo.provider.model.Email;
import com.seo.provider.model.FTP;
import com.seo.proxy.ProxyProvider;
import com.seo.text.words.WordProvider;
import com.seo.useragent.UserAgentProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockProviderManagerImpl implements ProviderManager {
    private final static Logger LOGGER = LoggerFactory.getLogger(MockProviderManagerImpl.class);

    private CaptchaService captchaService;
    private WordProvider wordProvider;

    public void setCaptchaService(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    public void setWordProvider(WordProvider wordProvider) {
        this.wordProvider = wordProvider;
    }

    @Override
    public BlogAccountProvider getBlogAccountProvider() {
        return new BlogAccountProvider() {
            @Override
            public void createAccount(String type, String url, String xmlRpcPath, String login, String password) {
                LOGGER.debug("creating account: {} - {}", login, url);
            }
        };
    }

    @Override
    public EmailProvider getEmailProvider() {
        return new EmailProvider() {
            @Override
            public Email getRandomByHost(String type) {
                return getRandomEmail();
            }

            @Override
            public Email getRandomEmail() {
                return new Email() {
                    @Override
                    public Long getId() {
                        return null;
                    }

                    @Override
                    public String getEmail() {
                        return "ssinfinum@gmail.com";
                    }

                    @Override
                    public String getHost() {
                        return "gmail.com";
                    }

                    @Override
                    public String getUser() {
                        return "ssinfinum";
                    }

                    @Override
                    public String getPassword() {
                        return "fakepass";
                    }
                };

            }

            @Override
            public void deleteByEmail(String email) {
                LOGGER.debug("deleting email: {}", email);
            }

            @Override
            public void createEmailAccount(String email, String host, String user, String password, String type) {
                LOGGER.debug("creating email account: {}", email);
            }
        };
    }

    @Override
    public FtpAccountProvider getFtpAccountProvider() {
        return new FtpAccountProvider() {
            @Override
            public void createAccount(String url, String login, String password, String host, String prefix, String type) {
                LOGGER.debug("creating ftp account: {} {}", url, login);
            }

            @Override
            public FTP getAccountByType(String type) {
                return new FTP() {
                    @Override
                    public String getUrl() {
                        return "http://localhost";
                    }

                    @Override
                    public String getHost() {
                        return "localhost";
                    }

                    @Override
                    public String getLogin() {
                        return "dwh";
                    }

                    @Override
                    public String getPassword() {
                        return "1zNxop.3d";
                    }

                    @Override
                    public String getPrefix() {
                        return "/home/dwh/upload_test";
                    }
                };
            }
        };
    }

    @Override
    public FtpAccountChecker getFtpAccountChecker() {
        return new FtpAccountChecker() {
            @Override
            public boolean checkAccount(String host, String login, String password) {
                LOGGER.debug("checking ftp account");

                return true;
            }
        };
    }

    @Override
    public EmailAccountChecker getEmailAccountChecker() {
        return new EmailAccountChecker() {
            @Override
            public boolean checkAccount(String login, String password, String host) {
                LOGGER.debug("checking email account");

                return true;
            }
        };
    }

    @Override
    public ProxyProvider getProxyProvider() {
        return null;
    }

    @Override
    public CaptchaService getCaptchaService() {
        return captchaService;
    }

    @Override
    public UserAgentProvider getUserAgentProvider() {
        return new UserAgentProvider() {
            @Override
            public String getRandomUserAgent() {
                return "Firefox";
            }
        };
    }

    @Override
    public WordProvider getWordProvider() {
        return wordProvider;
    }
}
