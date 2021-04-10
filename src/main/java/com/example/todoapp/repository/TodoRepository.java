package com.example.todoapp.repository;

import com.example.todoapp.domain.TodoItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TodoRepository {

    private Integer idCounter = 0;
    private List<TodoItem> todoItems = new ArrayList<>();

    public List<TodoItem> fetchAllTodoItems() {
        if(todoItems.size() == 0) {
            TodoItem item1 = new TodoItem();
            item1.setId(++idCounter);
            item1.setDone(true);
            item1.setTask("Task #1");

            todoItems.add(item1);
        }
        return todoItems;
    }

    public TodoItem save(TodoItem todoItem) {
        todoItems.add(todoItem);
        todoItem.setId(++idCounter);
        return todoItem;
    }

    public void delete(Integer id) {
        todoItems = todoItems.stream()
                             .filter(todoItem -> !todoItem.getId().equals(id))
                             .collect(Collectors.toList());
    }
}
