package com.konux.app.test.service;

public interface HttpClient {

    /**
     * Execute HTTP POST with given body content
     *
     * @param url
     * @param body
     */
    int doPost(String url, byte[] body);
}
