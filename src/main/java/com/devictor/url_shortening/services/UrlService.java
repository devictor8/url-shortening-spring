package com.devictor.url_shortening.services;

import com.devictor.url_shortening.domain.url.Url;
import com.devictor.url_shortening.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url shortenURL(String url) {
        if(url == null || url.isEmpty()) {
            throw new IllegalArgumentException("URL cannot be empty");
        }

        if(urlRepository.findUrlByUrl(url) != null) {
            throw new IllegalArgumentException("URL already exists");
        }

        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (urlRepository.findUrlByShortCode(shortCode) != null);

        Url newUrl = new Url(url, shortCode);
        return urlRepository.save(newUrl);
    }

    private String generateShortCode() {
        StringBuilder shortCode = new StringBuilder();
        String charSet = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(charSet.length());
            shortCode.append(charSet.charAt(index));
        }
        return shortCode.toString();
    }
}
