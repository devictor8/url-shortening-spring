package com.devictor.url_shortening.services;

import com.devictor.url_shortening.domain.url.Url;
import com.devictor.url_shortening.exceptions.InvalidUrlExeceptions;
import com.devictor.url_shortening.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url shortenURL(String url) {
        if(url == null || url.isEmpty()) {
            throw new InvalidUrlExeceptions("URL cannot be empty");
        }

        if(!validateUrl(url)) {
            throw new InvalidUrlExeceptions("URL format is invalid");
        }

        Url urlObject;
        if((urlObject = urlRepository.findUrlByUrl(url)) != null) {
            return urlObject;
        }

        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (urlRepository.findUrlByShortCode(shortCode) != null);

        urlObject = new Url(url, shortCode);
        return urlRepository.save(urlObject);
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

    private boolean validateUrl(String url) {
        Pattern urlPattern = Pattern.compile("^(https?|ftp)://[^\\s/$.?#].[^\\s]*$");
        return urlPattern.matcher(url).matches();
    }
}
