package org.example.cs322project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.example.cs322project.model.Course;
import org.example.cs322project.model.Section;
import org.example.cs322project.model.User;
import org.example.cs322project.model.enums.Semester;
import org.example.cs322project.repository.CourseRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllPassedCourses(Long userId){
        return courseRepository.getPassedByUserId(userId);
    }

    public boolean isCourseAvailable(Long userId, Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        List<Course> passedCourse = getAllPassedCourses(userId);
        return !course.get().getPrerequisites().stream().filter(c -> !passedCourse.contains(c)).toList().isEmpty();
    }

    public List<Course> getAvailableCourses(Long userId){
        return courseRepository.findAll().stream().filter(c -> isCourseAvailable(userId, c.getId())).toList();
    }

    public Map<Course, List<Section>> getAvailableSections(Long userId, Integer year, Semester semester){
        return getAllPassedCourses(userId).stream().collect(Collectors.toMap(
                course -> course,
                course -> course.getSections().stream()
                        .filter(section -> section.getYear().equals(year)
                                && section.getSemester().equals(semester))
                        .toList()
        ));
    }
}