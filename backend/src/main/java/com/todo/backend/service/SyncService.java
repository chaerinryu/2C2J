package com.todo.backend.service;

import com.todo.backend.controller.request.ChallengeRequestdto;
import com.todo.backend.controller.request.LogRequestdto;
import com.todo.backend.controller.request.RoutineRequestdto;
import com.todo.backend.controller.request.TodoRequestdto;
import com.todo.backend.entity.*;
import com.todo.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

// 여기에 투두, 챌린지 동기화도 같이 작성
@Service
public class SyncService {

    @Autowired
    private RoutineService routineService;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private ChallengeService challengeService;
    @Autowired
    private ChallengeRepository challengeRepository;
    @Autowired
    private LogService logService;
    @Autowired
    private LogRepository logRepository;

    // 루틴 동기화 데이터
    public List<RoutineEntity> synchronizeRoutineWithServer(List<RoutineRequestdto> localRoutines) {
        // 가장 최신 데이터의 lastData를 서버로부터 가져옴
        LocalDateTime serverLastData = routineService.getLastData();

        // 최신 데이터 이후 추가된 데이터 필터링
        List<RoutineRequestdto> addedData = routineService.filterAddedData(localRoutines, serverLastData);

        // 최신 데이터 이후 수정된 데이터 필터링
        List<RoutineRequestdto> modifiedData = routineService.filterModifiedAfter(localRoutines, serverLastData);

        // 추가된 데이터를 서버에 전송 및 저장
        addRoutineToServer(addedData);

        // 수정된 데이터를 서버에 전송 및 저장
        updateRoutineOnServer(modifiedData);

        // 동기화된 루틴 엔티티 반환
        return routineService.getAllRoutine();
    }

    // 루틴 추가 데이터 동기화
    private void addRoutineToServer(List<RoutineRequestdto> addedData) {
        for (RoutineRequestdto routine : addedData) {
            routineService.addRoutine(routine);
        }
    }

    // 루틴 수정 데이터 동기화
    private void updateRoutineOnServer(List<RoutineRequestdto> modifiedData) {
        for (RoutineRequestdto routine : modifiedData) {
            routineService.updateRoutine(routine.getId(), routine);
        }
    }

    // 투두 동기화 데이터
    public List<TodoEntity> synchronizeTodoWithServer(String userId, List<TodoRequestdto> localTodos) {
        // 가장 최신 데이터의 lastData를 서버로부터 가져옴
        LocalDateTime serverLastData = todoService.getLastData(userId);

        // 최신 데이터 이후 추가된 데이터 필터링
        List<TodoRequestdto> addedData = todoService.filterAddedData(userId, localTodos, serverLastData);

        // 최신 데이터 이후 수정된 데이터 필터링
        List<TodoRequestdto> modifiedData = todoService.filterModifiedAfter(localTodos, serverLastData);

        // 추가된 데이터를 서버에 전송 및 저장
        addTodoToServer(userId, addedData);

        // 수정된 데이터를 서버에 전송 및 저장
        updateTodoOnServer(modifiedData);

        // 동기화된 투두 엔티티 반환
        return todoService.getAllTodo(userId);
    }

    // 투두 추가 데이터 동기화
    private void addTodoToServer(String userId, List<TodoRequestdto> addedData) {
        for (TodoRequestdto todo : addedData) {
            todoService.addTodo(userId, todo);
        }
    }

    // 투두 수정 데이터 동기화
    private void updateTodoOnServer(List<TodoRequestdto> modifiedData) {
        for (TodoRequestdto todo : modifiedData) {
            todoService.updateTodo(todo.getId(), todo); // 수정된 일정 id 얻어서 하는듯
        }
    }

    // 챌린지 동기화 데이터
    public List<ChallengeEntity> synchronizeChallengeWithServer(List<ChallengeRequestdto> localChallenges) {
        // 가장 최신 데이터의 lastData를 서버로부터 가져옴
        LocalDateTime serverLastdata = challengeService.getLastData();

        // 최신 데이터 이후 추가된 데이터 필터링
        List<ChallengeRequestdto> addedData = challengeService.filterAddedData(localChallenges, serverLastdata);

        // 최신 데이터 이후 수정된 데이터 필터링
        //List<ChallengeRequestdto> modifiedData = challengeService.filterModifiedAfter(localChallenges, serverLastdata);

        // 추가된 데이터를 서버에 전송 및 저장
        addChallengeToServer(addedData);

        // 수정된 데이터를 서버에 전송 및 저장
        //updateDataOnServer(modifiedData);

        // 동기화된 챌린지 엔티티 반환
        return challengeService.getAllChallenge();
    }

    // 챌린지 추가 데이터 동기화
    private void addChallengeToServer(List<ChallengeRequestdto> addedData) {
        for (ChallengeRequestdto challenge : addedData) {
            challengeService.addChallenge(challenge);
        }
    }

    // 챌린지 수정 데이터 동기화
    private void updateChallengeOnServer(List<ChallengeRequestdto> modifiedData) {
        for (ChallengeRequestdto challenge : modifiedData) {
            challengeService.updateChallenge(challenge.getId(),challenge);
        }
    }

    // 로그 데이터 동기화(미완성)
    public List<LogEntity> synchronizeLogWithServer(List<LogRequestdto> localLogs) {
        for (LogRequestdto localLog : localLogs) {
            LogEntity logEntity = new LogEntity();
            logEntity.setDate(localLog.getDate());
            logEntity.setComplete(localLog.getComplete());
            logEntity.setTodo(todoRepository.findById(localLog.getTodoId()).orElse(null));
            logEntity.setRoutine(routineRepository.findById(localLog.getRoutineId()).orElse(null));
            logEntity.setChallenge(challengeRepository.findById(localLog.getChallengeId()).orElse(null));
            logRepository.save(logEntity);
        }
        return logService.getAllLog();
    }
}
