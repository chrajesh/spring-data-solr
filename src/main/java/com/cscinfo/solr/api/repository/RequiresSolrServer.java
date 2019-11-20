package com.cscinfo.solr.api.repository;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.core.Is;
import org.junit.Assume;
import org.junit.AssumptionViolatedException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.io.IOException;


public class RequiresSolrServer implements TestRule {

    private static final String PING_PATH = "/admin/info/system";

    private final String baseUrl;
    @Nullable
    private final String collection;

    private RequiresSolrServer(String baseUrl) {
        this(baseUrl, null);
    }

    private RequiresSolrServer(String baseUrl, @Nullable String collection) {
        this.baseUrl = baseUrl;
        this.collection = collection;
    }

    public static RequiresSolrServer onLocalhost() {
        return new RequiresSolrServer("http://localhost:8983/solr");
    }

    public RequiresSolrServer withCollection(String collection) {
        return new RequiresSolrServer(baseUrl, collection);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {

                checkServerRunning();
                base.evaluate();
            }
        };
    }

    private void checkServerRunning() {

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            String url = StringUtils.hasText(collection) ? baseUrl + "/" + collection + "/select?q=*:*" : baseUrl + PING_PATH;

            CloseableHttpResponse response = client.execute(new HttpGet(url));
            if (response != null && response.getStatusLine() != null) {
                Assume.assumeThat(response.getStatusLine().getStatusCode(), Is.is(200));
            }
        } catch (IOException e) {
            throw new AssumptionViolatedException("SolrServer does not seem to be running", e);
        }
    }

}
