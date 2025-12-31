package com.creazy.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Platform {
    INSTAGRAM, TIKTOK, FACEBOOK, YOUTUBE;

    /**
     *Making enums case-insensitive
     */
    @JsonCreator
    public static Platform fromString(String value){
        return Platform.valueOf(value.toUpperCase());
    }
}
