package sg.edu.nus.iss.vttp5a_day6l.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.vttp5a_day6l.model.Student;
import sg.edu.nus.iss.vttp5a_day6l.service.StudentService;

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
}
