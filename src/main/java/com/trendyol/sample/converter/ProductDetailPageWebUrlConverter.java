package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@Service
public class ProductDetailPageWebUrlConverter implements UrlConvertHandler {

    private static final String BOUTIQUE_ID_PARAMETER = "boutiqueId";
    private static final String MERCHANT_ID_PARAMETER = "merchantId";
    private static final String DEEPLINK_MERCHANT_ID_PARAMETER = "MerchantId";
    private static final String CAMPAIGN_ID_PARAMETER = "CampaignId";
    private static final String PAGE_PARAMETER = "Page";
    private static final String PRODUCT_DETAIL_PAGE_PARAMETER_VALUE = "Product";
    private static final String CONTENT_ID_PARAMETER = "ContentId";
    private static final String PRODUCT_URL = "-p-";
    private static final String DEEPLINK_HOST = "ty:/";

    public String convert(String url) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);

        String productId = getProductId(url);

        String urls = UriComponentsBuilder.newInstance()
                .host(DEEPLINK_HOST)
                .path("/")
                .queryParam(PAGE_PARAMETER, PRODUCT_DETAIL_PAGE_PARAMETER_VALUE)
                .query("{keyword}={keyword}")
                .buildAndExpand(CONTENT_ID_PARAMETER, productId)
                .toUriString()
                .substring(2);

        UriComponentsBuilder convertedComponentBuilder = UriComponentsBuilder.fromUriString(urls);

        List<String> boutiqueParameterList = uriComponentsBuilder.build().getQueryParams().get(BOUTIQUE_ID_PARAMETER);
        List<String> merchantParameterList = uriComponentsBuilder.build().getQueryParams().get(MERCHANT_ID_PARAMETER);

        if (CollectionUtils.isNotEmpty(boutiqueParameterList)) {
            convertedComponentBuilder.replaceQueryParam(BOUTIQUE_ID_PARAMETER)
                    .replaceQueryParam(CAMPAIGN_ID_PARAMETER, boutiqueParameterList.get(0));
        }

        if (CollectionUtils.isNotEmpty(merchantParameterList)) {
            convertedComponentBuilder.replaceQueryParam(MERCHANT_ID_PARAMETER)
                    .replaceQueryParam(DEEPLINK_MERCHANT_ID_PARAMETER, merchantParameterList.get(0));
        }

        return convertedComponentBuilder.toUriString();
    }

    @Override
    public boolean supports(String url) {
        return Objects.requireNonNull(UriComponentsBuilder.fromUriString(url).build().getPath()).contains(PRODUCT_URL);
    }

    private String getProductId(String url) {
        return Objects.requireNonNull(UriComponentsBuilder.fromUriString(url).build().getPath()).split(PRODUCT_URL)[1];
    }
}
