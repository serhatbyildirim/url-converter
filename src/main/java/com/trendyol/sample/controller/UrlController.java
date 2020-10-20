package com.trendyol.sample.controller;

import com.trendyol.sample.service.UrlConvertBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlConvertBuilderService urlConvertBuilderService;

    @GetMapping("urls")
    public String convert(@RequestParam("url") String url) {
        return urlConvertBuilderService.build(url);
    }

}
