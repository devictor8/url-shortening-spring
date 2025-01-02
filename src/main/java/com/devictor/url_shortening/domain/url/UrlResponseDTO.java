package com.devictor.url_shortening.domain.url;

import java.time.LocalDateTime;

public record UrlResponseDTO(Long id, String url, String shortCode, LocalDateTime createAt, LocalDateTime updatedAt) {
    public static UrlResponseDTO createDTO(Url urlData) {
        return new UrlResponseDTO(urlData.getId(), urlData.getUrl(), urlData.getShortCode(), urlData.getCreatedAt(), urlData.getUpdatedAt());
    }
}
