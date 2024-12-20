package com.spring.webflux.dto;

import com.spring.webflux.model.Status;
import com.spring.webflux.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static TaskResponse toTaskResponse(Task task) {
        return new TaskResponse(
                task.getId(), task.getTitle(),
                task.getDescription(), task.getStatus(),
                task.getCreatedAt(), task.getUpdatedAt()
        );
    }
}
