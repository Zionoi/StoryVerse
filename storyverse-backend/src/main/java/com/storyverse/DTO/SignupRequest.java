package com.storyverse.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SignupRequest {
    private String userId;
    private String password;
    private String fullName;
    private String nickname;
    private LocalDate birthDate;
}
