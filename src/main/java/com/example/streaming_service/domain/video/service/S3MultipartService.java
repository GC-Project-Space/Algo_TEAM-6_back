package com.example.streaming_service.domain.video.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.streaming_service.domain.video.converter.S3ResponseConverter;
import com.example.streaming_service.domain.video.dto.S3Request;
import com.example.streaming_service.domain.video.dto.S3Response;
import com.example.streaming_service.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedUploadPartRequest;
import software.amazon.awssdk.services.s3.presigner.model.UploadPartPresignRequest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for AWS S3 Multipart Upload
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class S3MultipartService {
    private final VideoRepository videoRepository;

    private final S3Client s3Client;
    private final S3Presigner s3Presigner;
    private final AmazonS3Client amazonS3Client;

    public String initiateUpload(
            String originalFileName, String targetBucket, String targetObjectDir
    ) {
        log.info("Bucket: {}, Object: {}", targetBucket, targetObjectDir);

        // 사용자가 보낸 파일 확장자와 현재 시간을 토대로 새로운 파일 이름을 생성한다.
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
        String newFileName = System.currentTimeMillis() + fileType;
        Instant now = Instant.now();

        CreateMultipartUploadRequest createMultipartUploadRequest = CreateMultipartUploadRequest.builder()
                        .bucket(targetBucket)   // 버킷 이름
                        .key(targetObjectDir + "/" + newFileName)   // 파일 경로
                        .acl(ObjectCannedACL.PRIVATE)   // ACL 설정 - Public Read: 모든 사용자가 읽을 수 있음
                        .expires(now.plusSeconds(60 * 20))   // 캐시 만료 시간 설정 - 20분
                        .build();

        // S3 Multipart Upload를 위해 고유 식별 ID를 포함된 답 반환
        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(createMultipartUploadRequest);

        return response.uploadId();
    }

    public String getUploadSignedUrl(
        S3Request.S3UploadSignedUrlDto request,
        String targetBucket, String targetObjectDir
    ) {
        UploadPartRequest uploadPartRequest = UploadPartRequest.builder()
                .bucket(targetBucket)
                .key(targetObjectDir + "/" + request.getFileName())
                .uploadId(request.getUploadId())
                .partNumber(request.getPartNumber())
                .build();

        // 미리 서명된 URL 요청
        UploadPartPresignRequest uploadPartPresignRequest = UploadPartPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .uploadPartRequest(uploadPartRequest)
                .build();

        // 클라이언트에서 S3로 직접 업로드 하기 위해 인증된 URL 활용
        PresignedUploadPartRequest presignedUploadPartRequest = s3Presigner.presignUploadPart(uploadPartPresignRequest);

        return presignedUploadPartRequest.url().toString();
    }

    public S3Response.S3UploadResultDto completeUpload(
            S3Request.S3UploadCompleteDto request,
            String targetBuket, String targetObject
    ) {
        List<CompletedPart> completedParts = new ArrayList<>();

        // 영상에 대한 모든 부분들에 부분 번호와 Etag 설정
        for (S3Request.S3UploadDetailDto partForm : request.getParts()) {
            CompletedPart part = CompletedPart.builder()
                    .partNumber(partForm.getPartNumber())
                    .eTag(partForm.getAwsETag())
                    .build();
            completedParts.add(part);
        }

        // 멀티파트 업로드 완료 요청을 AWS에게 반환
        CompletedMultipartUpload completedMultipartUpload = CompletedMultipartUpload.builder()
                .parts(completedParts)
                .build();

        String fileName = request.getFileName();
        CompleteMultipartUploadRequest completeMultipartUploadRequest = CompleteMultipartUploadRequest.builder()
                .bucket(targetBuket)
                .key(targetObject + "/" + fileName)
                .uploadId(request.getUploadId())
                .multipartUpload(completedMultipartUpload)
                .build();

        CompleteMultipartUploadResponse completeMultipartUploadResponse = s3Client.completeMultipartUpload(completeMultipartUploadRequest);

        String objectKey = completeMultipartUploadResponse.key();
        String url = amazonS3Client.getUrl(targetBuket, objectKey).toString();
        String bucket = completeMultipartUploadResponse.bucket();

        Long fileSize = getFileSizeFromS3Url(bucket, objectKey);

        return S3ResponseConverter.toS3UploadResultDto(fileName, url, fileSize);
    }

    public void abortUpload(
            S3Request.S3UploadDAbortDto request,
            String targetBucket, String targetObjectDir
    ) {
        AbortMultipartUploadRequest abortMultipartUploadRequest = AbortMultipartUploadRequest.builder()
                .bucket(targetBucket)
                .key(targetObjectDir + "/" + request.getUploadId())
                .uploadId(request.getUploadId())
                .build();

        s3Client.abortMultipartUpload(abortMultipartUploadRequest);
    }

    private Long getFileSizeFromS3Url(String bucketName, String fileName) {
        GetObjectMetadataRequest metadataRequest = new GetObjectMetadataRequest(bucketName, fileName);
        ObjectMetadata objectMetadata = amazonS3Client.getObjectMetadata(metadataRequest);
        return objectMetadata.getContentLength();
    }

}
