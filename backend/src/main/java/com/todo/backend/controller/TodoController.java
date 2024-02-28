package com.todo.backend.controller;

import com.todo.backend.controller.request.TodoRequestdto;
import com.todo.backend.controller.response.TodoResponsedto;
import com.todo.backend.entity.TodoEntity;
import com.todo.backend.entity.UserEntity;
import com.todo.backend.service.TodoService;
import com.todo.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public List<TodoResponsedto> getAllTodo(@PathVariable String userId) {
        List<TodoEntity> todo = todoService.getAllTodo(userId);
        return TodoResponsedto.fromTodoList(todo);
    }

    // 투두 추가
    @PostMapping("/{userId}")
    public TodoEntity addTodo(@PathVariable String userId, @RequestBody TodoRequestdto todoRequest) {
        return todoService.addTodo(userId, todoRequest);
    }

    // 수정
    @PutMapping("/{id}") // 투두 id를 식별하여 수행, 밑의 @PathVariable 어노테이션이 파라미터로 전달된 id를 찾는 것
    public TodoEntity updateTodo(@PathVariable int id, @RequestBody TodoRequestdto todoRequest) {
        return todoService.updateTodo(id, todoRequest);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
    }
}
