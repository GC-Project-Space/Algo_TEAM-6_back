package com.example.streaming_service.domain.video.service;

import com.example.streaming_service.domain.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadRequest;
import software.amazon.awssdk.services.s3.model.CreateMultipartUploadResponse;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.time.Instant;

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

    public String initiateUpload(
            String originalFileName, String targetBucket, String targetObjectDir
    ) {
        // 사용자가 보낸 파일 확장자와 현재 시간을 토대로 새로운 파일 이름을 생성한다.
        String fileType = originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
        String newFileName = System.currentTimeMillis() + fileType;
        Instant now = Instant.now();

        CreateMultipartUploadRequest createMultipartUploadRequest =
                CreateMultipartUploadRequest.builder()
                        .bucket(targetBucket)   // 버킷 이름
                        .key(targetObjectDir + "/" + newFileName)   // 파일 경로
                        .acl(ObjectCannedACL.PUBLIC_READ)   // ACL 설정 - Public Read: 모든 사용자가 읽을 수 있음
                        .expires(now.plusSeconds(60 * 20))   // 캐시 만료 시간 설정 - 20분
                        .build();

        CreateMultipartUploadResponse response = s3Client.createMultipartUpload(createMultipartUploadRequest);

        return response.uploadId();
    }



}
