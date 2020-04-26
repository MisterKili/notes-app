package com.example.notesapp.controller;

import com.example.notesapp.model.Note;
import com.example.notesapp.model.NotesRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class NotesController {
    private final NotesRepository repository;

    public NotesController(final NotesRepository repository) {
        this.repository = repository;
    }


    @GetMapping(path = "/notes", params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Note>> readAllNotes() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping(path = "/notes")
    ResponseEntity<List<Note>> readAllNotes(Pageable page) {
        return ResponseEntity.ok(repository.findAll(page).getContent());
    }

    @GetMapping(path = "/notes/{id}")
    ResponseEntity<Note> readNote(@PathVariable int id) {
        return repository.findById(id)
                .map(note -> ResponseEntity.ok(note))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/notes")
    ResponseEntity<Note> createNote(@RequestBody @Valid Note toCreate) {
        Note result = repository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @PutMapping(path = "/notes/{id}")
    ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody @Valid Note toUpdate) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        toUpdate.setId(id);
        repository.save(toUpdate);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/notes/{id}")
    ResponseEntity<?> removeNote(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

}
