package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class OtherPageDeeplinkConverter implements UrlConvertHandler {

    private static final String PAGE_PARAMETER = "Page";
    private static final String PRODUCT_DETAIL_PAGE_PARAMETER_VALUE = "Product";
    private static final String SEARCH_PAGE_PARAMETER_VALUE = "Search";
    private static final String WEB_URL_HOST = "www.trendyol.com";
    private static final String HTTPS = "https";

    public String convert(String url) {

        return UriComponentsBuilder.newInstance()
                .scheme(HTTPS)
                .host(WEB_URL_HOST)
                .build()
                .toUriString();
    }

    @Override
    public boolean supports(String url) {
        List<String> pageParameterList = UriComponentsBuilder.fromUriString(url).build().getQueryParams().get(PAGE_PARAMETER);
        return CollectionUtils.isNotEmpty(pageParameterList) &&
                pageParameterList.stream()
                .anyMatch(parameter -> parameter.equals(PRODUCT_DETAIL_PAGE_PARAMETER_VALUE) || parameter.equals(SEARCH_PAGE_PARAMETER_VALUE));
    }
}
