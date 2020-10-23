package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static com.trendyol.sample.utils.UrlUtils.HTTPS;
import static com.trendyol.sample.utils.UrlUtils.PAGE_PARAMETER;
import static com.trendyol.sample.utils.UrlUtils.PRODUCT_DETAIL_PAGE_PARAMETER_VALUE;
import static com.trendyol.sample.utils.UrlUtils.SEARCH_PAGE_PARAMETER_VALUE;
import static com.trendyol.sample.utils.UrlUtils.WEB_URL_HOST;

@Component
public class OtherPageDeeplinkConverter implements UrlConvertHandler {

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
