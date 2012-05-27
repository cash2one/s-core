package com.seo.blogposter;

import com.seo.blogposter.model.PostBlogRequest;

public interface BlogPosterFacade {
    BlogPoster getPosterByName(String name);
    void post(PostBlogRequest request);
}
