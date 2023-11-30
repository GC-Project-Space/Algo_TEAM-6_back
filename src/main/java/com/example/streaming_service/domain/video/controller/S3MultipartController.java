package com.example.streaming_service.domain.video.controller;

import com.example.streaming_service.domain.video.converter.S3ResponseConverter;
import com.example.streaming_service.domain.video.dto.S3Request;
import com.example.streaming_service.domain.video.dto.S3Response;
import com.example.streaming_service.domain.video.service.S3MultipartService;
import com.example.streaming_service.global.common.response.ResponseDto;
import com.example.streaming_service.global.config.aws.S3Config;
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

    /**
     * Multipart Upload start
     * - /s3/initiate-upload
     * @param request : S3Request.S3UploadInitiateDto
     * @return S3Response.S3UploadDto
     */
    @PostMapping("/initiate-upload")
    public ResponseDto<S3Response.S3UploadDto> initiateUpload(
        @RequestBody S3Request.S3UploadInitiateDto request
    ) {
        String uploadId = s3MultipartService.initiateUpload(
            request.getFileName(), videoBucket, S3Config.videoFolder
        );

        return ResponseDto.onSuccess(S3ResponseConverter.toS3UploadDto(uploadId, request.getFileName()));
    }

    /**
     * Multipart Upload를 위한 서명(인증)된 URL 발급
     * @param request : S3Request.S3UploadSignedUrlDto
     * @return S3Response.S3SignedUrlDto
     */
    @PostMapping("/upload-signed-url")
    public ResponseDto<S3Response.S3SignedUrlDto> getUploadSignedUrl(
            @RequestBody S3Request.S3UploadSignedUrlDto request
    ) {
        String signedUrl = s3MultipartService.getUploadSignedUrl(
                request, videoBucket, S3Config.videoFolder
        );

        return ResponseDto.onSuccess(S3ResponseConverter.toS3SignedUrlDto(signedUrl));
    }

    /**
     * Multipart Upload 완료 요청
     * @param request : S3Request.S3UploadCompleteDto
     * @return S3Response.S3UploadResultDto
     */
    @PostMapping("/complete-upload")
    public ResponseDto<S3Response.S3UploadResultDto> completeUpload(
            @RequestBody S3Request.S3UploadCompleteDto request
    ) {
        return ResponseDto.onSuccess(s3MultipartService.completeUpload(
                request, videoBucket, S3Config.videoFolder
        ));
    }

    /**
     * Multipart Upload 중단 요청
     * @param request : S3Request.S3UploadDAbortDto
     * @return String
     */
    @PostMapping("/abort-upload")
    public ResponseDto<String> abortUpload(
            @RequestBody S3Request.S3UploadDAbortDto request
    ) {
        s3MultipartService.abortUpload(
                request, videoBucket, S3Config.videoFolder
        );

        return ResponseDto.onSuccess(request.getFileName() + "의 업로드 중지에 성공했습니다.");
    }
}
