package com.example.streaming_service.domain.video.repository;

import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.global.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByStatusOrderByCreatedAtDesc(Status status);

}
