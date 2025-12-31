package com.creazy.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TargetEmotion {
    JOY, RELATABLE, EXCITEMENT, CURIOSITY, SURPRISE, AUTHORITY;


    /**
     *Making enums case-insensitive
     */
    @JsonCreator
    public static TargetEmotion fromString(String value){
        return TargetEmotion.valueOf(value.toUpperCase());
    }
}
