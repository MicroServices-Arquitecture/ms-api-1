package com.mstareas.service;

import org.springframework.stereotype.Service;

import com.mstareas.model.pojo.Tasks;
import com.mstareas.model.request.CreateTaskResquest;

import java.util.List;

@Service
public interface TareasService {

    List<Tasks> getTasks();

    Tasks getTask(String taskId);

    Tasks getName(String taskName);

    Boolean removeTask(String taskId);

    Tasks createTask(CreateTaskResquest request);

    Tasks updateTask(String taskId, CreateTaskResquest request);
  
}
