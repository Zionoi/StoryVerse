package com.storyverse.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
    private String title;
    private String postContent;
    private boolean isOpen;
    private String postDate;
}