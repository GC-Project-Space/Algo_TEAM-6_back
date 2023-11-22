package com.example.streaming_service.domain.example.converter;


import com.example.streaming_service.domain.example.domain.Example;
import com.example.streaming_service.domain.example.dto.ExampleRequest;
import com.example.streaming_service.domain.example.dto.ExampleResponse;
import com.example.streaming_service.global.constant.Status;

public class ExampleConverter {

    public static ExampleResponse.ExampleCreateDto toExampleResponse(
            String massage
    ) {
        return ExampleResponse.ExampleCreateDto.builder()
                .massage("Example 객체 생성에 성공했습니다.")
                .massageInExample(massage)
                .build();
    }

    public static Example toExample(
            ExampleRequest.ExampleCreateDto exampleCreateDto
    ) {
        return Example.builder()
                .massage(exampleCreateDto.getMassage())
                .status(Status.ACTIVE)
                .build();
    }
}
