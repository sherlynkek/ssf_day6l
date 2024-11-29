package sg.edu.nus.iss.vttp5a_day6l.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import sg.edu.nus.iss.vttp5a_day6l.constant.Url;
import sg.edu.nus.iss.vttp5a_day6l.model.Student;

@Service
public class StudentRestService {

    // @Autowired
    // RestTemplate restTemplate;

    RestTemplate restTemplate = new RestTemplate();

    public static final String studentUrl = "http://localhost:3000/api/students";

    public List<Student> getAllStudents() {
        // restTemplate.getForEntity(studentUrl, Student.class);
        ResponseEntity<String> data = restTemplate.getForEntity(studentUrl, String.class);
        String payload = data.getBody();

        // lecture day 16 slide 7 and 9
        List<Student> students = new ArrayList<>();
        JsonReader jReader = Json.createReader(new StringReader(payload));
        JsonArray jArray = jReader.readArray();

        for(int i = 0; i < jArray.size(); i++) {
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
        // day 16 - slide 7
        // convert to Json string using Json-P functions
        JsonObjectBuilder jObject = Json.createObjectBuilder();
        jObject.add("id", student.getId());
        jObject.add("fullName", student.getFullName());
        jObject.add("email", student.getEmail());
        jObject.add("phoneNumber", student.getPhoneNumber());
        String requestPayload = jObject.build().toString();

        RequestEntity<String> requestEntity = RequestEntity.post(Url.studentUrl + "/create").body(requestPayload);

        ResponseEntity<String> responseResult = restTemplate.exchange(requestEntity, String.class);

        return responseResult.getBody();

    }
}
