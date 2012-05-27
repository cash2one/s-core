package com.seo.provider;

public interface BlogAccountProvider {
    /**
     * Creates blog accoung with provided information.
     *
     * @param type type of blog account. e.g. <i>livejournal</i>, <i>mailru</i>
     * @param url url of the blog account
     * @param xmlRpcPath path to the xml rpc service
     * @param login login of the blog account
     * @param password password of the blog account
     */
    public void createAccount(String type, String url, String xmlRpcPath, String login, String password);
}
