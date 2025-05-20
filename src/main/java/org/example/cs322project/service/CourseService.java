package org.example.cs322project.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.example.cs322project.model.Course;
import org.example.cs322project.model.User;
import org.example.cs322project.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

}
