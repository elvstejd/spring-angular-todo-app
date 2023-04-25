package com.rogex.TodoAppBackend.Service;

import com.rogex.TodoAppBackend.Dto.TaskRequest;
import com.rogex.TodoAppBackend.Entity.Tasks;
import com.rogex.TodoAppBackend.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Tasks> getAllTasks() {
        return taskRepository.findAll();
    }

    public Tasks getTaskById(Integer id) {
        return taskRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Tasks addTask(TaskRequest taskRequest) {
        Tasks newTask = Tasks.builder()
                .title(taskRequest.getTitle())
                .description(taskRequest.getDescription())
                .status(taskRequest.getStatus())
                .priority(taskRequest.getPriority())
                .dueDate(taskRequest.getDueDate())
                .build();

        return taskRepository.save(newTask);
    }

    public ResponseEntity<Tasks> updateTask(Integer id, TaskRequest taskRequest) {
        Tasks currentTask = getTaskById(id);

        if(currentTask != null) {
            Tasks existingTask = currentTask;

            if (taskRequest.getTitle() != null)
                existingTask.setTitle(taskRequest.getTitle());
            if (taskRequest.getDescription() != null)
                existingTask.setDescription(taskRequest.getDescription());
            if (taskRequest.getStatus() != null)
                existingTask.setStatus(taskRequest.getStatus());
            if (taskRequest.getPriority() != null)
                existingTask.setPriority(taskRequest.getPriority());
            if (taskRequest.getDueDate() != null)
                existingTask.setDueDate(taskRequest.getDueDate());

            return new ResponseEntity<>(taskRepository.save(existingTask), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
