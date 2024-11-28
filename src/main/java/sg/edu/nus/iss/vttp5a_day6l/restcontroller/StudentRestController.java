package sg.edu.nus.iss.vttp5a_day6l.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.vttp5a_day6l.constant.Constant;
import sg.edu.nus.iss.vttp5a_day6l.model.Student;
import sg.edu.nus.iss.vttp5a_day6l.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/api/students", produces = "application/json")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    @PostMapping(path = {"", "/create"})
    public ResponseEntity<String> create(@RequestBody Student entity) {

        studentService.add(entity);
        // return new ResponseEntity("true", HttpStatus.OK);
        return ResponseEntity.ok().body("true");
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.findAll(Constant.studentKey);
        return ResponseEntity.ok().body(students);
    }
    
}
