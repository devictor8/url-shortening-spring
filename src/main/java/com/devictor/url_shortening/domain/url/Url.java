package com.devictor.url_shortening.domain.url;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@SequenceGenerator(name = "url_id_seq", allocationSize = 1)
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_id_seq")
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
        this.accessCount = 0L;
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
