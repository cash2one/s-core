package com.seo.core.facade;

import com.seo.blogposter.BlogPosterFacade;
import com.seo.blogposter.model.PostBlogRequest;
import com.seo.core.model.Doorway;
import com.seo.core.model.account.BlogAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PromotionFacadeImpl implements PromotionFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(PromotionFacadeImpl.class);

    private BlogPosterFacade blogPosterFacade;
    private BlogAccountFacade blogAccountFacade;

    public void setBlogAccountFacade(BlogAccountFacade blogAccountFacade) {
        this.blogAccountFacade = blogAccountFacade;
    }

    public void setBlogPosterFacade(BlogPosterFacade blogPosterFacade) {
        this.blogPosterFacade = blogPosterFacade;
    }

    @Override
    public void promote(Doorway doorway) {
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("promoting doorway, id = {}", doorway.getId());
        }

        BlogAccount blogAccount = blogAccountFacade.getRandom();
        PostBlogRequest request = new PostBlogRequest(
                blogAccount.getType(),
                blogAccount.getApiPath(),
                blogAccount.getLogin(),
                blogAccount.getPassword(),
                doorway.getUrl(),
                doorway.getUrl()//todo: fix to text generator
        );

        blogPosterFacade.post(request);
    }
}
