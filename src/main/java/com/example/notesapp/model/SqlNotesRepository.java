package com.example.notesapp.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface SqlNotesRepository extends NotesRepository, JpaRepository<Note, Integer> {
}
