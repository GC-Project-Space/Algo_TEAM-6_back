package com.example.streaming_service.domain.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class VideoRequest {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class S3UploadInitiateDto {
        private String fileName;
    }
}
