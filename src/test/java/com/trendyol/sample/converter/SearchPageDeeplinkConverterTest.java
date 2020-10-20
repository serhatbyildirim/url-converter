package com.trendyol.sample.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SearchPageDeeplinkConverterTest {

    @InjectMocks
    private SearchPageDeeplinkConverter converter;

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_search_page_deeplink() {
        // given
        String deeplink = "ty://?Page=Search&Query=elbise";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/tum-urunler?q=elbise");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_search_page_deeplink_with_encoded_url() {
        // given
        String deeplink = "ty://?Page=Search&Query=%C3%BCt%C3%BC";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com/tum-urunler?q=%C3%BCt%C3%BC");
    }

}