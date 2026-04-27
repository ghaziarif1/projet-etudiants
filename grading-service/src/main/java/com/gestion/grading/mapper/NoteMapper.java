package com.gestion.grading.mapper;

import com.gestion.grading.dto.NoteDTO;
import com.gestion.grading.entity.Note;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDTO toDto(Note note);
    Note toEntity(NoteDTO noteDTO);
}