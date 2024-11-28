package sg.edu.nus.iss.vttp5a_day6l.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import sg.edu.nus.iss.vttp5a_day6l.model.Student;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/students")
public class StudentController {
    @GetMapping("")
    public String studentForm() {
        return "studentform";
    }

    @PostMapping("")
    public String postStudentForm(@ModelAttribute Student entity) {
        return "redirect:/students/list";
    }

     @GetMapping("/list")
     public String getMethodName(Model model) {
        List<String> students = new ArrayList<>();
        model.addAttribute("students", students);

        return "studentlist";
     }
     
    
    
}
