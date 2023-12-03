package com.example.streaming_service.domain.video.dto;

import lombok.Builder;
import lombok.Getter;

public class VideoResponse {

    @Getter
    @Builder
    public static class VideoDto {
        private Long id;
        private String fileName;
        private String videoUrl;
        private String size;
    }
}
