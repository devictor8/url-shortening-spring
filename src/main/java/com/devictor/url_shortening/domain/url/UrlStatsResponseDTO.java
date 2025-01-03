package com.devictor.url_shortening.domain.url;

import java.time.LocalDateTime;

public record UrlStatsResponseDTO(Long id, String url, String shortCode, LocalDateTime createAt, LocalDateTime updatedAt, Long accessCount) {
    public static UrlStatsResponseDTO createDTO(Url urlData) {
        return new UrlStatsResponseDTO(urlData.getId(), urlData.getUrl(), urlData.getShortCode(), urlData.getCreatedAt(), urlData.getUpdatedAt(), urlData.getAccessCount());
    }
}
