package com.creazy.domain.entity;


import com.creazy.domain.enums.ContentType;
import com.creazy.domain.enums.Platform;
import com.creazy.domain.enums.TargetEmotion;
import jakarta.persistence.*;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Object class
 */
@Entity
@Table(name = "content_posts")
public class ContentPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //Auto generated, not part of the constructor

    private String title; //Post title or headline

    private String idea; //Post idea/ description

    //Metadata
    @Enumerated(EnumType.STRING)
    private Platform platform;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    @Enumerated(EnumType.STRING)
    private TargetEmotion targetEmotion;

    private LocalDateTime plannedDate;

    //Audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Default constructor
    public ContentPost(){}

    public ContentPost(String title, String idea, Platform platform, ContentType contentType,
                       TargetEmotion targetEmotion, LocalDateTime plannedDate, LocalDateTime createdAt,
                       LocalDateTime updatedAt) {
        this.title = title;
        this.idea = idea;
        this.platform = platform;
        this.contentType = contentType;
        this.targetEmotion = targetEmotion;
        this.plannedDate = plannedDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    //Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public TargetEmotion getTargetEmotion() {
        return targetEmotion;
    }

    public void setTargetEmotion(TargetEmotion targetEmotion) {
        this.targetEmotion = targetEmotion;
    }

    public LocalDateTime getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(LocalDateTime plannedDate) {
        this.plannedDate = plannedDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContentPost that = (ContentPost) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(idea, that.idea) && platform == that.platform && contentType == that.contentType && targetEmotion == that.targetEmotion && Objects.equals(plannedDate, that.plannedDate) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, idea, platform, contentType, targetEmotion, plannedDate, createdAt, updatedAt);
    }

    /**
     * Called automatically by JPA before the entity is inserted into the database
     * Sets createdAt and updatedAt for new records.
     */
    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Called automatically by JPA before the entity is updated.
     * Updates the UpdatedAt timestamp.
     */
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
