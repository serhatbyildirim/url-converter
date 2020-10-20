package com.trendyol.sample.service;

import com.trendyol.sample.converter.OtherPageDeeplinkConverter;
import com.trendyol.sample.converter.OtherPageWebUrlConverter;
import com.trendyol.sample.converter.ProductDetailPageDeeplinkConverter;
import com.trendyol.sample.converter.ProductDetailPageWebUrlConverter;
import com.trendyol.sample.converter.SearchPageDeeplinkConverter;
import com.trendyol.sample.converter.SearchPageWebUrlConverter;
import com.trendyol.sample.exception.UrlConvertException;
import com.trendyol.sample.repository.UrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UrlConvertBuilderServiceTest {

    @InjectMocks
    private UrlConvertBuilderService service;

    @Mock
    private ProductDetailPageWebUrlConverter productDetailPageWebUrlConverter;

    @Mock
    private ProductDetailPageDeeplinkConverter productDetailPageDeeplinkConverter;

    @Mock
    private SearchPageWebUrlConverter searchPageWebUrlConverter;

    @Mock
    private SearchPageDeeplinkConverter searchPageDeeplinkConverter;

    @Mock
    private OtherPageWebUrlConverter otherPageWebUrlConverter;

    @Mock
    private OtherPageDeeplinkConverter otherPageDeeplinkConverter;

    @Mock
    private UrlRepository urlRepository;

    @Test
    public void it_should_convert_url_when_url_is_product_detail_page_web_url() {
        //given
        String url = "url";
        given(productDetailPageWebUrlConverter.supports(url)).willReturn(true);

        given(productDetailPageWebUrlConverter.convert(url)).willReturn("convertedUrl");

        //when
        String build = service.build(url);

        //then
        verify(productDetailPageWebUrlConverter).convert(url);
        assertThat(build).isEqualTo("convertedUrl");
    }

    @Test
    public void it_should_convert_url_when_url_is_product_detail_page_deeplink() {
        //given
        String url = "url";
        given(productDetailPageDeeplinkConverter.supports(url)).willReturn(true);

        given(productDetailPageDeeplinkConverter.convert(url)).willReturn("convertedUrl");

        //when
        String build = service.build(url);

        //then
        verify(productDetailPageDeeplinkConverter).convert(url);
        assertThat(build).isEqualTo("convertedUrl");
    }

    @Test
    public void it_should_convert_url_when_url_is_search_page_web_url() {
        //given
        String url = "url";
        given(searchPageWebUrlConverter.supports(url)).willReturn(true);

        given(searchPageWebUrlConverter.convert(url)).willReturn("convertedUrl");

        //when
        String build = service.build(url);

        //then
        verify(searchPageWebUrlConverter).convert(url);
        assertThat(build).isEqualTo("convertedUrl");
    }

    @Test
    public void it_should_convert_url_when_url_is_search_page_deeplink() {
        //given
        String url = "url";
        given(searchPageDeeplinkConverter.supports(url)).willReturn(true);

        given(searchPageDeeplinkConverter.convert(url)).willReturn("convertedUrl");

        //when
        String build = service.build(url);

        //then
        verify(searchPageDeeplinkConverter).convert(url);
        assertThat(build).isEqualTo("convertedUrl");
    }

    @Test
    public void it_should_convert_url_when_url_is_other_page_web_url() {
        //given
        String url = "url";
        given(otherPageWebUrlConverter.supports(url)).willReturn(true);

        given(otherPageWebUrlConverter.convert(url)).willReturn("convertedUrl");

        //when
        String build = service.build(url);

        //then
        verify(otherPageWebUrlConverter).convert(url);
        assertThat(build).isEqualTo("convertedUrl");
    }

    @Test
    public void it_should_convert_url_when_url_is_other_page_deeplink() {
        //given
        String url = "url";
        given(otherPageDeeplinkConverter.supports(url)).willReturn(true);

        given(otherPageDeeplinkConverter.convert(url)).willReturn("convertedUrl");

        //when
        String build = service.build(url);

        //then
        verify(otherPageDeeplinkConverter).convert(url);
        assertThat(build).isEqualTo("convertedUrl");
    }

    @Test
    public void it_should_throw_exception_when_url_is_not_valid() {
        //then
        assertThatCode(() -> service.build("invalidUrl"))
                .isInstanceOf(UrlConvertException.class)
                .hasMessageContaining("Wrong Url");
    }

}