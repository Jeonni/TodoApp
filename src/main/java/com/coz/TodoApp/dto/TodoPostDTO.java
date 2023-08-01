package com.coz.TodoApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TodoPostDTO {
    private String title;
    private int todoOrder;
    private boolean completed;
}
