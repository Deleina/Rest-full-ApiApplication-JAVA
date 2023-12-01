package com.project.app.rest.Crontroler;

import com.project.app.rest.Model.Task;
import com.project.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoControl {
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/")
    public String holamundo (){

        return "HOLA MUNDO :D!";
    }

    @GetMapping(value = "/tasks")
    public List<Task> getTasks(){

        return todoRepository.findAll();
    }

    @PostMapping(value = "/savetasks")
    public  String saveTask(@RequestBody Task task){
        todoRepository.save(task);
        return "Save task";
    }

    @PutMapping(value = "/update/{id}")
    public String updateTask(@PathVariable long id, @RequestBody Task task){
        Task updatedTask = todoRepository.findById(id).get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        todoRepository.save(updatedTask);
        return "update Task";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable long id){
        Task deletedTask = todoRepository.findById(id).get();
        todoRepository.delete(deletedTask);
        return "deleted Task";
    }

}
