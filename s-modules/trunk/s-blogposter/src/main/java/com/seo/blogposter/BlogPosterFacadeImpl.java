package com.seo.blogposter;

import com.seo.blogposter.model.PostBlogRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class BlogPosterFacadeImpl implements BlogPosterFacade{
    private final static Logger LOGGER = LoggerFactory.getLogger(BlogPosterFacadeImpl.class);

    private List<BlogPoster> blogPosters;

    public void setBlogPosters(List<BlogPoster> blogPosters) {
        this.blogPosters = blogPosters;
    }

    @Override
    public BlogPoster getPosterByName(String name) {
        for (BlogPoster blogPoster : blogPosters) {
            if(blogPoster.getName().equals(name)) {
                return blogPoster;
            }
        }

        LOGGER.error("no such blog poster: {}", name);

        throw new RuntimeException("no such blogposter: " + name);
    }

    @Override
    public void post(PostBlogRequest request) {
        String blogType = request.getType();
        LOGGER.debug("posting to blog type = {}", blogType);

        BlogPoster blogPoster = this.getPosterByName(blogType);

        blogPoster.post(request);
    }
}
