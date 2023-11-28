package com.example.streaming_service.domain.video.repository;

import com.example.streaming_service.domain.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {

}
