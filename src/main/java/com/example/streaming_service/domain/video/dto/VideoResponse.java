package com.example.streaming_service.domain.video.dto;

import lombok.Builder;
import lombok.Getter;

public class VideoResponse {

    @Getter
    @Builder
    public static class S3UploadDto {
        private String uploadId;
        private String newFileName;
    }

    @Getter
    @Builder
    public static class S3PresignedUrlDto {
        private String presignedUrl;
    }

    @Getter
    @Builder
    public static class S3CompleteDto {
        private String fileName;
        private String videoUrl;
        private String thumbnailUrl;
        private String size;
    }
}
