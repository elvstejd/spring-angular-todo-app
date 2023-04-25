package com.rogex.TodoAppBackend.Repository;

import com.rogex.TodoAppBackend.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Integer> {
}
