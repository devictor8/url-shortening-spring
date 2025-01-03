package com.devictor.url_shortening.controllers;

import com.devictor.url_shortening.domain.url.UrlResponseDTO;
import com.devictor.url_shortening.domain.url.UrlRequestDTO;
import com.devictor.url_shortening.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shorten")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping
    public ResponseEntity<UrlResponseDTO> shortenURL(@RequestBody UrlRequestDTO requestBody) {
        UrlResponseDTO urlData = urlService.shortenURL(requestBody.url());
        return ResponseEntity.status(201).body(urlData);
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<UrlResponseDTO> getUrlByShortCode(@PathVariable String shortCode) {
        UrlResponseDTO urlData = urlService.getUrlByShortCode(shortCode);
        return ResponseEntity.ok(urlData);
    }

    @PutMapping("/{shortCode}")
    public ResponseEntity<UrlResponseDTO> updateUrl(@PathVariable String shortCode, @RequestBody UrlRequestDTO requestBody) {
        UrlResponseDTO urlData = urlService.updateUrl(shortCode, requestBody.url());
        return ResponseEntity.ok(urlData);
    }
}
