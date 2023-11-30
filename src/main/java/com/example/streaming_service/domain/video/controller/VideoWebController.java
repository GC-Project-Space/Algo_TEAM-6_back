package com.example.streaming_service.domain.video.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VideoWebController {

    @GetMapping("/v2/video/")
    public String multipartUpload() {
        return "multipart-upload-s3";
    }
}
