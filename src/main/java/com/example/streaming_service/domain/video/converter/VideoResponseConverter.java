package com.example.streaming_service.domain.video.converter;

import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.domain.video.dto.VideoResponse;

import java.util.List;
import java.util.stream.Collectors;

public class VideoResponseConverter {

    public static List<VideoResponse.VideoDto> toVideoDtoList(List<Video> videos) {
        return videos.stream()
                .map(VideoResponseConverter::toVideoDto)
                .collect(Collectors.toList());
    }

    public static VideoResponse.VideoDto toVideoDto(Video video) {
        return VideoResponse.VideoDto.builder()
                .id(video.getId())
                .fileName(video.getFileName())
                .videoUrl(video.getVideoUrl())
                .size(video.getSize())
                .build();
    }
}
