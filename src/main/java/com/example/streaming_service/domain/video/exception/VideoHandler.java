package com.example.streaming_service.domain.video.exception;

import com.example.streaming_service.global.common.code.status.ErrorStatus;
import com.example.streaming_service.global.common.exception.GeneralException;

public class VideoHandler extends GeneralException {

    public VideoHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
