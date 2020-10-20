package com.trendyol.sample.repository;

import com.trendyol.sample.domain.UrlLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UrlRepository {

    private final CouchbaseTemplate couchbaseTemplate;

    public void save(UrlLog urlLog) {
        couchbaseTemplate.save(urlLog);
    }
}
