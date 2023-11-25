package com.example.streaming_service.global.common.exception;

import com.example.streaming_service.global.common.code.BaseErrorCode;
import com.example.streaming_service.global.common.response.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;

    public ResponseDto.ErrorReasonDto getErrorReason() {
        return this.code.getReason();
    }

    public ResponseDto.ErrorReasonDto getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }

}