package com.rogex.TodoAppBackend.Dto;

import com.rogex.TodoAppBackend.Entity.Priority;
import com.rogex.TodoAppBackend.Entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequest {

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private LocalDateTime dueDate;
}
