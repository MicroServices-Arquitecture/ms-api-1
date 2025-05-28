package com.mstareas.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mstareas.model.pojo.Tasks;

import java.util.Optional;

public interface TareasRepository extends JpaRepository<Tasks, Long> {
    Optional<Tasks> findByName(String name);
}