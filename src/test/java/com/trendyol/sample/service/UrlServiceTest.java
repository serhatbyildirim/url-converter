package com.trendyol.sample.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {
    
    @InjectMocks
    private UrlService service;

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_and_has_both_merchantId_and_boutiqueId_parameters() {
        // given
        String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_has_not_query_parameter() {
        // given
        String webUrl = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_and_has_only_campaignId_parameter() {
        // given
        String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_product_detail_page_url_and_has_only_merchantId_parameter() {
        // given
        String webUrl = "https://www.trendyol.com/casio/saat-p-1925865?merchantId=105064";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_search_page_url() {
        // given
        String webUrl = "https://www.trendyol.com/tum—urunler?q=elbise";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Search&Query=elbise");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_search_page_url_with_encoded_url() {
        // given
        String webUrl = "https://www.trendyol.com/tum—urunler?q=%C3%BCt%C3%BC";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_other_page_url() {
        // given
        String webUrl = "https://www.trendyol.com/Hesabim/Favoriler";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_other_page_url2() {
        // given
        String webUrl = "https://www.trendyol.com/Hesabim/#/Siparislerim";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_has_productContentId_and_campaignId_and_merchantId_parameters() {
        // given
        String webUrl = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";

        // when
        String deeplink = service.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_and_has_only_productContentId() {
        // given
        String deeplink = "ty://?Page=Product&ContentId=1925865";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_and_has_both_productContentId_and_campaignId() {
        // given
        String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_product_detail_page_deeplink_and_has_both_productContentId_and_merchantId() {
        // given
        String deeplink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/brand/name-p-1925865?&merchantId=105064");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_search_page_deeplink() {
        // given
        String deeplink = "ty://?Page=Search&Query=elbise";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("ty://?Page=Search&Query=elbise");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_search_page_deeplink_with_encoded_url() {
        // given
        String deeplink = "ty://?Page=Search&Query=%C3%BCt%C3%BC";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_other_page_deeplink() {
        // given
        String deeplink = "ty://?Page=Favorites";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("www.trendyol.com");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_other_page_deeplink2() {
        // given
        String deeplink = "ty://?Page=Orders";

        // when
        String webUrl = service.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("www.trendyol.com");
    }
}