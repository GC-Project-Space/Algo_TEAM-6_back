package com.example.streaming_service.global.common.code;

import com.example.streaming_service.global.common.response.ResponseDto;

public interface BaseCode {

    public ResponseDto.ReasonDto getReason();

    public ResponseDto.ReasonDto getReasonHttpStatus();

}
