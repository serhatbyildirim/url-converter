package com.trendyol.sample.domain;

import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document
public class UrlLog {

    @Id
    private String requestLog;
    private String responseLog;
}


