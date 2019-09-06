package com.example.todolist.controller;

import com.example.todolist.exceptions.InvalidDescriptionException;
import com.example.todolist.model.ToDoItem;
import com.example.todolist.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/todo")
public class ToDoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    @ResponseBody
    public List<ToDoItem> getAllToDoItems() {
        log.info("Request - GET - List All");
        return todoService.getAllToDoItems();
    }

    @GetMapping("/{id}")
    public Optional<ToDoItem> getToDoItem(@PathVariable long id) {
        log.info(String.format("Request - GET - Item: %d", id));
        return todoService.getToDoItem(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createToDoItem(@RequestBody ToDoItem toDoItem) throws URISyntaxException, InvalidDescriptionException {
        log.info(String.format("Request - POST - Create Item: %s", toDoItem));
        long id = todoService.createToDoItem(toDoItem);
        return ResponseEntity
                .created(new URI(String.valueOf(id)))
                .build();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyToDoItem(@PathVariable long id, @RequestBody ToDoItem toDoItem) throws URISyntaxException, InvalidDescriptionException {
        log.info(String.format("Request - PUT - Update or Create: %s", toDoItem));
        long identifier = todoService.updateOrCreateToDoItem(id, toDoItem);
        return ResponseEntity
                .created(new URI(String.valueOf(identifier)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDoItem(@PathVariable long id) {
        log.info(String.format("Request - DELETE - Item: %d", id));
        todoService.deleteToDoItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
