package com.example.streaming_service.global.constant;

import lombok.Getter;

@Getter
public enum Status {
    ACTIVE("활성화"),
    INACTIVE("비 활성화"),
    DELETED("삭제");

    private final String description;

    Status(String description) {
        this.description = description;
    }
}
