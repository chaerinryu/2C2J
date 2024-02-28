package com.todo.backend.controller;

import com.todo.backend.controller.request.ChallengeRequestdto;
import com.todo.backend.controller.request.LogRequestdto;
import com.todo.backend.controller.request.RoutineRequestdto;
import com.todo.backend.controller.request.TodoRequestdto;
import com.todo.backend.controller.response.ChallengeResponsedto;
import com.todo.backend.controller.response.LogResponsedto;
import com.todo.backend.controller.response.RoutineResponsedto;
import com.todo.backend.controller.response.TodoResponsedto;
import com.todo.backend.entity.ChallengeEntity;
import com.todo.backend.entity.LogEntity;
import com.todo.backend.entity.RoutineEntity;
import com.todo.backend.entity.TodoEntity;
import com.todo.backend.service.SyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 여기에 투두, 챌린지 동기화도 같이 작성
@RestController
@RequestMapping("/api/syncWithServer")
public class SyncController {

    @Autowired
    private SyncService syncService;

    // 루틴 동기화 데이터 반환
    @PostMapping("/routin")
    public List<RoutineResponsedto> syncRoutineWithServer(@RequestBody List<RoutineRequestdto> localRoutines) {
        List<RoutineEntity> synchronizedData = syncService.synchronizeRoutineWithServer(localRoutines);
        return RoutineResponsedto.fromRoutineList(synchronizedData);
    }

    // 투두 동기화 데이터 반환
    @PostMapping("/todo/{userId}")
    public List<TodoResponsedto> syncTodoWithServer(@RequestBody List<TodoRequestdto> localTodos, @PathVariable String userId) {
        List<TodoEntity> synchronizedData = syncService.synchronizeTodoWithServer(userId, localTodos);
        return TodoResponsedto.fromTodoList(synchronizedData);
    }

    // 챌린지 동기화 데이터 반환
    @PostMapping("/challenge")
    public List<ChallengeResponsedto> syncChallengeWithServer(@RequestBody List<ChallengeRequestdto> localChallenges) {
        List<ChallengeEntity> synchronizedData = syncService.synchronizeChallengeWithServer(localChallenges);
        return ChallengeResponsedto.fromChallengeList(synchronizedData);
    }

    // 로그 동기화 데이터 반환
    @PostMapping("/log")
    public List<LogResponsedto> syncLogWithServer(@RequestBody List<LogRequestdto> localLogs) {
        List<LogEntity> synchronizedData = syncService.synchronizeLogWithServer(localLogs);
        return LogResponsedto.fromLogList(synchronizedData);
    }
}
