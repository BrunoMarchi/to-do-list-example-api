package com.example.todolist;

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

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceListAllTests {

    @Mock
    private ToDoRepository mockRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testListAllNullList() {
        Mockito.when(mockRepository.findAll())
                .thenReturn(null);

        List<ToDoItem> itemList = todoService.getAllToDoItems();

        assertThat(itemList)
                .isNull();
    }

    @Test
    public void testListAllEmptyList() {
        List<ToDoItem> emptyList= new ArrayList<>();

        Mockito.when(mockRepository.findAll())
                .thenReturn(emptyList);

        List<ToDoItem> itemList = todoService.getAllToDoItems();

        assertThat(itemList)
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testListAllSize1() {
        List<ToDoItem> mockList= new ArrayList<>();
        mockList.add(ToDoItem.builder().description("Test Description 1").status(Status.pending).id(1).build());

        Mockito.when(mockRepository.findAll()).thenReturn(mockList);

        List<ToDoItem> itemList = todoService.getAllToDoItems();

        assertThat(itemList)
                .isNotNull()
                .isNotEmpty();
        assertThat(itemList.size())
                .isEqualTo(1);
    }
}
