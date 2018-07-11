package com.konux.app.test.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.apache.http.HttpHeaders.CONTENT_TYPE;

@Component
public class ApacheHttpClient implements HttpClient {

    public static final Logger log = Logger.getLogger(HttpClient.class);

    @Override
    public int doPost(final String url, final byte[] body) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {

            CloseableHttpResponse response = httpclient.execute(
                buildHttpPost(new ByteArrayEntity(body), url));
            isHttpOk(response);

            return response.getStatusLine().getStatusCode();
        } catch (IOException e) {
            // missing cpp persister
            log.warn("event save failed ! " + e.getMessage());
        }

        return -1;
    }

    private HttpPost buildHttpPost(final ByteArrayEntity entity, final String url) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(CONTENT_TYPE, "application/octet-stream");
        httpPost.setEntity(entity);
        return httpPost;
    }

    private void isHttpOk(final CloseableHttpResponse response) {
        final int statusCode = response.getStatusLine()
                                       .getStatusCode();
        if (statusCode < 200 || 299 < statusCode) {
            throw new RuntimeException("event save failed ! http status code " + statusCode);
        }
    }

}
