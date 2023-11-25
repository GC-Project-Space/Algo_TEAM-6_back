package com.example.streaming_service.domain.example.exception;

import com.example.streaming_service.global.common.code.status.ErrorStatus;
import com.example.streaming_service.global.common.exception.GeneralException;

public class ExampleHandler extends GeneralException {

    public ExampleHandler(ErrorStatus errorStatus) {
        super(errorStatus);
    }
}
