package com.example.streaming_service.domain.video.controller;

import com.example.streaming_service.domain.video.converter.VideoResponseConverter;
import com.example.streaming_service.domain.video.dto.VideoRequest;
import com.example.streaming_service.domain.video.dto.VideoResponse;
import com.example.streaming_service.domain.video.service.S3MultipartService;
import com.example.streaming_service.global.common.response.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "S3", description = "AWS S3 Multipart Upload")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/s3")
public class S3MultipartController {

    private final S3MultipartService s3MultipartService;

    @Value("${cloud.aws.s3.bucket}")
    private String videoBucket;

    @PostMapping("/initiate-upload")
    public ResponseDto<VideoResponse.S3UploadDto> initiateUpload(
        @RequestBody VideoRequest.S3UploadInitiateDto request
    ) {
        String uploadId = s3MultipartService.initiateUpload(
            request.getFileName(), videoBucket, "video"
        );

        return ResponseDto.onSuccess(VideoResponseConverter.toS3UploadDto(uploadId, request.getFileName()));
    }
}
