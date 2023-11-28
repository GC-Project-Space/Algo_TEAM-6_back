package com.example.streaming_service.domain.video.converter;


import com.example.streaming_service.domain.video.dto.VideoResponse;

public class VideoResponseConverter {

    public static VideoResponse.S3UploadDto toS3UploadDto(String uploadId, String newFileName) {
        return VideoResponse.S3UploadDto.builder()
            .uploadId(uploadId)
            .newFileName(newFileName)
            .build();
    }
}
