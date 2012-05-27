package com.seo.blogposter;

import com.seo.blogposter.model.PostBlogRequest;

public interface BlogPoster {
    void post(PostBlogRequest request);
    String getName();
}
