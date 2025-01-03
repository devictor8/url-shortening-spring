package com.devictor.url_shortening.services;

import com.devictor.url_shortening.domain.url.Url;
import com.devictor.url_shortening.domain.url.UrlResponseDTO;
import com.devictor.url_shortening.exceptions.InvalidUrlException;
import com.devictor.url_shortening.exceptions.InvalidShortCodeException;
import com.devictor.url_shortening.exceptions.UrlAlreadyInUseException;
import com.devictor.url_shortening.exceptions.UrlNotFoundException;
import com.devictor.url_shortening.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public UrlResponseDTO shortenURL(String url) {
        if (url == null || url.isEmpty()) {
            throw new InvalidUrlException("URL cannot be empty.");
        }

        if (!validateUrl(url)) {
            throw new InvalidUrlException("URL format is invalid.");
        }

        Url urlObject;
        if ((urlObject = urlRepository.findUrlByUrl(url)) != null) {
            return UrlResponseDTO.createDTO(urlObject);
        }

        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (urlRepository.findUrlByShortCode(shortCode) != null);

        urlObject = new Url(url, shortCode);

        Url urlData = urlRepository.save(urlObject);

        return UrlResponseDTO.createDTO(urlData);
    }

    public UrlResponseDTO getUrlByShortCode(String shortCode) {
        if (shortCode == null || shortCode.isEmpty()) {
            throw new InvalidShortCodeException("ShortCode cannot be empty.");
        }

        Url urlData = urlRepository.findUrlByShortCode(shortCode);
        if (urlData == null) {
            throw new UrlNotFoundException();
        }

        urlData.urlVisited();
        urlRepository.save(urlData);
        return UrlResponseDTO.createDTO(urlData);
    }

    public UrlResponseDTO updateUrl(String shortCode, String newUrl) {
        if (shortCode == null || shortCode.isEmpty()) {
            throw new InvalidShortCodeException("shortCode cannot be empty.");
        }

        if (newUrl == null || newUrl.isEmpty()) {
            throw new InvalidUrlException("newURL cannot be empty.");
        }

        if(!validateUrl(newUrl)) {
            throw new InvalidUrlException("URL format is invalid.");
        }

        if (urlRepository.findUrlByUrl(newUrl) != null) {
            throw new UrlAlreadyInUseException("Url is already shorted by another shortCode");
        }

        Url url = urlRepository.findUrlByShortCode(shortCode);
        if (url == null) {
            throw new UrlNotFoundException("No Url has this shortCode");
        }

        url.setUrl(newUrl);
        Url urlUpdated = urlRepository.save(url);
        return UrlResponseDTO.createDTO(urlUpdated);
    }

    public void DeleteShortUrl(String shortCode) {
        if (shortCode == null || shortCode.isEmpty()) {
            throw new InvalidShortCodeException("ShortCode cannot be empty.");
        }

        Url url = urlRepository.findUrlByShortCode(shortCode);

        if (url ==  null) {
            throw new UrlNotFoundException("No url has this shortCode");
        }

        urlRepository.delete(url);
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
