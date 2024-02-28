package com.todo.backend.repository;

import com.todo.backend.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    //가장 최신 데이터 시간 찾기
    @Query("SELECT MAX(lastData) FROM TodoEntity")
    LocalDateTime findLastData(String userId);

    List<TodoEntity> findByUserId(String userId);
}
