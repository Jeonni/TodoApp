package com.coz.TodoApp.dto;

import lombok.Getter;

@Getter
public class TodoPatchDTO {
    private Long id;
    private String title;
    private int todoOrder;
    private boolean completed;

    public void setId(Long id) {
        this.id = id;
    }
}
