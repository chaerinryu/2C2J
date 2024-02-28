package com.todo.backend.service;

import com.todo.backend.controller.request.TodoRequestdto;
import com.todo.backend.entity.DoType;
import com.todo.backend.entity.TodoEntity;
import com.todo.backend.entity.UserEntity;
import com.todo.backend.repository.TodoRepository;
import com.todo.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    // 사용자의 모든 할 일 받아오기
    public List<TodoEntity> getAllTodo(String userId) {
        return todoRepository.findByUserId(userId);
    }

    public TodoEntity addTodo(String userId, TodoRequestdto todoRequest) { // 추가
        TodoEntity todoEntity = new TodoEntity();
        UserEntity user = userRepository.findByUserId(userId)
                // 예외처리: 해당 userId가 없다면
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        todoEntity.setUserId(user);
        todoEntity.setTitle(todoRequest.getTitle());
        todoEntity.setContent(todoRequest.getContent());
        todoEntity.setMemo(todoRequest.getMemo());
        todoEntity.setDotype(DoType.valueOf(todoRequest.getDotype().toUpperCase()));
        todoEntity.setLastData(todoRequest.getLastData());

        return todoRepository.save(todoEntity);
    }

    // 수정이랑 삭제는 일정 id만 있으면 가능해서 사용자 정보 뺐음(아니다 싶으면 변경하겠음)
    public TodoEntity updateTodo(int id, TodoRequestdto todoRequest) { // 수정
        TodoEntity todoEntity = todoRepository.findById(id)
                // 예외처리: 해당 id의 todo가 없다면
                .orElseThrow(() -> new EntityNotFoundException("Todo with id " + id + " not found"));

        todoEntity.setTitle(todoRequest.getTitle());
        todoEntity.setContent(todoRequest.getContent());
        todoEntity.setMemo(todoRequest.getMemo());
        todoEntity.setDotype(DoType.valueOf(todoRequest.getDotype().toUpperCase()));
        todoEntity.setLastData(todoRequest.getLastData());

        return todoRepository.save(todoEntity);
    }

    public void deleteTodo(int id) { // 삭제
        todoRepository.deleteById(id);
    }

    public LocalDateTime getLastData(String userId) {
        return todoRepository.findLastData(userId);
    }

    // 가장 최신 데이터 이후 저장된 데이터
    public List<TodoRequestdto> filterAddedData(String userId, List<TodoRequestdto> localTodos, LocalDateTime serverLastSaved) {
        return localTodos.stream()
                .filter(todo -> todo.getUserId().equals(userId)) // 해당 사용자의 일정만 필터링
                .filter(todo -> todo.getLastData().isAfter(serverLastSaved) || todo.getLastData().isEqual(serverLastSaved))
                .collect(Collectors.toList());
    }

    // 가장 최신 데이터 이후 수정된 데이터
    public List<TodoRequestdto> filterModifiedAfter(List<TodoRequestdto> localTodos, LocalDateTime serverLastModified) {
        return localTodos.stream()
                .filter(todo -> todo.getLastData().isAfter(serverLastModified))
                .collect(Collectors.toList());
    }
}
