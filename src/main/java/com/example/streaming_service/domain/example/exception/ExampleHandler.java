package com.example.streaming_service.domain.example.exception;

import com.example.streaming_service.global.error.exception.ErrorCode;
import com.example.streaming_service.global.error.exception.GeneralException;

public class ExampleHandler extends GeneralException {

    public ExampleHandler(ErrorCode errorCode) {
        super(errorCode);
    }
}
