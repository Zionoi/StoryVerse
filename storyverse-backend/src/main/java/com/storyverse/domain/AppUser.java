package com.storyverse.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId; // 로그인 ID

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName; // 이름

    @Column
    private String nickname;

    @Column
    private LocalDate birthDate; // 생일

    @Column(updatable = false)
    private LocalDateTime createdAt; // 가입 날짜

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "app_user_friends", joinColumns = @JoinColumn(name = "app_user_id"))
    @Column(name = "friend_user_id")
    private List<String> friends; // 친구 목록
    
    
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

