package com.trendyol.sample.service;

import com.trendyol.sample.converter.OtherPageDeeplinkConverter;
import com.trendyol.sample.converter.OtherPageWebUrlConverter;
import com.trendyol.sample.converter.ProductDetailPageDeeplinkConverter;
import com.trendyol.sample.converter.ProductDetailPageWebUrlConverter;
import com.trendyol.sample.converter.SearchPageDeeplinkConverter;
import com.trendyol.sample.converter.SearchPageWebUrlConverter;
import com.trendyol.sample.domain.UrlLog;
import com.trendyol.sample.exception.UrlConvertException;
import com.trendyol.sample.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UrlConvertBuilderService {

    private final ProductDetailPageWebUrlConverter productDetailPageWebUrlConverter;
    private final ProductDetailPageDeeplinkConverter productDetailPageDeeplinkConverter;
    private final SearchPageWebUrlConverter searchPageWebUrlConverter;
    private final SearchPageDeeplinkConverter searchPageDeeplinkConverter;
    private final OtherPageWebUrlConverter otherPageWebUrlConverter;
    private final OtherPageDeeplinkConverter otherPageDeeplinkConverter;
    private final UrlRepository urlRepository;

    public String build(String url) {
        Optional<UrlConvertHandler> urlConvertAppenderOptional = Stream.of(
                productDetailPageWebUrlConverter,
                productDetailPageDeeplinkConverter,
                searchPageWebUrlConverter,
                searchPageDeeplinkConverter,
                otherPageWebUrlConverter,
                otherPageDeeplinkConverter)
                .filter(c -> c.supports(url))
                .findFirst();

        if (!urlConvertAppenderOptional.isPresent()) {
            throw new UrlConvertException();
        }

        String convertedUrl = urlConvertAppenderOptional
                .get()
                .convert(url);

        urlRepository.save(new UrlLog(url, convertedUrl));

        return convertedUrl;
    }

}
