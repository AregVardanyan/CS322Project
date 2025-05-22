package org.example.cs322project.service;

import lombok.RequiredArgsConstructor;
import org.example.cs322project.model.Course;
import org.example.cs322project.model.Section;
import org.example.cs322project.model.dto.PromptDto;
import org.example.cs322project.model.enums.Semester;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PlannerService {

    private final CourseService courseService;
    private final GeminiClient geminiClient;

    public String generatePlan(Long userId, PromptDto dto) {
        Map<Course, List<Section>> availableCourses = courseService.getAvailableSections(userId, dto.getYear(), dto.getSemester());

        String prompt = buildPrompt(dto, availableCourses);
        return geminiClient.getGeminiPlan(prompt);
    }

    private String buildPrompt(PromptDto dto, Map<Course, List<Section>> courseSectionsMap) {
        StringBuilder sb = new StringBuilder();

        sb.append("You are a helpful academic advisor. Based on the student's preferences and the available courses, recommend a semester plan.\n\n");

        sb.append("Student Preferences:\n");
        sb.append("- Desired workload: ").append(dto.getPrefLoad()).append("\n");
        sb.append("- Interested topics: ").append(dto.getPrefTopics()).append("\n\n");

        sb.append("Available Courses and Sections:\n");
        for (Map.Entry<Course, List<Section>> entry : courseSectionsMap.entrySet()) {
            Course course = entry.getKey();
            List<Section> sections = entry.getValue();

            for (Section section : sections) {
                sb.append(String.format(
                        "- %s: Section %s | Schedule: %s %s | Year: %d | Semester: %s | Topic: %s | Prerequisites: %s\n",
                        course.getCode(),
                        section.getCode(),
                        section.getSchedule(),
                        section.getHours(),
                        section.getYear(),
                        section.getSemester(),
                        course.getTopic(),
                        formatPrereqs(course.getPrerequisites())
                ));
            }
        }

        sb.append("\nPlease recommend 3–5 suitable courses for this semester. Explain your choices briefly.");
        return sb.toString();
    }

    private String formatPrereqs(List<Course> prerequisites) {
        if (prerequisites == null || prerequisites.isEmpty()) {
            return "None";
        }
        return prerequisites.stream()
                .map(Course::getCode)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
    }
}
