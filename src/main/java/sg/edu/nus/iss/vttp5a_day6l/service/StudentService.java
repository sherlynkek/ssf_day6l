package sg.edu.nus.iss.vttp5a_day6l.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.util.ArrayList;
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

    public List<Student> findAll(String redisKey) {
        List<String> students = studentRepo.getList(redisKey);
        List<Student> studentRecords = new ArrayList<>();

        // i dont know how to do here. please use JSON-P to cast to list of students

        JsonArrayBuilder jab = Json.createArrayBuilder();
        
        for(String raw: students) {
            String[] data = raw.split(",");

            //day 16 lecture slide 12
            /* JsonObject jsonObj = Json.createObjectBuilder().add("id", data[0])
                            .add("fullName", data[1])
                            .add("email", data[2])
                            .add("phoneNumber", data[3])
                            .build();
             */
            // put JsonObject into JsonArray - Reference day 16 - slide 7
            // jab.add(jsonObj);

            Student s = new Student(Integer.parseInt(data[0]), data[1], data[2], data[3]);
            studentRecords.add(s);
        }
        return null;
    }
}
