package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Component
public class OtherPageWebUrlConverter implements UrlConvertHandler {

    private static final String MY_ACCOUNT = "Hesabim";
    private static final String DEEPLINK_HOST = "ty:/";
    private static final String PAGE_PARAMETER = "Page";
    private static final String HOME_PARAMETER_VALUE = "Home";

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
