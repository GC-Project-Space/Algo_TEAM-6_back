package com.example.streaming_service.domain.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class S3Request {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class S3UploadInitiateDto {
        private String fileName;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class S3UploadSignedUrlDto {
        private String fileName;
        private Integer partNumber;
        private String uploadId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class S3UploadCompleteDto {
        private List<S3UploadDetailDto> parts;
        private String fileName;
        private String uploadId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class S3UploadDetailDto {
        private Integer partNumber;
        private String awsETag;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class S3UploadDAbortDto {
        private String fileName;
        private String uploadId;
    }
}
