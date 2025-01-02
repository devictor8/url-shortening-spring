package com.devictor.url_shortening.repository;

import com.devictor.url_shortening.domain.url.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findUrlByUrl(String url);

    Url findUrlByShortCode(String shortCode);
}
