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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceCreateAndUpdateTests {

    @Mock
    private ToDoRepository mockRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    public void testCreateToDoItemNullDescription() {
        ToDoItem item = ToDoItem.builder()
                .status(Status.pending)
                .id(1)
                .build();

        Mockito.when(mockRepository.save(item)).thenReturn(item);

        assertThrows(InvalidDescriptionException.class, () -> {
            todoService.createToDoItem(item);
        });
    }

    @Test
    public void testCreateToDoItemEmptyDescription() {
        ToDoItem item = ToDoItem.builder()
                .description("")
                .status(Status.pending)
                .id(1)
                .build();

        Mockito.when(mockRepository.save(item)).thenReturn(item);

        assertThrows(InvalidDescriptionException.class, () -> {
            todoService.createToDoItem(item);
        });
    }

    @Test
    public void testCreateToDoItemDescriptionTooLong() {
        ToDoItem item = ToDoItem.builder()
                .description("This description is too long, so we expect an exception to be thrown, but I need to keep writing because the maximum length accepted is 100 characters.")
                .status(Status.pending)
                .id(1)
                .build();

        Mockito.when(mockRepository.save(item)).thenReturn(item);

        assertThrows(InvalidDescriptionException.class, () -> {
            todoService.createToDoItem(item);
        });
    }

    @Test
    public void testCreateToDoItemSuccessful() throws InvalidDescriptionException {
        ToDoItem item = ToDoItem.builder()
                .description("Test Description 1")
                .status(Status.pending)
                .id(1)
                .build();

        Mockito.when(mockRepository.save(item)).thenReturn(item);

        long id = todoService.createToDoItem(item);

        assertThat(id).isEqualTo(1);
    }

    @Test
    public void testUpdateToDoItem() throws InvalidDescriptionException {
        ToDoItem item = ToDoItem.builder()
                .description("Test Description 1")
                .status(Status.pending)
                .id(1)
                .build();

        Optional<ToDoItem> optItem = Optional.ofNullable(item);

        Mockito.when(mockRepository.findById(Long.valueOf(1))).thenReturn(optItem);
        Mockito.when(mockRepository.save(item)).thenReturn(item);

        long id = todoService.updateOrCreateToDoItem(1, item);

        assertThat(id).isEqualTo(1);
    }

}
