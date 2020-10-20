package com.trendyol.sample.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductDetailPageWebUrlConverterTest {

    @InjectMocks
    private ProductDetailPageWebUrlConverter converter;

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_has_not_query_parameter() {
        // given
        String webUrl = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_and_has_both_merchantId_and_boutiqueId_parameters() {
        // given
        String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_and_has_only_campaignId_parameter() {
        // given
        String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_and_has_only_merchantId_parameter() {
        // given
        String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?merchantId=105064";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&MerchantId=105064");
    }
}