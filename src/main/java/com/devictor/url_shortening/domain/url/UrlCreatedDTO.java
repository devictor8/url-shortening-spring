package com.devictor.url_shortening.domain.url;

import java.time.LocalDateTime;

public record UrlCreatedDTO(Long id, String url, String shortCode, LocalDateTime createAt, LocalDateTime updatedAt) {
    public static UrlCreatedDTO createdDTO(Url urlData) {
        return new UrlCreatedDTO(urlData.getId(), urlData.getUrl(), urlData.getShortCode(), urlData.getCreatedAt(), urlData.getUpdatedAt());
    }
}
