package com.mstareas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mstareas.data.TareasRepository;
import com.mstareas.model.pojo.Tasks;
import com.mstareas.model.request.CreateTaskResquest;

import java.util.List;

@Service
public class TareasServiceImpl implements TareasService {

    @Autowired
    private TareasRepository repository;

    @Override
    public List<Tasks> getTasks(){

        List<Tasks> tasks = repository.findAll();
        return tasks.isEmpty() ? null : tasks;
    }

    @Override
    public Tasks getTask(String taskId){
        return repository.findById(Long.valueOf(taskId)).orElse(null);
    }
    
    @Override
    public Boolean removeTask(String taskId){

        Tasks task = repository.findById(Long.valueOf(taskId)).orElse(null);

        if (task != null){
            repository.delete(task);
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    @Override
    public Tasks updateTask(String taskId, CreateTaskResquest request){

        Tasks task = repository.findById(Long.valueOf(taskId)).orElse(null);

        if (task != null && request != null){
            if (request.getName() != null && !request.getName().trim().isEmpty()){
                task.setName(request.getName());
            }

            if (request.getDescription() != null){
                task.setDescription(request.getDescription());
            }

            return repository.save(task);
        }

        return null;
    } 
    
    @Override
    public Tasks createTask(CreateTaskResquest request){

        String name = request.getName();
        String description = request.getDescription();
        
        if (request != null && StringUtils.hasText(name.trim()) 
        && StringUtils.hasText(description.trim())){

            Tasks task = Tasks.builder().name(name.trim()
            ).description(description.trim()).build();

            return repository.save(task);
         } else{
            return null;
         }

    }
    

}
