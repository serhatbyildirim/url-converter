package com.trendyol.sample.exception;

public class UrlConvertException extends RuntimeException {

    public UrlConvertException() {
        super("Invalid Url");
    }
}
