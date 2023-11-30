package com.example.streaming_service.domain.video.converter;


import com.example.streaming_service.domain.video.dto.S3Response;

public class S3ResponseConverter {

    public static S3Response.S3UploadDto toS3UploadDto(
            String uploadId, String newFileName
    ) {
        return S3Response.S3UploadDto.builder()
            .uploadId(uploadId)
            .newFileName(newFileName)
            .build();
    }

    public static S3Response.S3UploadResultDto toS3UploadResultDto(
            String fileName, String url, Long fileSize
    ) {
        return S3Response.S3UploadResultDto.builder()
                .fileName(fileName)
                .videoUrl(url)
                .size(fileSize)
                .build();
    }

    public static S3Response.S3SignedUrlDto toS3SignedUrlDto(
            String signedUrl
    ) {
        return S3Response.S3SignedUrlDto.builder()
                .signedUrl(signedUrl)
                .build();
    }
}
