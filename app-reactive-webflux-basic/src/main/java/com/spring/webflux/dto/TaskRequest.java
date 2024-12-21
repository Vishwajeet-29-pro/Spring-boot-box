package com.spring.webflux.dto;

import com.spring.webflux.model.Status;
import com.spring.webflux.model.Task;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private Status status;

    public static Task toTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        return task;
    }
}
