package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.trendyol.sample.utils.UrlUtils.DEEPLINK_SEARCH_QUERY_PARAMETER;
import static com.trendyol.sample.utils.UrlUtils.HTTPS;
import static com.trendyol.sample.utils.UrlUtils.PAGE_PARAMETER;
import static com.trendyol.sample.utils.UrlUtils.WEB_URL_HOST;

@Component
public class SearchPageDeeplinkConverter implements UrlConvertHandler {

    public String convert(String url) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        String searchParameter = uriComponentsBuilder.build().getQueryParams().get(DEEPLINK_SEARCH_QUERY_PARAMETER).get(0);

        return UriComponentsBuilder.newInstance()
                .scheme(HTTPS)
                .host(WEB_URL_HOST)
                .path("/tum-urunler")
                .query("q={keyword}")
                .buildAndExpand(searchParameter)
                .toUriString();
    }

    @Override
    public boolean supports(String url) {
        List<String> pageParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams().get(PAGE_PARAMETER);
        List<String> queryParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams().get(DEEPLINK_SEARCH_QUERY_PARAMETER);
        return CollectionUtils.isNotEmpty(pageParameter) && CollectionUtils.isNotEmpty(queryParameter);
    }
}
