package com.example.streaming_service.domain.video.controller;

import com.example.streaming_service.domain.video.converter.VideoResponseConverter;
import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.domain.video.dto.VideoResponse;
import com.example.streaming_service.domain.video.service.VideoService;
import com.example.streaming_service.global.common.response.ResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Video", description = "Video 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/Video")
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/{id}")
    public ResponseDto<VideoResponse.VideoDto> getVideoUrlById(
            @RequestParam("id") Long id
    ){
        Video video = videoService.getVideoById(id);

        return ResponseDto.onSuccess(VideoResponseConverter.toVideoDto(video));
    }

    @GetMapping("/List")
    public ResponseDto<List<VideoResponse.VideoDto>>getActiveVideoUrlInOrder(){
        List<Video> activeVideoUrls = videoService.getActiveVideoUrlInOrder();

        return ResponseDto.onSuccess(VideoResponseConverter.toVideoDtoList(activeVideoUrls));
    }

}
