package com.example.streaming_service.global.common.code;

import com.example.streaming_service.global.common.response.ResponseDto;

public interface BaseErrorCode {

    public ResponseDto.ErrorReasonDto getReason();

    public ResponseDto.ErrorReasonDto getReasonHttpStatus();

}
