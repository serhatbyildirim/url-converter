package com.trendyol.sample.service;

public interface UrlConvertHandler {

    String convert(String url);

    boolean supports(String url);
}
