package org.example.cs322project.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.cs322project.model.enums.Schedule;
import org.example.cs322project.model.enums.Semester;

import java.util.List;

@Getter
@Setter
public class PromptDto {
    private List<String> prefTopics;

    private Integer year;

    private Semester semester;

    private Schedule prefLoad;
}
