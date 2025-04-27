package com.myproject.transparentmoney.user.model;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_settings", schema = "transparentmoney")
@Data
@NoArgsConstructor
@AllArgsConstructor
class UserSettings {

    @Id
    @JsonIgnore
    @Column(name = "user_id")
    private UUID userId;

    /**
     * Bidirectional link back to User; shares primary key via MapsId.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "pin_enabled", nullable = false)
    private boolean pinEnabled = false;

    @Column(name = "biometric_enabled", nullable = false)
    private boolean biometricEnabled = false;

    @Column(name = "timezone", nullable = false)
    private String timezone = "UTC";

    @Column(name = "encryption_key_id")
    private String encryptionKeyId;

    @Column(name = "theme", nullable = false)
    private String theme = "light";

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "created_at", updatable = false, nullable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", timezone = "UTC")
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}