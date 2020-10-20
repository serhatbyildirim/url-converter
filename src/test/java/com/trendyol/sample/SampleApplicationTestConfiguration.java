package com.trendyol.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import static org.mockito.Mockito.mock;

public class SampleApplicationTestConfiguration {

    @Bean
    public CouchbaseTemplate couchbaseTemplate() {
        return mock(CouchbaseTemplate.class);
    }
}
