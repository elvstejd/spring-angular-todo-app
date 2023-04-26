package com.rogex.TodoAppBackend.Controllers;

import com.rogex.TodoAppBackend.Dto.TaskRequest;
import com.rogex.TodoAppBackend.Entity.Tasks;
import com.rogex.TodoAppBackend.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Tasks> getAllTasks(){
        return taskService.getAllTasks();
    }
    @PostMapping
    public ResponseEntity<Tasks> createTask(@RequestBody TaskRequest  taskRequest){
        return new ResponseEntity<>(taskService.addTask(taskRequest), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Tasks> updateTask(@PathVariable("id") Integer id, @RequestBody Tasks taskRequest){
        return taskService.updateTask(id, taskRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Integer id){
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
