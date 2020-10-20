package com.trendyol.sample.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OtherPageDeeplinkConverterTest {

    @InjectMocks
    private OtherPageDeeplinkConverter converter;

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_other_page_deeplink() {
        // given
        String deeplink = "ty://?Page=Favorites";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com");
    }

    @Test
    public void it_should_convert_to_web_url_when_deeplink_is_other_page_deeplink2() {
        // given
        String deeplink = "ty://?Page=Orders";

        // when
        String webUrl = converter.convert(deeplink);

        // then
        assertThat(webUrl).isEqualTo("https://www.trendyol.com");
    }
}