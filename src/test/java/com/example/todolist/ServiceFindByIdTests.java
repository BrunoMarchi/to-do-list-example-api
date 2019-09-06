package com.example.todolist;


import com.example.todolist.exceptions.InvalidDescriptionException;
import com.example.todolist.model.Status;
import com.example.todolist.model.ToDoItem;
import com.example.todolist.repository.ToDoRepository;
import com.example.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceFindByIdTests {

    @Mock
    private ToDoRepository mockRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testFindByNotExistingId() {
        Optional<ToDoItem> optItem = Optional.ofNullable(null);

        Mockito.when(mockRepository.findById(Long.valueOf(1))).thenReturn(optItem);

        Optional<ToDoItem> item = todoService.getToDoItem(1);

        assertThat(item.isPresent()).isFalse();
    }

    @Test
    public void testFindByExistingId() {
        Optional<ToDoItem> optItem = Optional.ofNullable(
                ToDoItem.builder()
                        .description("Test Description 1")
                        .status(Status.pending)
                        .id(1)
                        .build());

        Mockito.when(mockRepository.findById(Long.valueOf(1))).thenReturn(optItem);

        Optional<ToDoItem> item = todoService.getToDoItem(1);

        assertThat(item.isPresent()).isTrue();
        assertThat(item.get())
                .isNotNull()
                .hasFieldOrPropertyWithValue("description", "Test Description 1")
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("status", Status.pending);
    }


}
