package com.mstareas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mstareas.model.pojo.Tasks;
import com.mstareas.model.request.CreateTaskResquest;
import com.mstareas.service.TareasService;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
public class TareasController {

    private final TareasService service;

    @GetMapping("/tasks")
    @Operation(summary = "Show all Task", description = "Show all Task in BD")
    public ResponseEntity<List<Tasks>> getTasks() {

        List<Tasks> tasks = service.getTasks();

        if (tasks != null){
            return ResponseEntity.ok(tasks);
        } else{
            return ResponseEntity.ok(Collections.emptyList());
        }
    }

    @GetMapping("/tasks/id/{taskId}")
    @Operation(summary = "Get record for id", description = "Show 1 task pro id")
    public ResponseEntity<Tasks> getTask(@PathVariable String taskId) {
        
        Tasks task = service.getTask(taskId);

        if (task != null){
            return ResponseEntity.ok(task);
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tasks/name/{name}")
    @Operation(summary = "Get record for name", description = "Show 1 task pro name")
    public ResponseEntity<Tasks> getName(@RequestParam String taskName) {
        
        Tasks task = service.getName(taskName);

        if (task != null){
            return ResponseEntity.ok(task);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/tasks/{taskId}")
    @Operation(summary = "Delete a record pro id")
    public ResponseEntity<Void> deleteTask(@PathVariable String taskId){

        Boolean removed = service.removeTask(taskId);

        if (Boolean.TRUE.equals(removed)){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/tasks")
    @Operation(summary = "Created a new record")
    public ResponseEntity<Tasks> createTask(@RequestBody CreateTaskResquest request) {
        
        Tasks createTask = service.createTask(request);

        if (createTask != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createTask);
        } else{
               return ResponseEntity.notFound().build();
        } 
    }

    @PutMapping("/tasks/{taskId}")
    @Operation(summary = "Update a record pro id")
    public ResponseEntity<Tasks> updateTask(@PathVariable String taskId, @RequestBody CreateTaskResquest request) {
        
        Tasks updateTask = service.updateTask(taskId, request);

        if (updateTask != null){
            return ResponseEntity.ok(updateTask);
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    
    

    // public TareasController(TareasService tareasService) {
    //     this.tareasService = tareasService;
    // }

    // @GetMapping
    // public List<Tareas> listarTareas() {
    //     return tareasService.obtenerTodos();
    // }

    // @GetMapping("/{id}")
    // public Optional<tareas> obtenerTareaPorId(@PathVariable Long id) {
    //     return tareasService.buscarPorId(id);
    // }

    // @GetMapping("/buscar")
    // public List<tareas> buscarPorNombre(@RequestParam String name) {
    //     return tareasService.buscarPorNombre(name);
    // }
}
