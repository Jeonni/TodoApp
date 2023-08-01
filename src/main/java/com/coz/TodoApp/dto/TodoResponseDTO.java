package com.coz.TodoApp.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TodoResponseDTO {
    private Long id;
    private String title;
    private int todoOrder;
    private boolean completed;
}
