package com.devictor.url_shortening.controllers;

import com.devictor.url_shortening.domain.url.Url;
import com.devictor.url_shortening.domain.url.UrlRequestDTO;
import com.devictor.url_shortening.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shorten")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<Url> shortenURL(@RequestBody UrlRequestDTO requestBody) {
        Url urlData = urlService.shortenURL(requestBody.url());
        return ResponseEntity.ok(urlData);
    }
}
