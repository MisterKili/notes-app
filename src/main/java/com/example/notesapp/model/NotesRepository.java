package com.example.notesapp.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NotesRepository {

    List<Note> findAll();

    Page<Note> findAll(Pageable page);

    Optional<Note> findById(Integer id);

    boolean existsById(Integer id);

    Note save(Note entity);

    void deleteById(Integer id);
}
