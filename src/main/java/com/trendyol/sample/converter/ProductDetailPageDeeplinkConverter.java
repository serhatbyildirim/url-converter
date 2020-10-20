package com.trendyol.sample.converter;

import com.trendyol.sample.service.UrlConvertHandler;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class ProductDetailPageDeeplinkConverter implements UrlConvertHandler {

    private static final String BOUTIQUE_ID_PARAMETER = "boutiqueId";
    private static final String MERCHANT_ID_PARAMETER = "merchantId";
    private static final String DEEPLINK_MERCHANT_ID_PARAMETER = "MerchantId";
    private static final String CAMPAIGN_ID_PARAMETER = "CampaignId";
    private static final String PAGE_PARAMETER = "Page";
    private static final String CONTENT_ID_PARAMETER = "ContentId";
    private static final String WEB_URL_HOST = "www.trendyol.com";
    private static final String HTTPS = "https";

    public String convert(String url) {

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url);
        String contentId = uriComponentsBuilder.build().getQueryParams().get(CONTENT_ID_PARAMETER).get(0);

        String urls = UriComponentsBuilder.newInstance()
                .scheme(HTTPS)
                .host(WEB_URL_HOST)
                .path("/brand/name-p-{contentId}")
                .buildAndExpand(contentId)
                .toUriString();

        List<String> campaignParameterList = uriComponentsBuilder.build().getQueryParams().get(CAMPAIGN_ID_PARAMETER);
        List<String> merchantParameterList = uriComponentsBuilder.build().getQueryParams().get(DEEPLINK_MERCHANT_ID_PARAMETER);

        UriComponentsBuilder convertedComponentBuilder = UriComponentsBuilder.fromUriString(urls);

        if (CollectionUtils.isNotEmpty(campaignParameterList)) {
            convertedComponentBuilder.replaceQueryParam(CAMPAIGN_ID_PARAMETER)
                    .replaceQueryParam(BOUTIQUE_ID_PARAMETER, campaignParameterList.get(0));
        }

        if (CollectionUtils.isNotEmpty(merchantParameterList)) {
            convertedComponentBuilder.replaceQueryParam(DEEPLINK_MERCHANT_ID_PARAMETER)
                    .replaceQueryParam(MERCHANT_ID_PARAMETER, merchantParameterList.get(0));
        }

        return convertedComponentBuilder.toUriString();
    }

    @Override
    public boolean supports(String url) {
        List<String> pageParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams().get(PAGE_PARAMETER);
        List<String> contentIdParameter = UriComponentsBuilder.fromUriString(url).build().getQueryParams().get(CONTENT_ID_PARAMETER);
        return CollectionUtils.isNotEmpty(pageParameter) && CollectionUtils.isNotEmpty(contentIdParameter);
    }
}
