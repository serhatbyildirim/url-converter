package com.trendyol.sample.controller;

import com.trendyol.sample.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @GetMapping("urls")
    public String convert(@RequestParam("url") String url) {
        // TODO : url validator
        return urlService.convert(url);
    }

}
