package com.storyverse.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_gen")
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "post_seq", allocationSize = 1)
    private Long postId;

    private String title;

    @Column(name = "USER_ID")
    private String userId;

    @Column(length = 1000)
    private String postContent;

    private String postDate;

    @Column(name = "IS_OPEN", nullable = false)
    private boolean isOpen;
}
