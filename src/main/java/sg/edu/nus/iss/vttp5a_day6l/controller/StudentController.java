package sg.edu.nus.iss.vttp5a_day6l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import sg.edu.nus.iss.vttp5a_day6l.model.Student;
import sg.edu.nus.iss.vttp5a_day6l.service.StudentRestService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentRestService studentRestService;

    @GetMapping("")
    public String studentForm(@ModelAttribute Student entity) {
        return "studentForm";
    }

    @PostMapping("")
    public String postStudentForm(@ModelAttribute("student") Student entity, BindingResult result) {
        // if (result.hasErrors()){
        //     return "studentForm";
        // }

        studentRestService.createStudent(entity);

        return "redirect:/students/list";
    }

    @GetMapping("/list")
    public String getMethodName(Model model) {
        List<Student> students = new ArrayList<>();
        students = studentRestService.getAllStudents();

        model.addAttribute("students", students);
        return "studentList";
    }
    
}
