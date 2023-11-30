package com.example.streaming_service.domain.video.service;

import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.domain.video.repository.VideoRepository;
import com.example.streaming_service.global.constant.Status;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Example
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public String getVideoUrlById(Long id){
        Video video = videoRepository.findById(id).orElseThrow();

        return video.getVideoUrl();
    }

    public List<String> getActiveVideoUrlInOrder(){
        List<Video> videos = videoRepository.findByStatusOrderByCreatedAtDesc(Status.ACTIVE);

        return videos.stream()
                .map(Video::getVideoUrl)
                .collect(Collectors.toList());
    }

}
