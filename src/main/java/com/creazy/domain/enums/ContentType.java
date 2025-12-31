package com.creazy.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ContentType {
    REEL, CAROUSEL,TWEET_POST, STORY, LIVE;


    /**
     *Making enums case-insensitive
     */
    @JsonCreator
    public static ContentType fromString(String value){
        return ContentType.valueOf(value.toUpperCase());
    }
}
