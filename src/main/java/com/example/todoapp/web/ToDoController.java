package com.example.todoapp.web;

import com.example.todoapp.domain.TodoItem;
import com.example.todoapp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ToDoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("api/todoItems")
    public ResponseEntity<?> fetchAllTodoItems() {
        List<TodoItem> todoItems = todoService.fetchAllTodoItems();
        return ResponseEntity.ok(todoItems);
    }

    @PostMapping("/api/todoItems")
    public ResponseEntity<?> createNewTodoItem() {
        TodoItem todoItem = todoService.createTodoItem();
        return ResponseEntity.ok(todoItem);
    }

    @PutMapping("api/todoItems/{id}")
    public ResponseEntity<?> updateTodoItem(@PathVariable String id, @RequestBody TodoItem todoItem) {
        int id_ = Integer.parseInt(id);
        TodoItem updatedTodoItem = todoService.updateTodoItem(id_, todoItem);

        return ResponseEntity.ok(updatedTodoItem);
    }

    @DeleteMapping("api/todoItems/{id}")
    public ResponseEntity<?> deleteTodoItem(@PathVariable Integer id) {
        todoService.deleteTodoItem(id);
        return ResponseEntity.ok("ok");
    }

}
