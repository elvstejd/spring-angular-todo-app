package com.rogex.TodoAppBackend.Dto;

import com.rogex.TodoAppBackend.Entity.Priority;
import com.rogex.TodoAppBackend.Entity.Status;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDateTime dueDate;
}
