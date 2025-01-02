package com.devictor.url_shortening.repository;

import com.devictor.url_shortening.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UrlRepository extends JpaRepository<Url, Long> {
    boolean findByUrl(String url);

    boolean findByShortCode(String shortCode);
}
