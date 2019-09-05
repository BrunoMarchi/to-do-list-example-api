package com.example.todolist.model;

import com.example.todolist.exceptions.InvalidDescriptionException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import static com.example.todolist.Utils.isValidDescription;

@Entity
@Data
@NoArgsConstructor
public class ToDoItem {
    @ToString.Exclude
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "description", nullable = false)
    private String description;

    public void update(ToDoItem item) throws InvalidDescriptionException {
        setNewDescription(item.getDescription());
        setNewtStatus(item.getStatus());
    }

    private void setNewtStatus(Status status) {
        if(status != null) {
            this.status = status;
        }
    }

    private void setNewDescription(String description) throws InvalidDescriptionException {
        if (description != null && description.length() > 100) {
            throw new InvalidDescriptionException();
        }
        if(isValidDescription(description)) {
            this.description = description;
        }
    }
}