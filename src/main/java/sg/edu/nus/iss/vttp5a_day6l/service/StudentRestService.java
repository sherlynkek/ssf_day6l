package sg.edu.nus.iss.vttp5a_day6l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.vttp5a_day6l.constant.Url;
import sg.edu.nus.iss.vttp5a_day6l.model.Student;

@Service
public class StudentRestService {
    
    @Autowired
    RestTemplate restTemplate;

    // private static final String studentUrl = "http://localhost:3000/api/students";
    // Above has been moved to Url.java in constant folder

    public List<Student> getAllStudents() {
        // Data contains both the HEADER and the BODY 
        ResponseEntity<String> data = restTemplate.getForEntity(Url.studentUrl, String.class);
        // We want the body only
        String payload = data.getBody();

        // reference day 16: slides 7 and 9
        JsonReader jReader = Json.createReader(new StringReader(payload));
        JsonArray jArray = jReader.readArray();
        
        List<Student> students = new ArrayList<>(); 
        for (int i = 0; i < jArray.size(); i++) {
            JsonObject jObject = jArray.getJsonObject(i);
            Student s = new Student();
            s.setId(jObject.getInt("id"));
            s.setFullName(jObject.getString("fullName"));
            s.setEmail(jObject.getString("email"));
            s.setPhoneNumber(jObject.getString("phoneNumber"));
            students.add(s);
        }

        return students;
    }

    public String createStudent(Student student) {
        // day 16: slide 7
        // Convert to Json string using Json-P functions
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        jObject.add("id", student.getId());
        jObject.add("fullName", student.getFullName());
        jObject.add("email", student.getEmail());
        jObject.add("phoneNumber", student.getPhoneNumber());
        String requestPayload = jObject.build().toString();

        RequestEntity<String> requestEntity = RequestEntity.post(Url.studentUrl, "/create").body(requestPayload);

        ResponseEntity<String> responseResult = restTemplate.exchange(requestEntity, String.class);

        return responseResult.getBody();
    }
}
