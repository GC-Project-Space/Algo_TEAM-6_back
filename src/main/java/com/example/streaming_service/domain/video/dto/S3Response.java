package com.example.streaming_service.domain.video.dto;

import lombok.Builder;
import lombok.Getter;

public class S3Response {

    @Getter
    @Builder
    public static class S3UploadDto {
        private String uploadId;
        private String newFileName;
    }

    @Getter
    @Builder
    public static class S3SignedUrlDto {
        private String signedUrl;
    }

    @Getter
    @Builder
    public static class S3UploadResultDto {
        private String fileName;
        private String videoUrl;
        private Long size;
    }
}
