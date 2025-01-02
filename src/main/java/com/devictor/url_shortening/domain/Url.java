package com.devictor.url_shortening.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Url {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String url;
    @Column(unique = true)
    private String shortCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long accessCount;

    public Url(String url, String shortCode) {
        this.url = url;
        this.shortCode = shortCode;
        accessCount = 0L;
    }

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
