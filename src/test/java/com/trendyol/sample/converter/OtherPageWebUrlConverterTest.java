package com.trendyol.sample.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OtherPageWebUrlConverterTest {

    @InjectMocks
    private OtherPageWebUrlConverter converter;

    @Test
    public void it_should_convert_to_deeplink_when_url_is_other_page_url() {
        // given
        String webUrl = "https://www.trendyol.com/Hesabim/Favoriler";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Home");
    }

    @Test
    public void it_should_convert_to_deeplink_when_url_is_other_page_url2() {
        // given
        String webUrl = "https://www.trendyol.com/Hesabim/#/Siparislerim";

        // when
        String deeplink = converter.convert(webUrl);

        // then
        assertThat(deeplink).isEqualTo("ty://?Page=Home");
    }
}