package sg.edu.nus.iss.vttp5a_day6l.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import sg.edu.nus.iss.vttp5a_day6l.repo.ListRepo;
import sg.edu.nus.iss.vttp5a_day6l.model.Student;

@Service
public class StudentService {
    @Autowired
    ListRepo studentRepo;

    // write your CRUD functions here
    public void add(Student student) {
        studentRepo.rightPush("students", student.toString());
    }

    public List<String> findAll(String redisKey) {
        List<String> students = studentRepo.getList(redisKey);

        // i dont know how to do here. please use JSON-P to cast to list of students

        return null;
    }
}
