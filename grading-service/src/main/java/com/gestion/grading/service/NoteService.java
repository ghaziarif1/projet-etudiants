package com.gestion.grading.service;

import com.gestion.grading.config.EtudiantServiceClient;
import com.gestion.grading.dto.NoteDTO;
import com.gestion.grading.entity.Note;
import com.gestion.grading.mapper.NoteMapper;
import com.gestion.grading.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final EtudiantServiceClient etudiantServiceClient;

    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll().stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }

    public NoteDTO getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        return noteMapper.toDto(note);
    }

    public NoteDTO createNote(NoteDTO noteDTO) {
        // Verify student exists
        try {
            etudiantServiceClient.getEtudiantById(noteDTO.getStudentId());
        } catch (Exception e) {
            throw new RuntimeException("Student not found");
        }

        Note note = noteMapper.toEntity(noteDTO);
        Note savedNote = noteRepository.save(note);
        return noteMapper.toDto(savedNote);
    }

    public NoteDTO updateNote(Long id, NoteDTO noteDTO) {
        Note existingNote = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        // Verify student exists
        try {
            etudiantServiceClient.getEtudiantById(noteDTO.getStudentId());
        } catch (Exception e) {
            throw new RuntimeException("Student not found");
        }

        existingNote.setStudentId(noteDTO.getStudentId());
        existingNote.setMatiere(noteDTO.getMatiere());
        existingNote.setValeur(noteDTO.getValeur());

        Note updatedNote = noteRepository.save(existingNote);
        return noteMapper.toDto(updatedNote);
    }

    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new RuntimeException("Note not found");
        }
        noteRepository.deleteById(id);
    }

    public List<NoteDTO> getNotesByStudentId(Long studentId) {
        return noteRepository.findByStudentId(studentId).stream()
                .map(noteMapper::toDto)
                .collect(Collectors.toList());
    }
}