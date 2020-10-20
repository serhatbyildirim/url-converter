package com.trendyol.sample.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SearchPageWebUrlConverterTest {

    @InjectMocks
    private SearchPageWebUrlConverter converter;

    @Test
    public void it_should_convert_to_deeplink_when_url_is_search_page_url() {
        // given
        String webUrl = "https://www.trendyol.com/tum—urunler?q=elbise";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Search&Query=elbise");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_search_page_url_with_encoded_url() {
        // given
        String webUrl = "https://www.trendyol.com/tum—urunler?q=%C3%BCt%C3%BC";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Search&Query=%C3%BCt%C3%BC");
    }

}