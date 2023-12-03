package com.example.streaming_service.domain.video.service;

import com.example.streaming_service.domain.video.converter.VideoConverter;
import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.domain.video.repository.VideoRepository;
import com.example.streaming_service.global.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for Example
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;


    public List<Video> getActiveVideoUrlInOrder(){
        return videoRepository.findByStatusOrderByCreatedAtDesc(Status.ACTIVE);
    }

    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void saveVideo(String fileName, String url, String string) {
        Video video = VideoConverter.toVideo(fileName, url, string);
        videoRepository.save(video);
    }
}
