package com.example.todolist.repository;

import com.example.todolist.model.ToDoItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepository extends PagingAndSortingRepository<ToDoItem, Long> {
    List<ToDoItem> findAll();

}
