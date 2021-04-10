package com.example.todoapp.service;

import com.example.todoapp.domain.TodoItem;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepo;

    public List<TodoItem> fetchAllTodoItems() {
        return todoRepo.fetchAllTodoItems();
    }

    public TodoItem updateTodoItem(int id, TodoItem todoItem) {
        Optional<TodoItem> todoOpt = todoRepo.fetchAllTodoItems()
                                                .stream()
                                                .filter(item -> item.getId().equals(id))
                                                .findAny();
        if(todoOpt.isPresent()) {
            TodoItem item = todoOpt.get();
            item.setDone(todoItem.isDone());
            item.setTask(todoItem.getTask());
            return item;
        }
        return null;
    }

    public TodoItem createTodoItem() {
        TodoItem todoItem = new TodoItem();
        todoItem.setDone(false);
        todoItem = todoRepo.save(todoItem);
        todoItem.setTask("Task #" + todoItem.getId());
        return todoItem;
    }

    public void deleteTodoItem(Integer id) {
        todoRepo.delete(id);
    }
}
