package com.example.streaming_service.domain.video.converter;


import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.global.constant.Status;

public class VideoConverter {

    public static Video toVideo(
            String fileName, String videoUrl, String size
    ) {
        return Video.builder()
                .fileName(fileName)
                .videoUrl(videoUrl)
                .size(size)
                .status(Status.ACTIVE)
                .build();
    }
}
