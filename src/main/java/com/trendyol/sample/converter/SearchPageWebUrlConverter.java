package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.trendyol.sample.utils.UrlUtils.DEEPLINK_HOST;
import static com.trendyol.sample.utils.UrlUtils.DEEPLINK_SEARCH_QUERY_PARAMETER;
import static com.trendyol.sample.utils.UrlUtils.PAGE_PARAMETER;
import static com.trendyol.sample.utils.UrlUtils.SEARCH_PAGE_PARAMETER_VALUE;
import static com.trendyol.sample.utils.UrlUtils.SEARCH_QUERY_PARAMETER;

@Component
public class SearchPageWebUrlConverter implements UrlConvertHandler {

    public String convert(String url) {
        String searchQuery = getSearchQuery(url);

        String urls = UriComponentsBuilder.newInstance()
                .host(DEEPLINK_HOST)
                .path("/")
                .queryParam(PAGE_PARAMETER, SEARCH_PAGE_PARAMETER_VALUE)
                .query("{keyword}={keyword}")
                .buildAndExpand(DEEPLINK_SEARCH_QUERY_PARAMETER, searchQuery)
                .toUriString()
                .substring(2);

        UriComponentsBuilder convertedComponentBuilder = UriComponentsBuilder.fromUriString(urls).encode();

        return decodeUrl(convertedComponentBuilder.toUriString());
    }

    @Override
    public boolean supports(String url) {
        List<String> searchParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams().get(SEARCH_QUERY_PARAMETER);
        return CollectionUtils.isNotEmpty(searchParameter);
    }

    private String getSearchQuery(String url) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        return uriComponentsBuilder.build().getQueryParams().get(SEARCH_QUERY_PARAMETER).get(0);
    }

    private String decodeUrl(String url) {
        return UriUtils.decode(url, StandardCharsets.UTF_8);
    }
}
