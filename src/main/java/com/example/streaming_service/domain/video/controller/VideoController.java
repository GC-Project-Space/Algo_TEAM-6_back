package com.example.streaming_service.domain.video.controller;

import com.example.streaming_service.domain.video.converter.VideoConverter;
import com.example.streaming_service.domain.video.domain.Video;
import com.example.streaming_service.domain.video.dto.VideoRequest;
import com.example.streaming_service.domain.video.dto.VideoResponse;
import com.example.streaming_service.domain.video.service.VideoService;
import com.example.streaming_service.global.common.response.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Video", description = "Video 관련 API")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/Video")
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/Video/{id}")
    public ResponseDto<String> getVideoUrlById(Long id){
        String url = videoService.getVideoUrlById(id);

        return ResponseDto.onSuccess(url);
    }

    @GetMapping("/getActiveVideoUrlInOrder")
    public ResponseDto<List<String>>getActiveVideoUrlInOrder(){
        List<String> activeVideoUrls = videoService.getActiveVideoUrlInOrder();

        return ResponseDto.onSuccess(activeVideoUrls);
    }

}
