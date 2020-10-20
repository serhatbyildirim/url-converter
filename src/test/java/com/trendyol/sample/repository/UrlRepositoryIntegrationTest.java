package com.trendyol.sample.repository;

import com.trendyol.sample.domain.UrlLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlRepositoryIntegrationTest {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @Test
    public void it_should_save() {
        // given
        UrlLog objectToSave = new UrlLog("url", "convertedUrl");

        // when
        urlRepository.save(objectToSave);

        // then
        UrlLog urlDocument = couchbaseTemplate.findById("url", UrlLog.class);
        assertThat(urlDocument.getRequestLog()).isEqualTo("url");
        assertThat(urlDocument.getResponseLog()).isEqualTo("convertedUrl");

        couchbaseTemplate.remove(urlDocument);
    }
}