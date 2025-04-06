package com.storyverse.service;

import com.storyverse.DTO.SignupRequest;
import com.storyverse.domain.AppUser;
import com.storyverse.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    // 로그인 검증
    public boolean validate(String userId, String rawPassword) {
        Optional<AppUser> optionalUser = appUserRepository.findByUserId(userId);
        if (optionalUser.isEmpty()) {
            return false;
        }

        AppUser user = optionalUser.get();
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // 유저 조회
    public AppUser getUserByUserId(String userId) {
        return appUserRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
    }
    
    public void register(SignupRequest request) {
        appUserRepository.findByUserId(request.getUserId()).ifPresent(user -> {
            throw new IllegalArgumentException("이미 존재하는 사용자 ID입니다.");
        });

        AppUser newUser = AppUser.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .nickname(request.getNickname())
                .birthDate(request.getBirthDate())
                .build();

        appUserRepository.save(newUser);
    }
}
