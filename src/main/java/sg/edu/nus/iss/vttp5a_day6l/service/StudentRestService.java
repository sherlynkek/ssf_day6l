package sg.edu.nus.iss.vttp5a_day6l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_day6l.model.Student;

@Service
public class StudentRestService {

    // @Autowired
    // RestTemplate restTemplate;

    RestTemplate restTemplate = new RestTemplate();

    public static final String studentUrl = "http://localhost:3000/api/students";

    public List<String> getAllStudents() {
        // restTemplate.getForEntity(studentUrl, Student.class);
        ResponseEntity<String> data = restTemplate.getForEntity(studentUrl, String.class);

        // lecture day 16 slide 7 and 9
        JsonReader jReader = Json.createReader(new StringReader(data.toString()));
        JsonObject jObject = jReader.readObject();
        
        List<Student> student = new ArrayList<>();
        Set<Entry<String, JsonValue>> entries = jObject.entrySet();

        for(Entry<String, JsonValue> entry: entries) {
            Student s = new Student();
            s.setId(Integer.parseInt(entry.getValue().asJsonObject().getString("id")));
            s.setFullName(entry.getValue().asJsonObject().getString("fullName"));
            s.setEmail(entry.getValue().asJsonObject().getString("email"));
            s.setPhoneNumber(entry.getValue().asJsonObject().getString("phoneNumber"));
            student.add(s);
        }

        return null;
    }
}
