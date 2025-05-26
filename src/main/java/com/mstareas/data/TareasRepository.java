package com.mstareas.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mstareas.model.pojo.Tasks;

import java.util.List;

public interface TareasRepository extends JpaRepository<Tasks, Long> {
    List<Tasks> findByName(String name);
}