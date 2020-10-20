package com.trendyol.sample.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailPageDeeplinkConverterTest {

    @InjectMocks
    private ProductDetailPageDeeplinkConverter converter;

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_and_has_only_productContentId() {
        // given
        String deeplink = "ty://?Page=Product&ContentId=1925865";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_has_productContentId_and_campaignId_and_merchantId_parameters() {
        // given
        String webUrl = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_and_has_both_productContentId_and_campaignId() {
        // given
        String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_and_has_both_productContentId_and_merchantId() {
        // given
        String deeplink = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?merchantId=105064");
    }

}