package com.Proteccion.taskColab.TareasColaborativo.Repository;

import com.Proteccion.taskColab.TareasColaborativo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedToId(Long userId);

    Task findByTitle(String title);
}
