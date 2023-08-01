package com.coz.TodoApp.service;

import com.coz.TodoApp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findById(Long id);

    Optional<Todo> findByTitle(String title);
}
