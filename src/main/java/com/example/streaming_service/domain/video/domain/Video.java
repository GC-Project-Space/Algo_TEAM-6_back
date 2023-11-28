package com.example.streaming_service.domain.video.domain;


import com.example.streaming_service.global.common.entity.BaseEntity;
import com.example.streaming_service.global.constant.Status;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "VIDEO")
public class Video extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fileName", nullable = false)
    private String fileName;

    @Column(name = "videoUrl", nullable = false)
    private String videoUrl;

    @Column(name = "thumbnailUrl", nullable = false)
    private String thumbnailUrl;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
