package sg.edu.nus.iss.vttp5a_day6l.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import sg.edu.nus.iss.vttp5a_day6l.model.Student;

@Service
public class StudentRestService {
    @Autowired
    RestTemplate restTemplate;

    public static final String studentUrl = "http://localhost:3000/api/students";

    public List<String> getAllStudents() {
        restTemplate.getForEntity(studentUrl, Student.class);

        return null;
    }
}
