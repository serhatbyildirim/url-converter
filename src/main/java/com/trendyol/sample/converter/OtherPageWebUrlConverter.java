package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

import static com.trendyol.sample.utils.UrlUtils.DEEPLINK_HOST;
import static com.trendyol.sample.utils.UrlUtils.HOME_PARAMETER_VALUE;
import static com.trendyol.sample.utils.UrlUtils.MY_ACCOUNT;
import static com.trendyol.sample.utils.UrlUtils.PAGE_PARAMETER;

@Component
public class OtherPageWebUrlConverter implements UrlConvertHandler {

    public String convert(String url) {

        return UriComponentsBuilder.newInstance()
                .host(DEEPLINK_HOST)
                .path("/")
                .queryParam(PAGE_PARAMETER, HOME_PARAMETER_VALUE)
                .build()
                .toUriString()
                .substring(2);
    }

    @Override
    public boolean supports(String url) {
        return Objects.requireNonNull(UriComponentsBuilder.fromUriString(url).build().getPath()).contains(MY_ACCOUNT);
    }
}
